package ebidar.com.minioms.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String lastName;
    private String exchangeCode;

    @OneToOne
    @JoinColumn(name = "wallet_id",referencedColumnName = "id")
    private Wallet wallet;

    public void update(Customer customer) {

    }

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(Id, customer.Id) && Objects.equals(name, customer.name) && Objects.equals(lastName, customer.lastName) && Objects.equals(exchangeCode, customer.exchangeCode) && Objects.equals(wallet, customer.wallet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, lastName, exchangeCode, wallet);
    }
}
