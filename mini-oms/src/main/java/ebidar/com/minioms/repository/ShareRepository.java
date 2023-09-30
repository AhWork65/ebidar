package ebidar.com.minioms.repository;


import ebidar.com.minioms.model.Customer;
import ebidar.com.minioms.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {

}
    