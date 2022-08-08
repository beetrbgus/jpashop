package com.jpabook.jpashop.controller;

import com.jpabook.jpashop.domain.order.Order;
import com.jpabook.jpashop.dto.CommonResponse;
import com.jpabook.jpashop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

}
