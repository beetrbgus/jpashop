package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.delivery.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
}
