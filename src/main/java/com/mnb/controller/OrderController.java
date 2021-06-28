package com.mnb.controller;

import com.mnb.model.Order;
import com.mnb.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    final OrderService orderService;

    /**
     * O1: Shows all orders
     *
     * @return Order list
     */
    @GetMapping
    public ResponseEntity<?> getAllCustomers() {

        List<Order> orderList = orderService.getOrders();
        if (orderList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orderList);
    }
}