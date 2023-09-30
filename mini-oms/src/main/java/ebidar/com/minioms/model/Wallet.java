package ebidar.com.minioms.model;

import ebidar.com.minioms.model.enums.SettlementDateType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Order> orders;

    @ElementCollection
    @CollectionTable(name = "wallet_buying_Power",
            joinColumns = {@JoinColumn(name = "wallet_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "settlement_date_type")
    @Column(name = "price")
    private Map<SettlementDateType, BigDecimal> BuyingPower;

    private BigDecimal blockedAmount;
}

