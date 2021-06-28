package com.mnb.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author merve
 */
@Table(name = "customers")
@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String customerName;

    @Column(name = "age")
    Integer customerAge;

    @Column(name = "is_deleted")
    Boolean isDeleted = false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerjoin")
    List<Order> orderList = new ArrayList<>();

    public void add(Order tempOrder) {
        if (orderList == null) {
            orderList = new ArrayList<>();
        }
        orderList.add(tempOrder);
    }
}