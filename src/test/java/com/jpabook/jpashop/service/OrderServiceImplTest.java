package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.domain.order.Order;
import com.jpabook.jpashop.domain.order.OrderStatus;
import com.jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
public class OrderServiceImplTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Test
    public void order() throws Exception{

        //given
        Member member = createMember("회원1", new Address("서울", "도로명주소", "123-1234"));

        Book book = createBook("시골 JPA", 10000, 10);

        entityManager.persist(book);
        int orderCount = 2;
        // When
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        // Then
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        Assertions.assertEquals(OrderStatus.ORDER,orderOptional.get().getStatus());
        assertEquals(1, orderOptional.get().getOrderItems().size()); // 주문 상품 종류수 1개
        assertEquals(10000 * orderCount, orderOptional.get().getTotalPrice()); // 가격 만원이고 2권이니까 20000원
        assertEquals(8, book.getStockQuantity()); // 재고는 8권이 되어야 함
    }
    @Test
    public void orderCancel() throws Exception{
        //given
        Member member = createMember("회원1", new Address("서울", "도로명주소", "123-1234"));

        Book book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //when
        orderService.orderCancel(orderId);

        //then
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        assertEquals(OrderStatus.CANCEL,orderOptional.get().getStatus());

    }
    @Test
    public void noStock() throws Exception{
        //given
        Member member = createMember("회원1", new Address("서울", "도로명주소", "123-1234"));

        Item book = createBook("시골 JPA", 10000, 10);

        int orderCount = 11;

        // JUnit5에서의 예외기대
        IllegalStateException thrown =
                assertThrows(
                        IllegalStateException.class, () -> orderService.order(member.getId(), book.getId(), orderCount)
                );
        assertEquals("need more stock", thrown.getMessage());
    }
    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);

        entityManager.persist(book);
        return book;
    }

    private Member createMember(String userName, Address address) {
        Member member = new Member();
        member.setUserName(userName);
        member.setAddress(address);

        entityManager.persist(member);
        return member;
    }


}