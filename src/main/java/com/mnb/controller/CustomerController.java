package com.mnb.controller;

import com.mnb.model.Customer;
import com.mnb.model.dto.CustomerInDTO;
import com.mnb.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
@RequestMapping("/api/customers")
public class CustomerController {

    final CustomerService customerService;

    /**
     * C1: It shows all customers.
     *
     * @return List<Customer>
     */
    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = customerService.getCustomers();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

    /**
     * C2: It shows customers by filter.
     *
     * @param inDTO   Filtre bilgileri
     * @return      List<Customer>
     */
    @GetMapping("/by-filter")
    public ResponseEntity<?> getCustomersByFilter(@RequestBody CustomerInDTO inDTO) {

        List<Customer> customers = customerService.getCustomersByFilter(inDTO.getName(), inDTO.getAge());
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

    /**
     * C3: Shows the customer whose id information is entered.
     *
     * @param id    Customer id
     * @return      Customer
     */
    @GetMapping({"/{id}"})
    public ResponseEntity<?> getCustomerResponseEntity(@PathVariable Integer id) {

        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customer);
    }

    /**
     * C4: Create a new customer.
     *
     * @param dto   New customer information
     * @return      Http 201
     */
    @PostMapping
    public ResponseEntity<?> insertCustomer(@RequestBody CustomerInDTO dto) {

        Customer customer = customerService.insertCustomer(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("CustomerId", customer.getId().toString())
                .build();
    }

    /**
     * C5: Updated the customer whose id information is entered.
     *
     * @param id                Customer id
     * @param customerInDTO     Customer information to be updated
     * @return Http 202
     */
    @PutMapping({"/{id}"})
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerInDTO customerInDTO) {

        customerService.updateCustomer(id, customerInDTO);

        return ResponseEntity.accepted().build();
    }

    /**
     * C6: Deleted the customer whose id information is entered.
     * @param id    Customer information to be deleted
     * @return      Http 204
     */
    @DeleteMapping({"/{id}"})
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * C7: List of customers younger than 25 years old without "most" in their name.
     *
     * @return  List<Customer>
     */
    @GetMapping("/custom-query")
    ResponseEntity<?> getAllWhichAreUnder25AndHasNotEN() {

        List<Customer> customers = customerService.findAllWhichAreUnder25AndHasNotEN();
        if (customers.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(customers);
    }

    /**
     *C8: Shows a list of customers over 30 years old.
     *
     * @return      List<Customer>
     */
    @GetMapping("/filter-age")
    public ResponseEntity<?> findAllCustomersByCustomerAge() {

        List<Customer> customers = customerService.findAllCustomersByCustomerAge();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }
}