package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.order.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderList();
    Order saveOrder(Order order);
}
