package ebidar.com.minioms.service;

import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.model.Customer;
import ebidar.com.minioms.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OMSService {
    private final static Logger LOGGER = LoggerFactory.getLogger(OMSService.class);

    @Autowired
    private CustomerRepository customerRepository;

    //======================================================================================
    public List<Customer> getAllCustoner() {
        List<Customer> depositList = customerRepository.findAll();
        LOGGER.debug("getAllDeposit depositList count id  {} ", depositList.size());
        return depositList;
    }

    public Customer getCustomerById(Long id) throws NotFound {
        var customer = customerRepository.findById(id);

        customer.orElseThrow(() -> {
            LOGGER.error("Deposit Not Found");
            return new NotFound("Deposit Not Found");
        });
        return customer.orElse(null);

    }


}
