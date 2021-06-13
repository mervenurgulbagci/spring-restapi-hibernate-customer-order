package com.mnb.repository;

import com.mnb.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllCustomersByCustomerAgeIs(Integer customerAge);

    @Query(value =
            "SELECT * " +
            "FROM enoca.customers c " +
            "WHERE c.age > ?1 AND c.is_deleted = FALSE",
            nativeQuery = true)
    List<Customer> findAllCustomersByCustomerAgeGreaterThan(Integer customerAge);

    @Query(value =
            "SELECT * " +
            "FROM enoca.customers c " +
            "WHERE c.name LIKE %?1% AND c.age >= ?2 AND c.is_deleted = FALSE",
            nativeQuery = true)
    List<Customer> findAllByFilter(String name, Integer age);

    //İsminde "en" geçmeyen ve yaşı 25ten küçük olanlar
    @Query(value =
            "SELECT * " +
            "FROM enoca.customers c " +
            "WHERE c.age < 25 AND c.name NOT LIKE '%en%' AND c.is_deleted = FALSE",
            nativeQuery = true)
    List<Customer> findAllWhichAreUnder25AndHasNotEN();

    @Query(value =
            "SELECT * " +
            "FROM enoca.customers c " +
            "WHERE c.age >= 30 AND c.is_deleted = FALSE",
            nativeQuery = true)
    List<Customer> findAllCustomersByCustomerAge(String name, Integer age);
}