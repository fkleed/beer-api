package guru.springframework.spring6restmvc.repository;

import guru.springframework.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository {
    Customer store(Customer customer);

    List<Customer> findAll();

    Customer findById(UUID id);

    void updateCustomerById(UUID customerId, Customer customer);

    void deleteById(UUID id);

    void patchCustomerById(UUID customerId, Customer customer);
}
