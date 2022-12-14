package com.CarRentalAgency.controller;


import com.CarRentalAgency.entity.Customer;
import com.CarRentalAgency.services.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @GetMapping("/list")
    public List<Customer> list() {
        List<Customer> listCustomers = customerService.findAllCustomers();
        if (listCustomers.isEmpty()) throw new NoSuchElementException("THERE IS NO CUSTOMERS IN THE DATA BASE.");
        return listCustomers;
    }


    @GetMapping("/list/id/{id}")
    public Customer fetchCustomerByID(@PathVariable("id") Long id) throws NoSuchElementException {
        return customerService.findCustomerById(id);
    }

    @GetMapping("/list/email/{email}")
    public Customer fetchCustomerByEmail(@PathVariable("email") String email) throws NoSuchElementException {
        return  customerService.findCustomerByEmail(email);
    }

    @GetMapping("/list/name/{name}")
    public List<Customer> fetchCustomerByName(@PathVariable("name") String customerName) throws NoSuchElementException {
        return customerService.findCustomerByFirstNameIgnoreCase(customerName);
    }

    @PostMapping("/save")
    public Customer saveCustomer(@RequestBody @Valid Customer customer) throws NoSuchElementException {
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomerById(@PathVariable Long id) throws NoSuchElementException {
        customerService.deleteCustomerById(id);
        return "Deleted Successfully ;) ";
    }

    @PutMapping("/update/{id}")
    public Customer updateCustomerById(@PathVariable Long id, @RequestBody @Valid Customer customer) throws NoSuchElementException {
        return customerService.updateCustomer(id, customer);
    }

}
