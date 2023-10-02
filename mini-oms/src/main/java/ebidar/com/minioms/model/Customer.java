package ebidar.com.minioms.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
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

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Order> orders;

    public Customer(String name, String lastName, String exchangeCode, Wallet wallet, Set<Order> orders) {
        this.name = name;
        this.lastName = lastName;
        this.exchangeCode = exchangeCode;
        this.wallet = wallet;
        this.orders = orders;
    }

    public Customer() {
    }

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
