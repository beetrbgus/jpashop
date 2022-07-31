package com.jpabook.jpashop.domain.delivery;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;
    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //  READY, COMP
}
