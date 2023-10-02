package ebidar.com.minioms.model;

import ebidar.com.minioms.model.enums.SettlementDateType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WalletPowerSettlementDate {
    @Id
    private Long id;
    private SettlementDateType dateType;
    private BigDecimal Balance;
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "wallet_power_settlement_date_debtor", joinColumns = @JoinColumn(name = "wallet_power_settlement_date_id"))
    private List<WalletPowerSettlementDateDebtor> walletPowerSettlementDateDebtors = new ArrayList<WalletPowerSettlementDateDebtor>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletPowerSettlementDate that = (WalletPowerSettlementDate) o;
        return Objects.equals(id, that.id) && dateType == that.dateType && Objects.equals(Balance, that.Balance) && Objects.equals(wallet, that.wallet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateType, Balance, wallet);
    }

    public void setSingleWalletPowerSettlementDateDebtors(WalletPowerSettlementDateDebtor walletPowerSettlementDateDebtors) {
        this.walletPowerSettlementDateDebtors.add(walletPowerSettlementDateDebtors);
    }
}
