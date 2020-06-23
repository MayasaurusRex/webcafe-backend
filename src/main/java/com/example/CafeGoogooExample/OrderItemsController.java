package com.example.CafeGoogooExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/order/items") // This means URL's start with /demo (after Application path)
public class OrderItemsController {

    public static final Logger LOG = LoggerFactory.getLogger(com.example.CafeGoogooExample.UserController.class);

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    //ADD
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<OrderItems> addOrderItem (@RequestParam String orderId, @RequestParam String menuId,
            @RequestParam Integer quantity, @RequestParam String price) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        LOG.info("orderId: {}, menuId: {}, quantity: {}, price:{}", orderId,menuId,quantity,price);
        OrderItems orderItem = new OrderItems();
        orderItem.setOrderId(orderId);
        orderItem.setMenuId(menuId);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(price);
        orderItemsRepository.save(orderItem);
        return ResponseEntity.ok(orderItem);
    }

    //GET
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<OrderItems>> getOrderItems() {
        // This returns a JSON or XML with the users
        return ResponseEntity.ok(orderItemsRepository.findAll());
    }

}