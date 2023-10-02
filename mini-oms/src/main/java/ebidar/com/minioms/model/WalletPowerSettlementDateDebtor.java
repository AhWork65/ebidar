package ebidar.com.minioms.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@ToString
@Getter
@Setter
public class WalletPowerSettlementDateDebtor {

	private BigDecimal debtorPrice;


	@ManyToOne
	@JoinColumn(name = "walletPowerSettlementDateDebtors")
	private WalletPowerSettlementDate  walletPowerSettlementDate;

	private byte state;

	public WalletPowerSettlementDateDebtor() {
	}

	public WalletPowerSettlementDateDebtor(BigDecimal debtorPrice, WalletPowerSettlementDate walletPowerSettlementDate, byte state) {
		this.debtorPrice = debtorPrice;
		this.walletPowerSettlementDate = walletPowerSettlementDate;
		this.state = state;
	}
}