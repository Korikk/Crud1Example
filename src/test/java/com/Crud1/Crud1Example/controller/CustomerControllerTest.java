package com.Crud1.Crud1Example.controller;

import com.Crud1.Crud1Example.advice.CustomerNotFound;
import com.Crud1.Crud1Example.dto.CustomerDto;
import com.Crud1.Crud1Example.entity.Customer;
import com.Crud1.Crud1Example.repository.CustomerRepository;
import com.Crud1.Crud1Example.service.CustomerService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @Test

    public void create_shouldCreateSuccessfully(){
        CustomerDto customerDto=new CustomerDto(1L,"Gokay","Korik",19L);

        when(customerService.addCustomer(any())).thenReturn(customerDto);
        ResponseEntity<CustomerDto> result =customerController.customerCreate(customerDto);

       // assertEquals(customerDto,result.getBody());
        assertAll(
                ()-> assertNotNull(customerDto),
                ()->assertEquals(HttpStatus.OK,result.getStatusCode()),
                ()->assertEquals(result.getBody(),customerDto)
        );
    }
    @Test
    public void getAll_shouldReturnCustomerDtoList(){
        CustomerDto customerDto=new CustomerDto();
        List<CustomerDto> excepted= Arrays.asList(customerDto);
        when(customerService.findAllCustomer()).thenReturn(excepted);

        ResponseEntity<List<CustomerDto>> response=customerController.readCustomer();
        List<CustomerDto> actual=response.getBody();
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(HttpStatus.OK,response.getStatusCode()),
                ()-> assertEquals(excepted.size(),actual.size())
        );
    }
    @Test
    public void getByAge_shouldReturCustomerDto(){
        CustomerDto customerDto=new CustomerDto();
        customerDto.setAge(19L);
        List<CustomerDto> excepted=Arrays.asList(customerDto);

        when(customerService.findByAgeEquals(anyInt())).thenReturn(excepted);
        ResponseEntity<List<CustomerDto>> response=customerController.getByAge(19);
        List<CustomerDto> actual=response.getBody();
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(HttpStatus.OK,response.getStatusCode())

        );
    }

    @Test
    public void deleteById_shouldSuccessfully(){
        ResponseEntity<Boolean> response=customerController.deleteCustomer(1L);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
    @Test
    public void getById_shouldReturCustomerDto(){
        CustomerDto excepted=new CustomerDto();
        excepted.setId(1L);

        when(customerService.getCustomerById(anyLong())).thenReturn(excepted);
        ResponseEntity<CustomerDto> response=customerController.getCustomerId(1L);
        CustomerDto actual=response.getBody();
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(HttpStatus.OK,response.getStatusCode()),
                ()-> assertEquals(excepted.getId(),actual.getId())
        );
    }




}
