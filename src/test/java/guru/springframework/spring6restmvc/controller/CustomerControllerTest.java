package guru.springframework.spring6restmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    private static CustomerDTO customer1;
    private static CustomerDTO customer2;
    private static CustomerDTO customer3;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    CustomerService customerService;
    private Map<UUID, CustomerDTO> customerMap = new HashMap<>();

    @BeforeAll
    static void setup() {
        customer1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Homer Simpson")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customer2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Marge Simpson")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customer3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Monty Burns")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }

    @BeforeEach
    void init() {
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }

    @AfterEach
    void teardown() {
        customerMap.clear();
    }

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<CustomerDTO> customerArgumentCaptor;

    @Test
    void updateCustomerPatchById() throws Exception {
        Map<String, Object> patchCustomerMap = new HashMap<>();
        patchCustomerMap.put("customerName", "New Name");

        mockMvc.perform(patch(CustomerController.CUSTOMER_PATH_ID, customer1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patchCustomerMap)))
                .andExpect(status().isNoContent());

        verify(customerService).patchCustomerById(uuidArgumentCaptor.capture(), customerArgumentCaptor.capture());

        assertThat(customer1.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(patchCustomerMap.get("customerName")).isEqualTo(customerArgumentCaptor.getValue().getCustomerName());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete(CustomerController.CUSTOMER_PATH_ID, customer3.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(customerService).deleteById(uuidArgumentCaptor.capture());

        assertThat(customer3.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void updateById() throws Exception {
        mockMvc.perform(put(CustomerController.CUSTOMER_PATH_ID, customer2.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer2)))
                .andExpect(status().isNoContent());

        verify(customerService).updateCustomerById(uuidArgumentCaptor.capture(), any(CustomerDTO.class));

        assertThat(customer2.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Disabled
    @Test
    void createNewCustomer() throws Exception {
        CustomerDTO customerToCreate = customer1;
        customerToCreate.setId(UUID.randomUUID());
        customerToCreate.setCustomerName("TestCustomer");

        given(customerService.saveNewCustomer(any(CustomerDTO.class))).willReturn(customerToCreate);

        mockMvc.perform(post(CustomerController.CUSTOMER_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerToCreate)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

    @Test
    void listCustomers() throws Exception {
        given(customerService.listCustomers())
                .willReturn(new ArrayList<>(customerMap.values()));

        mockMvc.perform(get(CustomerController.CUSTOMER_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void getCustomerByIdNotFound() throws Exception {

        given(customerService.getCustomerById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get(CustomerController.CUSTOMER_PATH_ID, UUID.randomUUID())).andExpect(status().isNotFound());
    }

    @Test
    void getCustomerById() throws Exception {
        given(customerService.getCustomerById(customer1.getId()))
                .willReturn(Optional.of(customer1));

        mockMvc.perform(get(CustomerController.CUSTOMER_PATH_ID, customer1.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(customer1.getId().toString())))
                .andExpect(jsonPath("$.customerName", is(customer1.getCustomerName())));
    }
}