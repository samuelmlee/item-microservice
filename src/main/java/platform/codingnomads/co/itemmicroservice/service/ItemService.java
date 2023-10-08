package platform.codingnomads.co.itemmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import platform.codingnomads.co.itemmicroservice.exception.ItemNotFoundException;
import platform.codingnomads.co.itemmicroservice.model.Item;
import platform.codingnomads.co.itemmicroservice.repository.ItemRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item getItemById(Long id) {
        Optional<Item> optional;
        if ((optional = itemRepository.findById(id)).isEmpty()) {
            return null;
        } else {
            return optional.get();
        }
    }

    public Map<Long, Item>getItemsByIdList(List<Long> itemIds) {
        List<Item> items = itemRepository.findAllById(itemIds);
        // init map with id as key, value all null, value will be updated if found
        Map<Long, Item> itemsMap = items.stream().collect(Collectors.toMap(Item::getItemId, Function.identity()));

        Map<Long, Item> resultMap = new HashMap<>();
        for (Long id : itemIds) {
            resultMap.put(id, itemsMap.get(id));
        }
        return resultMap;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }


    public Item insertNewItem(Item newItem) {
        return itemRepository.save(newItem);
    }

    public Item updateItem(Item updatedItem) {
        Item item = itemRepository.findById(updatedItem.getItemId()).orElse(null);
        if(item == null) {
            throw new ItemNotFoundException("No item found for id :" + updatedItem.getItemId());
        }
        BeanUtils.copyProperties(updatedItem, item);
        return itemRepository.save(item);
    }

    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}