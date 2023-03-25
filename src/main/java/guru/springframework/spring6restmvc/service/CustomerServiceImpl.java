package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> listCustomers() {

        log.debug("Get all customers in service");

        return customerRepository.findAll();
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {

        log.debug("Get Customer by Id - in service. Id: " + id.toString());

        return Optional.ofNullable(customerRepository.findById(id));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {

        log.debug("Save new customer to repository: " + customer.getCustomerName());

        return customerRepository.store(customer);
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer) {

        log.debug("Update existing customer with Id: " + customerId);

        customerRepository.updateCustomerById(customerId, customer);
    }

    @Override
    public void deleteById(UUID customerId) {

        log.debug("Delete existing customer with Id: " + customerId);

        customerRepository.deleteById(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer) {

        log.debug("Patch existing customer with Id: " + customerId);

        customerRepository.patchCustomerById(customerId, customer);
    }
}
