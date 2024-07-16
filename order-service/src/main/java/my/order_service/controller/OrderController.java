package my.order_service.controller;

import my.item_service.entity.Item;
import my.item_service.service.ItemService;
import lombok.RequiredArgsConstructor;
import my.order_service.service.OrderService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import my.user_service.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ItemService itemService;
    private final UserRepository userRepository;


    @GetMapping("/order")
    public String createForm(Model model) {

        List<Item> items = itemService.findItems();

        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("userId") Integer userId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {

        orderService.order(userId, itemId, count);
        return "redirect:/orders";
    }

//    @GetMapping("/orders")
//    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
//        List<Order> orders = orderService.findOrders(orderSearch);
//        model.addAttribute("orders", orders);
//
//        return "order/orderList";
//    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
