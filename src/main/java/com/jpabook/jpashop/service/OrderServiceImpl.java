package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.delivery.Delivery;
import com.jpabook.jpashop.domain.delivery.DeliveryStatus;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.domain.order.Order;
import com.jpabook.jpashop.domain.order.OrderItem;
import com.jpabook.jpashop.exception.NotFoundItem;
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
     * 주문 취소
     *
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public void orderCancel(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if(!orderOptional.isPresent()) {
            throw new NotFoundMember("주문 정보를 찾을 수 없습니다.");
        }
        orderOptional.get().cancel(); // update 쿼리 안날려도 변경감지가 되어서 끝
    }

    /**
     * 주문 비즈니스 로직
     * @param memberId
     * @param itemId
     * @param count
     * @return
     */
    @Override
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Item> itemOptional = itemRepository.findById(itemId);

        if(!memberOptional.isPresent()){
            throw new NotFoundMember("회원을 찾을 수 없습니다.");
        }
        if(!itemOptional.isPresent()){
            throw new NotFoundItem("상품을 찾을 수 없습니다.");
        }
        // 배송정보 설정
        Delivery delivery = new Delivery();
        delivery.setAddress(memberOptional.get().getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(itemOptional.get(), itemOptional.get().getPrice(), count);

        // 주문 생성
        /*
             Cascade 로 OrderItem 과 Delivery 가 자동 저장
             Order만 Delivery 사용, Order만 OrderItem만 사용. Persist하는 LifeCycle이 똑같아서 사용
             Entity의 핵심로직을 넣는 것을 Domain Model Pattern 이라고 함
             서비스의 계층은 단순히 Entity에 필요한 요청을 위임하는 역할을 함.
             반대로 Entity에 핵심 로직이 없고 Service 계층에서 비즈니스 로직을 처리하는 것을 트랜잭션 스크립트 패턴이라고 함.
         */
        Order order = Order.createOrder(memberOptional.get(), delivery, orderItem);
        
        // 주문 저장
        orderRepository.save(order);

        return order.getId();

    }
    // 검색
/*
    @Override
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }
*/


}
