package guru.springframework.spring6restmvc.repository;

import guru.springframework.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerRepositoryOld {
    CustomerDTO store(CustomerDTO customer);

    List<CustomerDTO> findAll();

    CustomerDTO findById(UUID id);

    CustomerDTO updateCustomerById(UUID customerId, CustomerDTO customer);

    Boolean deleteById(UUID id);

    CustomerDTO patchCustomerById(UUID customerId, CustomerDTO customer);
}
