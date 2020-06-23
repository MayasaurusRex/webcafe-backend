package com.example.CafeGoogooExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/order/options") // This means URL's start with /demo (after Application path)
public class OrderOptionsController {

    public static final Logger LOG = LoggerFactory.getLogger(com.example.CafeGoogooExample.UserController.class);

    @Autowired
    private OrderOptionsRepository orderOptionsRepository;

    //ADD
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<OrderOptions> addOrderOption (@RequestParam String orderId, @RequestParam String menuOpId,
                                                              @RequestParam Integer quantity, @RequestParam String price) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        LOG.info("orderId: {}, menuId: {}, quantity: {}, price:{}", orderId,menuOpId,quantity,price);
        OrderOptions orderOption = new OrderOptions();
        orderOption.setOrderId(orderId);
        orderOption.setMenuOpId(menuOpId);
        orderOption.setQuantity(quantity);
        orderOption.setPrice(price);
        orderOptionsRepository.save(orderOption);
        return ResponseEntity.ok(orderOption);
    }

    //GET
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<OrderOptions>> getOrderOptions() {
        // This returns a JSON or XML with the users
        return ResponseEntity.ok(orderOptionsRepository.findAll());
    }

}