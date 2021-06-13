package com.mnb.service;

import com.mnb.model.Customer;
import com.mnb.model.dto.CustomerInDTO;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    Customer getCustomerById(Integer id);

    Customer insertCustomer(CustomerInDTO dto);

    void updateCustomer(Integer id, CustomerInDTO customer);

    void deleteCustomer(Integer id);

    List<Customer> getCustomersByFilter(String name, Integer age);

    List<Customer> findAllWhichAreUnder25AndHasNotEN();

}