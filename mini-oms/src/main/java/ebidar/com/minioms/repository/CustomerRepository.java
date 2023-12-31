package ebidar.com.minioms.repository;


import ebidar.com.minioms.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByExchangeCode(String exchangeCode);


}
    