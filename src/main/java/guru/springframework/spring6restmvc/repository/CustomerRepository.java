package guru.springframework.spring6restmvc.repository;

import guru.springframework.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository {
    CustomerDTO store(CustomerDTO customer);

    List<CustomerDTO> findAll();

    CustomerDTO findById(UUID id);

    void updateCustomerById(UUID customerId, CustomerDTO customer);

    void deleteById(UUID id);

    void patchCustomerById(UUID customerId, CustomerDTO customer);
}
