package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    @Override
    public Item getItem(Long id) {
        return itemRepository.findById(id).get();
    }
}
