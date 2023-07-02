package com.Crud1.Crud1Example.repository;

import com.Crud1.Crud1Example.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long > {
    List<Customer> findAllByName (String name);
    List<Customer> findAllBySurname(String surname);
    List<Customer> getAllByNameEndingWith(String ending);
    List<Customer> findByAgeEquals(int age);

}
