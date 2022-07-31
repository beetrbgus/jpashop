package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.delivery.Delivery;
import com.jpabook.jpashop.domain.delivery.DeliveryStatus;
import com.jpabook.jpashop.domain.order.Order;
import com.jpabook.jpashop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private DeliveryService deliveryService;
    @Override
    public List<Order> getOrderList() {
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        //주문 저장
        Order saveOrder = orderRepository.save(order);
        Member orderMember = saveOrder.getMember();

        //주문과 동시에 배송 준비 상태로 배송 테이블 저장
        Delivery delivery = new Delivery();
        delivery.setAddress(orderMember.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        
        Delivery savedDelivery = deliveryService.save(delivery);
        saveOrder.setDelivery(savedDelivery);
        // JPA 변경감지로 저장
        return saveOrder;
    }
}
