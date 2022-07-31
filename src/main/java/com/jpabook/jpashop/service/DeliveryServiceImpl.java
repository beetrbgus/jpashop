package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.delivery.Delivery;
import com.jpabook.jpashop.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private DeliveryRepository deliveryRepository;

    @Override
    public Delivery save(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }
}
