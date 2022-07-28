package com.jpabook.jpashop.dto;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.order.Order;
import com.jpabook.jpashop.domain.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class MemberDto {
    private Long id;

    private String userName;

    private Address address;

    private OrderStatus status;

    private List<Order> orders = new ArrayList<>();

    static MemberDto copy(Member member) {
        return new MemberDto(member.getId(), member.getUserName());
    }

    public MemberDto(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
