package com.jpabook.jpashop.api;

import com.jpabook.jpashop.domain.order.Order;
import com.jpabook.jpashop.dto.CommonResponse;
import com.jpabook.jpashop.dto.OrderSearch;
import com.jpabook.jpashop.repository.OrderRepository;
import com.jpabook.jpashop.repository.OrderRepositoryImpl;
import com.jpabook.jpashop.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * xToOne ( ManyToOne, OneToOne )
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderApiController {
    private final OrderService orderService;
    private final OrderRepositoryImpl orderRepository;

    @GetMapping("/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> orderList = orderRepository.findAllByString(new OrderSearch());

        return orderList;
    }
}
