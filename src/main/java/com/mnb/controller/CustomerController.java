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
     * C1: Tüm müşterileri getirir.
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
     * C2: Filtreye uygun müşterileri getirir.
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
     * C3: İd bilgisi girilen müşteriyi getirir.
     *
     * @param id    Müşteri id bilgisi
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
     * C4: Yeni müşteri oluşturur.
     *
     * @param dto   Yeni müşteri bilgileri
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
     * C5: Id bilgisi girilen müşteriyi günceller.
     *
     * @param id                Müşteri id bilgisi
     * @param customerInDTO     Güncellenecek müşteri bilgisi
     * @return Http 202
     */
    @PutMapping({"/{id}"})
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerInDTO customerInDTO) {

        customerService.updateCustomer(id, customerInDTO);

        return ResponseEntity.accepted().build();
    }

    /**
     * C6: Id bilgisi girilen müşteriyi siler.
     * @param id    Silinecek müşteri id bilgisi
     * @return      Http 204
     */
    @DeleteMapping({"/{id}"})
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * C7:
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
}