package com.Crud1.Crud1Example.repository;

import com.Crud1.Crud1Example.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;



    @Test
    public void should_create_new_user() {
        Customer customer = new Customer(1L,"Gokay","Korik",19L);
        customerRepository.save(customer);
        List<Customer> customerBySurname = customerRepository.findAllBySurname("Korik");
        assertThat(customerBySurname).isNotNull();


        System.out.println(customerBySurname.toString());
    }

    @Test
    public void should_find_ageEquals(){
        Customer customer = new Customer(1L,"Gokay","Korik",19L);
        customerRepository.save(customer);
        List<Customer> customerByAge=customerRepository.findByAgeEquals(19);
        assertThat(customerByAge).isNotNull();

        System.out.println(customerByAge.toString());
    }
    @Test
    public void should_find_Endless(){
        Customer customer = new Customer(1L,"Gokay","Korik",19L);
        customerRepository.save(customer);
        List<Customer> customerByEnd=customerRepository.getAllByNameEndingWith("ay");
        assertThat(customerByEnd).isNotNull();

        System.out.println(customerByEnd.toString());
    }
}