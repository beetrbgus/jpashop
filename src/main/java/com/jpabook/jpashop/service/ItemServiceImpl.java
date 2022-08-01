package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    @Override
    public Item getItem(Long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public List<Item> findItems() {
        return itemRepository.findAll();
    }
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }
    public Item findOne(Long itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);

        if(!itemOptional.isPresent()) {
            throw new IllegalStateException();
        }
        return itemOptional.get();
    }
}
