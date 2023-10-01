package ebidar.com.minioms.model;

import ebidar.com.minioms.model.enums.OrderState;
import ebidar.com.minioms.model.enums.OrderType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;


@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "share_id", nullable = false)
    private Share share;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private BigDecimal amount;
    private Integer count;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    public Order(Share share, Customer customer, BigDecimal amount, Integer count, OrderType orderType, OrderState orderState) {
        this.share = share;
        this.customer = customer;
        this.amount = amount;
        this.count = count;
        this.orderType = orderType;
        this.orderState = orderState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(Id, order.Id) && Objects.equals(share, order.share) && Objects.equals(customer, order.customer) && Objects.equals(amount, order.amount) && Objects.equals(count, order.count) && orderType == order.orderType && orderState == order.orderState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, share, customer, amount, count, orderType, orderState);
    }
}
