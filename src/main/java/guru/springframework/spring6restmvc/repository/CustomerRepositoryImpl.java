package guru.springframework.spring6restmvc.repository;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private Map<UUID, CustomerDTO> customerMap;

    public CustomerRepositoryImpl() {
        this.customerMap = new HashMap<>();
    }

    @Override
    public CustomerDTO store(CustomerDTO customer) {

        CustomerDTO savedCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName(customer.getCustomerName())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public List<CustomerDTO> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public CustomerDTO findById(UUID id) {
        return customerMap.get(id);
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer) {
        CustomerDTO existingCustomer = customerMap.get(customerId);

        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setUpdateDate(LocalDateTime.now());

        customerMap.put(existingCustomer.getId(), existingCustomer);
    }

    @Override
    public void deleteById(UUID id) {
        customerMap.remove(id);
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer) {

        CustomerDTO existingCustomer = customerMap.get(customerId);

        if (StringUtils.hasText(customer.getCustomerName())) {
            existingCustomer.setCustomerName(customer.getCustomerName());
        }

        existingCustomer.setUpdateDate(LocalDateTime.now());

        customerMap.put(existingCustomer.getId(), existingCustomer);
    }

    @PostConstruct
    public void init() {
        CustomerDTO customer1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Homer Simpson")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Marge Simpson")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDTO customer3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Monty Burns")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }
}
