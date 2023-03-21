package guru.springframework.spring6restmvc.repository;

import guru.springframework.spring6restmvc.model.Customer;
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

    private Map<UUID, Customer> customerMap;

    public CustomerRepositoryImpl() {
        this.customerMap = new HashMap<>();
    }

    @Override
    public Customer store(Customer customer) {

        Customer savedCustomer = Customer.builder()
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
    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer findById(UUID id) {
        return customerMap.get(id);
    }

    @Override
    public void updateCustomerById(UUID customerId, Customer customer) {
        Customer existingCustomer = customerMap.get(customerId);

        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setUpdateDate(LocalDateTime.now());

        customerMap.put(existingCustomer.getId(), existingCustomer);
    }

    @Override
    public void deleteById(UUID id) {
        customerMap.remove(id);
    }

    @Override
    public void patchCustomerById(UUID customerId, Customer customer) {

        Customer existingCustomer = customerMap.get(customerId);

        if (StringUtils.hasText(customer.getCustomerName())) {
            existingCustomer.setCustomerName(customer.getCustomerName());
        }

        existingCustomer.setUpdateDate(LocalDateTime.now());

        customerMap.put(existingCustomer.getId(), existingCustomer);
    }

    @PostConstruct
    public void init() {
        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Homer Simpson")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Marge Simpson")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Customer customer3 = Customer.builder()
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
