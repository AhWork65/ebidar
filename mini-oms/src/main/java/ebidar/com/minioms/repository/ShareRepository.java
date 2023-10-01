package ebidar.com.minioms.repository;


import ebidar.com.minioms.model.Customer;
import ebidar.com.minioms.model.Share;
import ebidar.com.minioms.model.enums.SettlementDateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {

    Share findByShareCode(String shareCode);
    List<Share> findBySettlementDate(SettlementDateType dateType);

}
    