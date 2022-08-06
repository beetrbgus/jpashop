package com.jpabook.jpashop.dto;

import com.jpabook.jpashop.domain.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;//주문 상태 [ORDER, CANCEL]

}
