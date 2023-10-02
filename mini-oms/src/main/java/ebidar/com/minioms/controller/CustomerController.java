package ebidar.com.minioms.controller;



import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.exception.NotValidException;
import ebidar.com.minioms.model.Customer;
import ebidar.com.minioms.model.Dto.CustomerDto;
import ebidar.com.minioms.service.ICustomerOMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 4200)
@RequestMapping("/api/v1/customer/")
public class CustomerController {

    private final ICustomerOMSService customerOMSService ;

    @Autowired
    public CustomerController(ICustomerOMSService customerOMSService) {
        this.customerOMSService = customerOMSService;
    }
    @PostMapping()
    public void createCustomer(@RequestBody CustomerDto input) throws NotValidException {
        customerOMSService.createCustoner(input);
    }

    @DeleteMapping("")
    public void deleteCustomer( @RequestParam(required = false) String exchangeCode) throws  NotFound {
        customerOMSService.deleteCustomer(exchangeCode);
    }

    @GetMapping("all")
    public List<Customer> getAll()  {
        return customerOMSService.getAllCustoner();
    }

    @GetMapping("{id}")
    public Customer getById(@PathVariable Long id) throws NotFound {
        return customerOMSService.getCustomerById(id);
    }
    @GetMapping("by-exchange-code")
    public Customer getByExchangeCode( @RequestParam(required = false) String code) throws NotFound {
        return customerOMSService.getCustomerByExchangeCode(code);
    }

}


