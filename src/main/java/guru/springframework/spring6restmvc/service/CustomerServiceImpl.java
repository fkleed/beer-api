package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> listCustomers() {

        log.debug("Get all customers in service");

        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(UUID id) {

        log.debug("Get Customer by Id - in service. Id: " + id.toString());

        return customerRepository.findById(id);
    }
}
