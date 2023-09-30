package ebidar.com.minioms.repository;


import ebidar.com.minioms.model.Customer;
import ebidar.com.minioms.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
    