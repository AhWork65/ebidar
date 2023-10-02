package ebidar.com.minioms.service;

import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.exception.NotValidException;
import ebidar.com.minioms.exception.WalletNoBalanceException;
import ebidar.com.minioms.model.Wallet;
import ebidar.com.minioms.model.WalletPowerSettlementDate;
import ebidar.com.minioms.model.WalletPowerSettlementDateDebtor;
import ebidar.com.minioms.model.enums.SettlementDateType;
import ebidar.com.minioms.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
public class WalletServiceImpl implements IWalletService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ShareOMSServiceImpl.class);

    private final WalletRepository shareRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository shareRepository) {
        this.shareRepository = shareRepository;
    }


    @Override
    public void addBalance(Wallet wallet, BigDecimal price) {
        wallet.getWalletPowerSettlementDates().stream().filter(x -> x.getDateType().equals(SettlementDateType.T0)).forEach(x -> x.getBalance().add(price));
    }

    @Override
    public void addBalance(Wallet wallet, BigDecimal price, SettlementDateType dateType) {
        wallet.getWalletPowerSettlementDates().stream().filter(x -> x.getDateType().equals(dateType)).forEach(x -> x.getBalance().add(price));
    }

    @Override
    public Boolean blockBalance(Wallet wallet, BigDecimal price, SettlementDateType dateType) throws WalletNoBalanceException {
        var balance = getBalancePowerBySettlementDate(wallet, dateType);
        if (balance.compareTo(price) < 0) {
            LOGGER.error("The wallet has no balance");
            throw new WalletNoBalanceException("The wallet has no balance");
        }
        wallet.setBlockedBalance(price);
        var baseWalletPower = wallet.getWalletPowerSettlementDates().stream().filter(x -> x.getDateType().equals(dateType)).findFirst().get();

        blockBalanceAndAddDecreasingFromWallet(wallet, baseWalletPower, baseWalletPower, price);
        return balance.compareTo(price) < 0;
    }

    @Override
    public void returnBlockPrice(Wallet wallet, SettlementDateType dateType, BigDecimal price) {
        var baseWalletPower = wallet.getWalletPowerSettlementDates().stream().filter(x -> x.getDateType().equals(dateType)).findFirst().get();
        if (baseWalletPower.getWalletPowerSettlementDateDebtors().stream().filter(x -> x.getState() == 1).toList().isEmpty()) {
            baseWalletPower.setBalance(price);
            return;
        }
        var debtorList = baseWalletPower.getWalletPowerSettlementDateDebtors().stream()
                .sorted((f1, f2) -> Integer.compare(f2.getWalletPowerSettlementDate().getDateType().toValue(), f1.getWalletPowerSettlementDate().getDateType().toValue()))
                .toList();

        final BigDecimal[] basePrice = {new BigDecimal(0)};
        basePrice[0] = basePrice[0].add(price);
        debtorList.stream().forEach(x -> {
            var remain = basePrice[0].subtract(x.getDebtorPrice());
            if (remain.compareTo(new BigDecimal(0)) > -1) {
                x.getWalletPowerSettlementDate().setBalance(x.getDebtorPrice());
                x.setState((byte) 0);
            }
            basePrice[0] = basePrice[0].subtract(x.getDebtorPrice());
        });

    }


    @Override
    public void ReduceBlockPrice(Wallet wallet, BigDecimal price) {
        wallet.getBlockPrice().subtract(price);
    }

    @Override
    public BigDecimal getBalancePowerBySettlementDate(Wallet wallet, SettlementDateType dateType) {
        var day = dateType.toValue();
        var balance = new BigDecimal(0);
        wallet.getWalletPowerSettlementDates().stream().filter(x -> x.getDateType().toValue() >= day).forEach(x -> balance.add(x.getBalance()));
        return balance;
    }

    private void blockBalanceAndAddDecreasingFromWallet(Wallet wallet, WalletPowerSettlementDate baseWalletPower, WalletPowerSettlementDate current, BigDecimal price) {

        var result = DecreasingFromBaseWalletPower(baseWalletPower, price);

        if (!baseWalletPower.equals(current)) {
            baseWalletPower.setSingleWalletPowerSettlementDateDebtors(new WalletPowerSettlementDateDebtor(current.getBalance(), current, (byte) 1));
        }

        var day = current.getDateType().toValue();

        if (result.compareTo(new BigDecimal(0)) < 1) {
            return;
        }

        if (day == 0) {
            return;
        }
        blockBalanceAndAddDecreasingFromWallet(wallet, baseWalletPower, wallet.getWalletPowerSettlementDates().stream().filter(x -> x.getDateType().toValue() == (day - 1)).findFirst().get(), result);
    }

    private BigDecimal DecreasingFromBaseWalletPower(WalletPowerSettlementDate baseWalletPower, BigDecimal price) {
        var remain = baseWalletPower.getBalance().subtract(price);
        if (remain.compareTo(new BigDecimal(0)) >= 0) {
            baseWalletPower.setBalance(baseWalletPower.getBalance().subtract(price));
            return new BigDecimal(0);
        }
        baseWalletPower.setBalance(baseWalletPower.getBalance().subtract(baseWalletPower.getBalance()));
        return remain;
    }
}
