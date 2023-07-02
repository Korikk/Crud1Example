package com.Crud1.Crud1Example.controller;

import com.Crud1.Crud1Example.dto.CustomerDto;
import com.Crud1.Crud1Example.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;


    @RequestMapping(value = "/customers",method = RequestMethod.POST)
    public ResponseEntity<CustomerDto> customerCreate(@RequestBody CustomerDto customerDto){
        CustomerDto customerResult=customerService.addCustomer(customerDto);
        return ResponseEntity.ok(customerResult);
    }

    @RequestMapping(value = "/customers",method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDto>> readCustomer(){

        List<CustomerDto> allCustomer= customerService.findAllCustomer();
        return ResponseEntity.ok(allCustomer);
    }

    @RequestMapping(value = "/customers/{id}",method = RequestMethod.GET)
    public ResponseEntity<CustomerDto> getCustomerId (@PathVariable("id")Long id) {

                CustomerDto customer=customerService.getCustomerById(id);
                return ResponseEntity.ok(customer);
    }


    @RequestMapping(value = "/customers/{id}",method = RequestMethod.PUT)
    public ResponseEntity<CustomerDto> updateCustomers (@PathVariable (value = "id")Long id,@RequestBody CustomerDto customerDto)  {
            CustomerDto customer = customerService.updateCustomer(id,customerDto);
            return ResponseEntity.ok(customer);
    }


    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    //@DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<Boolean>  deleteCustomer(@PathVariable("id")Long id) {
        Boolean status =customerService.deleteCustomerById(id);
        return ResponseEntity.ok(status);

    }
    @RequestMapping(value = "/customers/name",method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDto>> getCustomerByName(@RequestParam String name){
        List<CustomerDto> allCustomer = customerService.getCustomerName(name);


            return ResponseEntity.ok(allCustomer);

    }
    @RequestMapping(value = "/customers/surname",method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDto>> getCustomerBysurname(@RequestParam String surname){

        List<CustomerDto> allCustomer= customerService.getCustomerSurname(surname);


            return ResponseEntity.ok(allCustomer);


    }
    @RequestMapping(value = "/customers/ending",method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDto>> getNameEnding(@RequestParam String ending){

        List<CustomerDto> allCustomer= customerService.getAllByNameEnding(ending);
            return ResponseEntity.ok(allCustomer);


    }
    @RequestMapping(value = "/customers/age",method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDto>> getByAge(@RequestParam int age){

        List<CustomerDto> allCustomer= customerService.findByAgeEquals(age);
        return ResponseEntity.ok(allCustomer);


    }



}
