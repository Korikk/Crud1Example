package com.Crud1.Crud1Example.service;

import com.Crud1.Crud1Example.advice.CustomerNotFound;
import com.Crud1.Crud1Example.dto.CustomerDto;
import com.Crud1.Crud1Example.entity.Customer;
import com.Crud1.Crud1Example.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;


    public CustomerDto addCustomer(CustomerDto customerDto){
            Customer customer=modelMapper.map(customerDto,Customer.class);
            return modelMapper.map(customerRepository.save(customer),CustomerDto.class);
    }
    public List<CustomerDto> findAllCustomer(){
            List<Customer> customers=customerRepository.findAll();
            List<CustomerDto> dtos=customers.stream().map(customer-> modelMapper.map(customer,CustomerDto.class)).collect(Collectors.toList());
            return dtos;
    }

    public CustomerDto getCustomerById(Long customerId){

                Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
                if(optionalCustomer.isPresent()) {
                    return modelMapper.map(optionalCustomer.get(), CustomerDto.class);
                }

                throw new CustomerNotFound("Customer Not found with id :"+customerId);


    }
    public Boolean deleteCustomerById(Long customerId) {
            Optional<Customer> customer=customerRepository.findById(customerId);
            if (customer.isPresent()){
                customerRepository.deleteById(customerId);
                return true;
            }
            throw new CustomerNotFound("Customer Not found with id :"+customerId);
    }
    public CustomerDto updateCustomer(Long id,CustomerDto customerDto ) {

           Optional<Customer> resultCustomer=customerRepository.findById(id);
           if (resultCustomer.isPresent()) {
               resultCustomer.get().setName(customerDto.getName());
               resultCustomer.get().setSurname(customerDto.getSurname());
               resultCustomer.get().setAge(customerDto.getAge());
               return modelMapper.map(customerRepository.save(resultCustomer.get()), CustomerDto.class);
           }
            throw new IllegalArgumentException("id cannot be null");


    }
    public List<CustomerDto> getCustomerName(String name){
        List<Customer> customers=customerRepository.findAllByName(name);
        if(customers.isEmpty()){
            throw new CustomerNotFound("name not found");
        }
        else {
            List<CustomerDto> dtos = customers.stream().map(customer -> modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
            return dtos;
        }

    }

    public List<CustomerDto> getCustomerSurname(String surname){
        List<Customer> customers=customerRepository.findAllBySurname(surname);
        if(customers.isEmpty()){
            throw new CustomerNotFound("surname not found");
        }
        else{
        List<CustomerDto> dtos =customers.stream().map(customer-> modelMapper.map(customer,CustomerDto.class)).collect(Collectors.toList());
        return dtos;
        }
    }
    public List<CustomerDto> getAllByNameEnding(String ending){
        List<Customer> customers=customerRepository.getAllByNameEndingWith(ending);
        if(customers.isEmpty()){
            throw new CustomerNotFound("Not found");
        }
        else {
            List<CustomerDto> dtos = customers.stream().map(customer -> modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
            return dtos;
        }

    }
    public List<CustomerDto> findByAgeEquals(int age){
        List<Customer> customers=customerRepository.findByAgeEquals(age);
        if (customers.isEmpty()){
            throw new CustomerNotFound("Age not found");
        }
        else {
            List<CustomerDto> dtos =customers.stream().map(customer-> modelMapper.map(customer,CustomerDto.class)).collect(Collectors.toList());
            return dtos;
        }
    }

}
