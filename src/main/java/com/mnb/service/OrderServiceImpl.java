package com.mnb.service;

import com.mnb.model.Order;
import com.mnb.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;

    /**
     *  Tüm sipariş listesini getirir.
     * @return     Sipariş listesi
     */

    @Override
    public List<Order> getOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to get orders!");
        }
    }
}