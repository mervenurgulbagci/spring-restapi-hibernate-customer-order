package com.mnb.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Details about the contact")
public class Customer {

    @Id
    @ApiModelProperty(notes = "The unique of the contact")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @ApiModelProperty(notes = "The customer's name")
    @Column(name = "name")
    String customerName;

    @ApiModelProperty(notes = "The customer's age")
    @Column(name = "age")
    Integer customerAge;

    @ApiModelProperty(notes = "The customer deleted?")
    @Column(name = "is_deleted")
    Boolean isDeleted = false;

    @ApiModelProperty(notes = "The customer's order list")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerjoin")
    List<Order> orderList = new ArrayList<>();

    public Customer() {
    }

    public void add(Order tempOrder) {
        if (orderList == null) {
            orderList = new ArrayList<>();
        }
        orderList.add(tempOrder);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(Integer customerAge) {
        this.customerAge = customerAge;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}