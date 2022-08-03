package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.item.Item;

import java.util.List;

public interface ItemService {
    Item getItem(Long id);
    List<Item> findItems();
}
