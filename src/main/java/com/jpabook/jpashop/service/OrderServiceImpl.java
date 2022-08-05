package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.delivery.Delivery;
import com.jpabook.jpashop.domain.delivery.DeliveryStatus;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.domain.order.Order;
import com.jpabook.jpashop.domain.order.OrderItem;
import com.jpabook.jpashop.exception.NotFoundMember;
import com.jpabook.jpashop.repository.ItemRepository;
import com.jpabook.jpashop.repository.MemberRepository;
import com.jpabook.jpashop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final DeliveryService deliveryService;

    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;
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

    /**
     * 주문 비즈니스 로직
     * @param memberId
     * @param itemId
     * @param count
     * @return
     */
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Item> itemOptional = itemRepository.findById(itemId);

        if(!memberOptional.isPresent()){
            throw new NotFoundMember("회원을 찾을 수 없습니다.");
        }
        if(!itemOptional.isPresent()){
            throw new NotFoundMember("상품을 찾을 수 없습니다.");
        }
        // 배송정보 설정
        Delivery delivery = new Delivery();
        delivery.setAddress(memberOptional.get().getAddress());

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(itemOptional.get(), itemOptional.get().getPrice(), count);

        // 주문 생성
        // Cascade 로 OrderItem 과 Delivery 가 자동 저장
        // Order만 Delivery 사용, Order만 OrderItem만 사용. Persist하는 LifeCycle이 똑같아서 사용
        Order order = Order.createOrder(memberOptional.get(), delivery, orderItem);
        
        // 주문 저장
        orderRepository.save(order);

        return order.getId();

    }
}
