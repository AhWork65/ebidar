package ebidar.com.minioms.model;

import ebidar.com.minioms.model.enums.OrderType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "share_id", nullable = false)
    private Share share;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private BigDecimal amount;
    private Integer count;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;
}
