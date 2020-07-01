package com.example.CafeGoogooExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for order items, with path '/order/items' after application path
 */
@Controller
@RequestMapping(path="/order/items")
public class OrderItemsController {

    public static final Logger LOG = LoggerFactory.getLogger(com.example.CafeGoogooExample.UserController.class);

    //Auto-generated bean to handle orderItemsRepository data
    @Autowired
    private OrderItemsRepository orderItemsRepository;

    /**
     * Post mapping to add an order item to the repository
     * @param orderId order number
     * @param menuId id of the menu item
     * @param quantity quantity user picks
     * @param price price of the menu item
     * @return response entity 'ok' for order item added
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(path="/add") // path name after '/order/items'
    public @ResponseBody ResponseEntity<OrderItems> addOrderItem (@RequestParam String orderId, @RequestParam String menuId,
            @RequestParam Integer quantity, @RequestParam String price) {
        LOG.info("orderId: {}, menuId: {}, quantity: {}, price:{}", orderId,menuId,quantity,price);
        //create new order item based on params
        OrderItems orderItem = new OrderItems();
        orderItem.setOrderId(orderId);
        orderItem.setMenuId(menuId);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(price);
        //save the order item in the repository database, return the response entity
        orderItemsRepository.save(orderItem);
        return ResponseEntity.ok(orderItem);
    }

    /**
     * Get mapping for all items in orders
     * @return all order items stored in the database
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(path="/all") // path name after '/order/items'
    public @ResponseBody ResponseEntity<Iterable<OrderItems>> getOrderItems() {
        // This returns a JSON with the order items
        return ResponseEntity.ok(orderItemsRepository.findAll());
    }

}