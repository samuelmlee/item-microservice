package platform.codingnomads.co.itemmicroservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.codingnomads.co.itemmicroservice.model.BatchItemRequest;
import platform.codingnomads.co.itemmicroservice.model.Item;
import platform.codingnomads.co.itemmicroservice.service.ItemService;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {


    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<?> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @PostMapping("/batch")
    public ResponseEntity<Map<Long, Item>> getItemsByIdList(@RequestBody BatchItemRequest request) {
        Map<Long, Item> items = itemService.getItemsByIdList(request.getItemIds());
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<?> insertNewItem(@RequestBody Item item) {
        try {
            Item newItem = itemService.insertNewItem(item);
            return ResponseEntity.created(URI.create("/item/" + newItem.getItemId())).body(newItem);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<?> updateItem(@RequestBody Item item) {
        try {
            return ResponseEntity.ok(itemService.updateItem(item));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        try {
            itemService.deleteItemById(id);
            return ResponseEntity.ok("item with " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}