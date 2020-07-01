package com.example.CafeGoogooExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for orders, with path '/order' after application path
 */
@Controller
@RequestMapping(path="/order")
public class OrderController {

    public static final Logger LOG = LoggerFactory.getLogger(com.example.CafeGoogooExample.UserController.class);

    //Auto-generated bean to handle orderRepository data
    @Autowired
    private OrderRepository orderRepository;

    /**
     * Post mapping to add an order to repository
     * @param userId id of the user who is placing the order
     * @return response entity 'ok' for added order
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(path="/add") // path name after '/order'
    public @ResponseBody ResponseEntity<UserOrder>  addOrder (@RequestParam String userId) {
        LOG.info("userId: {}", userId);
        //create new order based on userid param
        UserOrder order = new UserOrder();
        order.setUserId(userId);
        //save the order in the repository database, return the response entity
        orderRepository.save(order);
        return ResponseEntity.ok(order);
    }

    /**
     * Get mapping for orders
     * @return all orders stored in the database
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<UserOrder>> getAllOrders() {
        // This returns a JSON with the orders
        return ResponseEntity.ok(orderRepository.findAll());
    }

}