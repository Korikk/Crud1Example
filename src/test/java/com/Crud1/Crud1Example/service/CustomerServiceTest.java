package com.Crud1.Crud1Example.service;

import com.Crud1.Crud1Example.advice.CustomerNotFound;
import com.Crud1.Crud1Example.dto.CustomerDto;
import com.Crud1.Crud1Example.entity.Customer;
import com.Crud1.Crud1Example.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.Mockito;


import org.modelmapper.ModelMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {
    private CustomerService customerService;
    private ModelMapper modelMapper;
    private CustomerRepository customerRepository;
    @BeforeEach
    public void setUp(){
        modelMapper=Mockito.mock(ModelMapper.class);
        customerRepository=Mockito.mock(CustomerRepository.class);
        customerService=new CustomerService(customerRepository,modelMapper);
    }


   @Test
    public void testGetAllCustomers(){
       List<Customer> list=new ArrayList<>();
       Customer customer1=new Customer(1L,"Gokay","Korik",19L);
       Customer customer2=new Customer(2L,"Gokay","Korik",19L);
       Customer customer3=new Customer(3L,"Gokay","Korik",19L);

       list.add(customer1);
       list.add(customer2);
       list.add(customer3);

       when(customerRepository.findAll()).thenReturn(list);
       List<CustomerDto> customerDtos=customerService.findAllCustomer();
       assertEquals(3,customerDtos.size());

   }

   @Test
   public void whenCreateCustomer(){
        ModelMapper modelMapper1=new ModelMapper();
        CustomerDto customerDto = new CustomerDto(1L,"Gokay","Korik",19L);
        Customer customer=modelMapper1.map(customerDto,Customer.class);

        //Customer customer=modelMapper.map(customerDto,Customer.class);
        Mockito.when(modelMapper.map(customerRepository.save(customer),CustomerDto.class)).thenReturn(customerDto);
        CustomerDto result=customerService.addCustomer(customerDto);
        assertEquals(result,customerDto);
   }





   @Test
    public void whengetCustomerById(){
       Customer customer = new Customer(1L,"Gokay","Korik",19L);
       CustomerDto customerDto = new CustomerDto(1L,"Gokay","Korik",19L);

       Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
       Mockito.when(modelMapper.map(customer,CustomerDto.class)).thenReturn(customerDto);
       CustomerDto actualPostResponse = customerService.getCustomerById(customer.getId());

       Assertions.assertThat(actualPostResponse.getId()).isEqualTo(customerDto.getId());
       Assertions.assertThat(actualPostResponse.getName()).isEqualTo(customerDto.getName());

   }
   @Test
    public void getById_itShouldThrowNotFound(){
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception=assertThrows(CustomerNotFound.class,()->{
            customerService.getCustomerById(1L);
       });

        assertTrue(exception.getMessage().contains("Customer Not found with id :"));
   }
    @Test
    public void getByIdDelete_itShouldThrowNotFound(){
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception=assertThrows(CustomerNotFound.class,()->{
            customerService.deleteCustomerById(1L);
        });

        assertTrue(exception.getMessage().contains("Customer Not found with id :"));

    }

    @Test
    public void whenCustomerGetByEndless(){
        List<Customer> list=new ArrayList<>();
        Customer customer1=new Customer(1L,"Gokay","Korik",19L);
        Customer customer2=new Customer(2L,"Gokay","Korik",19L);
        Customer customer3=new Customer(3L,"Gokay","Korik",19L);

        list.add(customer1);
        list.add(customer2);
        list.add(customer3);

        when(customerRepository.findAllBySurname("Korik")).thenReturn(list);
        List<CustomerDto> customerDtos=customerService.getCustomerSurname("Korik");
        assertEquals(3,customerDtos.size());
    }
    @Test
    public void whenCustomerSurnameThrowsException(){
        List<Customer> customer = new ArrayList<>();
        when(customerRepository.findAllBySurname(any())).thenReturn(customer);

        Exception exception=assertThrows(CustomerNotFound.class,()->{
            customerService.getCustomerSurname("Korik");
        });

        assertTrue(exception.getMessage().contains("surname not found"));

    }


}
