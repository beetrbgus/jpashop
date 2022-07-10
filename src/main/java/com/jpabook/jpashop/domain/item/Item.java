package com.jpabook.jpashop.domain.item;

import com.jpabook.jpashop.domain.category.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일 테이블 전략 ( 한 테이블에 다 떄려넣음 )
@DiscriminatorColumn(name = "dtype")
@Entity @Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    // 다대다는 실무에서는 잘 안 씀.
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
