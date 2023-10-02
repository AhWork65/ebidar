package ebidar.com.minioms.service;

import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.exception.NotValidException;
import ebidar.com.minioms.exception.WalletNoBalanceException;
import ebidar.com.minioms.model.Wallet;
import ebidar.com.minioms.model.enums.SettlementDateType;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface IWalletService {


    @Transactional(rollbackFor = {Exception.class})
    void addBalance(Wallet wallet, BigDecimal price) ;
    @Transactional(rollbackFor = {Exception.class})
    void addBalance(Wallet wallet, BigDecimal price, SettlementDateType dateType) ;

    @Transactional(rollbackFor = {Exception.class})
    Boolean blockBalance(Wallet wallet, BigDecimal price,SettlementDateType dateType)throws WalletNoBalanceException;
    @Transactional(rollbackFor = {Exception.class})
    void returnBlockPrice(Wallet wallet, SettlementDateType dateType, BigDecimal price);
    @Transactional(rollbackFor = {Exception.class})
    void reduceBlockPrice(Wallet wallet, BigDecimal price);

    @Transactional(rollbackFor = {Exception.class})
    BigDecimal getBalancePowerBySettlementDate(Wallet wallet, SettlementDateType dateType);



}
