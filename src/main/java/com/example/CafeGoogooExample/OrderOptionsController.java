package com.example.CafeGoogooExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for order options, with path '/order/options' after application path
 */
@Controller
@RequestMapping(path="/order/options")
public class OrderOptionsController {

    public static final Logger LOG = LoggerFactory.getLogger(com.example.CafeGoogooExample.UserController.class);

    //Auto-generated bean to handle orderOptionsRepository data
    @Autowired
    private OrderOptionsRepository orderOptionsRepository;

    /**
     * Post mapping to add an order item to the repository
     * @param orderId order number
     * @param menuOpId id of the menu option
     * @param quantity quantity user picks
     * @param price price of the menu option
     * @return response entity 'ok' for order option added
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<OrderOptions> addOrderOption (@RequestParam String orderId, @RequestParam String menuOpId,
                                                              @RequestParam Integer quantity, @RequestParam String price) {
        LOG.info("orderId: {}, menuId: {}, quantity: {}, price:{}", orderId,menuOpId,quantity,price);
        //create new order option based on params
        OrderOptions orderOption = new OrderOptions();
        orderOption.setOrderId(orderId);
        orderOption.setMenuOpId(menuOpId);
        orderOption.setQuantity(quantity);
        orderOption.setPrice(price);
        //save the order option in the repository database, return the response entity
        orderOptionsRepository.save(orderOption);
        return ResponseEntity.ok(orderOption);
    }

    /**
     * Get mapping for all items in orders
     * @return all order options stored in the database
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<OrderOptions>> getOrderOptions() {
        // This returns a JSON with the order options
        return ResponseEntity.ok(orderOptionsRepository.findAll());
    }

}