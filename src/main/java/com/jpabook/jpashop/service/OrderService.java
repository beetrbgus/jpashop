package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.order.Order;
import com.jpabook.jpashop.dto.OrderSearch;

import java.util.List;

public interface OrderService {
    List<Order> getOrderList();
    Order saveOrder(Order order);
    Long order(Long memberId, Long itemId, int count);
    void orderCancel(Long orderId);

    List<Order> findOrders(OrderSearch orderSearch);
}
