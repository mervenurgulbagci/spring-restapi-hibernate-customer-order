package com.mnb.repository;

import com.mnb.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllCustomersByCustomerAgeIs(Integer customerAge);

    //Retrieve customers whose age is greater than the entered value from the Customers table.
    @Query(value =
            "SELECT * " +
            "FROM enoca.customers c " +
            "WHERE c.age > ?1 AND c.is_deleted = FALSE",
            nativeQuery = true)
    List<Customer> findAllCustomersByCustomerAgeGreaterThan(Integer customerAge);

    //Brings the customers who match the age value entered from the Customers table and match the entered name pattern.
    @Query(value =
            "SELECT * " +
            "FROM enoca.customers c " +
            "WHERE c.name LIKE %?1% AND c.age >= ?2 AND c.is_deleted = FALSE",
            nativeQuery = true)
    List<Customer> findAllByFilter(String name, Integer age);

    //Brings customers under the age of 25 who do not have "en" in their name.
    @Query(value =
            "SELECT * " +
            "FROM enoca.customers c " +
            "WHERE c.age < 25 AND c.name NOT LIKE '%en%' AND c.is_deleted = FALSE",
            nativeQuery = true)
    List<Customer> findAllWhichAreUnder25AndHasNotEN();

    //Brings customers over 30 years old.
    @Query(value =
            "SELECT * " +
            "FROM enoca.customers c " +
            "WHERE c.age >= 30 AND c.is_deleted = FALSE",
            nativeQuery = true)
    List<Customer> findAllCustomersByCustomerAge();
}