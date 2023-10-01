package ebidar.com.minioms.service;


import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.exception.NotValidException;
import ebidar.com.minioms.model.Customer;
import ebidar.com.minioms.model.Dto.CustomerDto;
import ebidar.com.minioms.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerOMSServiceImpl implements ICustomerOMSService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerOMSServiceImpl.class);

    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CustomerOMSServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public boolean validateCustomer(CustomerDto customer) {
        if (customer == null) {
            return false;
        }
        if (customer.getName() == null) {
            return false;
        }
        if (customer.getExchangeCode() == null) {
            return false;
        }
        if (customer.getLastName() == null) {
            return false;
        }
        return true;
    }


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void createCustoner(CustomerDto input) throws NotValidException {
        if (!validateCustomer(input)) {
            LOGGER.error("createCustomer : Customer Parameter Not valid ");
            throw new NotValidException("createCustomer Wrong parameter posted");
        }
        customerRepository.save(modelMapper.map(input, Customer.class));
    }


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteCustomer(String exchangeCode) throws NotFound {
        var customer = customerRepository.findCustomerByExchangeCode(exchangeCode);
        if (customer == null) {
            LOGGER.error("Customer Not Found");
            throw new NotFound("Customer Not Found");
        }
        customerRepository.delete(customer);

    }

    @Override
    public List<Customer> getAllCustoner() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }


    @Override
    public Customer getCustomerById(Long id) throws NotFound {
        var customer = customerRepository.findById(id);

        customer.orElseThrow(() -> {
            LOGGER.error("Customer Not Found");
            return new NotFound("Customer Not Found");
        });
        return customer.orElse(null);

    }

    @Override
    public Customer getCustomerByExchangeCode(String exchangCode) throws NotFound {
        var customer = customerRepository.findCustomerByExchangeCode(exchangCode);
        if (customer == null) {
            LOGGER.error("Customer Not Found");
            throw new NotFound("Customer Not Found");
        }
        return customer;
    }
}
