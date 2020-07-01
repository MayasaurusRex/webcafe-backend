package com.example.CafeGoogooExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Controller class for menu items, with path '/menu' after application path
 */
@Controller
@RequestMapping(path="/menu")
public class MenuController {

    public static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    //Auto-generated bean to handle menuRepository data
    @Autowired
    private MenuRepository menuRepository;

    //Auto-generated bean to handle menuOpRepository data
    @Autowired
    private MenuOpRepository menuOpRepository;

    /**
     * Post mapping which adds new menu item to repository
     * @param name name of the menu item
     * @param price price of the menu item
     * @return ResponseEntity of saved menu item
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(path="/add") // path name after '/menu'
    public @ResponseBody ResponseEntity<Menu> addNewMenu (@RequestParam String name
            , @RequestParam String price) {
        LOG.info("Name: {}, Price: {}", name, price);

        //create new menu item based on name and price params
        Menu menu = new Menu();
        menu.setName(name);
        menu.setPrice(price);

        //save the item in the repository database, return the response entity
        menuRepository.save(menu);
        return ResponseEntity.ok(menu);
    }

    /**
     * Get mapping for menu items
     * @return all menu items stored in database
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(path="/all") // path name after '/menu'
    public @ResponseBody ResponseEntity<Iterable<Menu>> getMenu() {
        // This returns a JSON with the menu items
        return ResponseEntity.ok(menuRepository.findAll());
    }

    /**
     * Delete mapping for menu item
     * @param id id of the menu item to be deleted
     * @return updated response entity
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @DeleteMapping(path="/{id}") // path name after '/menu'
    @Transactional
    public @ResponseBody ResponseEntity<?> deleteItem(@PathVariable("id") Integer id) {
        // This checks if id exists within the menu items database/repository
        final Optional<Menu> menu = menuRepository.findById(id);
        if (!menu.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        //If exists, deletes the item and the options associated with the item
        menuOpRepository.deleteByMenuid(String.valueOf(menu.get().getId()));
        menuRepository.delete(menu.get());
        return ResponseEntity.ok().build();
    }

    /**
     * Put mapping to update menu items
     * @param menu Menu item to be updated
     * @param id id of the menu item to be updated
     * @return updated response entity
     */
    @CrossOrigin(origins = "http://localhost:8081" )
    @PutMapping(path = "/{id}") // path name after '/menu'
    @Transactional
    public @ResponseBody ResponseEntity<Menu> updateItem(@RequestBody Menu menu, @PathVariable("id") Integer id) {
        // This checks if id exists within the menu items database/repository
        if (!menuRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        //set updated menu item with original id
        menu.setId(id);
        menuRepository.save(menu);
        return ResponseEntity.ok().build();
    }

}

