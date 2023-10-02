package ebidar.com.minioms.model;

import ebidar.com.minioms.model.enums.SettlementDateType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private BigDecimal blockPrice;

    @OneToMany(mappedBy = "wallet", fetch = FetchType.EAGER)
    private Set<WalletPowerSettlementDate> walletPowerSettlementDates;


    private BigDecimal blockedBalance;

    @OneToOne(mappedBy = "wallet")
    private Customer customer;

    public Wallet() {
    }

    public Wallet(BigDecimal blockPrice, Set<WalletPowerSettlementDate> walletPowerSettlementDates, BigDecimal blockedBalance, Customer customer) {
        this.blockPrice = blockPrice;
        this.walletPowerSettlementDates = walletPowerSettlementDates;
        this.blockedBalance = blockedBalance;
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(Id, wallet.Id) && Objects.equals(blockPrice, wallet.blockPrice) && Objects.equals(walletPowerSettlementDates, wallet.walletPowerSettlementDates) && Objects.equals(blockedBalance, wallet.blockedBalance) && Objects.equals(customer, wallet.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, blockPrice, walletPowerSettlementDates, blockedBalance, customer);
    }
}

