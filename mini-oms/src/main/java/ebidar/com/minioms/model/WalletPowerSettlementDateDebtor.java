package ebidar.com.minioms.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WalletPowerSettlementDateDebtor {

	private BigDecimal debtorPrice;


	@ManyToOne
	@JoinColumn(name = "wallet_power_settlement_date_id")
	private WalletPowerSettlementDate  walletPowerSettlementDate;

	private byte state;

}