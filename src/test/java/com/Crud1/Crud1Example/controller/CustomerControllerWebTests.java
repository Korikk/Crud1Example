package com.Crud1.Crud1Example.controller;


import com.Crud1.Crud1Example.advice.CustomerNotFound;
import com.Crud1.Crud1Example.dto.CustomerDto;
import com.Crud1.Crud1Example.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerWebTests {
    @MockBean
    private CustomerService customerService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void givenCustomers_whenGetAllCustomer_thenReturnJsonArray()
            throws Exception {
       // CustomerDto customerDto=new CustomerDto(1L,"Gokay","Korik",19L);

        when(customerService.findAllCustomer())
                .thenReturn(List.of(new CustomerDto(1L,"Gokay","Korik",19L)));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Gokay"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname").value("Korik"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(19L));
    }


    @Test
    public void shouldReturn404WhenCustomerIsNotFound() throws Exception {
        when(customerService.getCustomerById(1L))
                .thenThrow(new CustomerNotFound("1 is not found"));

        this.mockMvc
                .perform(get("/api/customers/1"))
                .andExpect(status().isNotFound());
    }
    @Test
    public void givenCustomers_whenGetCustomerById_thenReturnJsonArray()
            throws Exception {
        CustomerDto customerDto=new CustomerDto(1L,"Gokay","Korik",19L);
        when(customerService.getCustomerById(1L))
                .thenReturn(customerDto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/customers/{id}",1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Gokay"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Korik"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(19L));
    }
    @Test
    public void givenCustomers_whenGetAgeEquals_thenReturnJsonArray()
            throws Exception {
        // CustomerDto customerDto=new CustomerDto(1L,"Gokay","Korik",19L);

        when(customerService.findByAgeEquals(19))
                .thenReturn(List.of(new CustomerDto(1L,"Gokay","Korik",19L)));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/customers/age").param("age","19"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Gokay"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname").value("Korik"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(19L));
    }

    }

