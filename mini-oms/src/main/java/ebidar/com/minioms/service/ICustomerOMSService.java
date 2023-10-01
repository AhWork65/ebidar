package ebidar.com.minioms.service;

import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.exception.NotValidException;
import ebidar.com.minioms.model.Customer;
import ebidar.com.minioms.model.Dto.CustomerDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICustomerOMSService {



    @Transactional(rollbackFor = {Exception.class})
    void createCustoner(CustomerDto input) throws NotValidException;
    @Transactional(rollbackFor = {Exception.class})
    public void deleteCustomer(String exchangeCode) throws NotFound;
    public List<Customer> getAllCustoner();
    public Customer getCustomerById(Long id) throws NotFound;
    public Customer getCustomerByExchangeCode(String exchangCode) throws NotFound;





}
