package my.item_service.controller;

import my.item_service.entity.Item;
import my.item_service.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("form", new ItemForm());
        return "";
    }

    @PostMapping("/create")
    public String create(ItemForm form) {
        Item item = new Item();
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        item.setStockQuantity(form.getStockQuantity());

        itemService.saveItem(item);
        return "";
    }

    @GetMapping("/itemList")
    public List<Item> list() {
        return itemService.findItems();
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getItem(@PathVariable Long itemId) {
        Item item = itemService.findOne(itemId);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
