package guru.springframework.spring6restmvc.repository;

import guru.springframework.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository {
    void store(Customer customer);
    List<Customer> findAll();
    Customer findById(UUID id);
    void deleteById(UUID id);
}
