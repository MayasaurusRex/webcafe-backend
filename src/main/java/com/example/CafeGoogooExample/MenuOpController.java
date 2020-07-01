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
 * Controller class for menu options, with path '/option' after application path
 */
@Controller
@RequestMapping(path="/option")
public class MenuOpController {

    public static final Logger LOG = LoggerFactory.getLogger(MenuOpController.class);

    //Auto-generated bean to handle menuOpRepository data
    @Autowired
    private MenuOpRepository menuOpRepository;

    //Auto-generated bean to handle menuRepository data
    @Autowired
    private MenuRepository menuRepository;

    /**
     * Post mapping to add a new menu option
     * @param menuid id of the menu item associated with the option
     * @param name name of the menu option
     * @param price price of the menu option
     * @return response entity of type 'ok' for the option added to the repository
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(path="/add") // path name after '/option'
    public @ResponseBody ResponseEntity<MenuOp> addNewOption (@RequestParam String menuid , @RequestParam String name
            , @RequestParam String price) {

            //check if the id for the item exists, assigning values tot he new menu option if exists
            Integer m = Integer.valueOf(menuid);
            if( menuRepository.existsById(m)) {
                LOG.info("Name: {}, Price: {}", name, price);
                MenuOp menuOp = new MenuOp();
                menuOp.setMenuid(menuid);
                menuOp.setName(name);
                menuOp.setPrice(price);

                //save the menu option in the repository, return response entity
                menuOpRepository.save(menuOp);
                return ResponseEntity.ok(menuOp);
            }else{
                return (ResponseEntity<MenuOp>) ResponseEntity.badRequest();
            }
    }

    /**
     * Get mapping for menu options
     * @return all menu options stored in the database
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(path="/all") // path name after '/option'
    public @ResponseBody ResponseEntity<Iterable<MenuOp>> getOptions() {
        // This returns a JSON with the menu options
        return ResponseEntity.ok(menuOpRepository.findAll());
    }

    /**
     * Delete mapping to delete a specific menu item
     * @param id id of the menu item to be deleted
     * @return response entity 'ok' for the deleted menu option
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @DeleteMapping(path="/{id}") // path name after '/option'
    public @ResponseBody ResponseEntity<?> deleteOption(@PathVariable("id") Integer id) {
        // check if the option exists, then delete it if exists
        final Optional<MenuOp> menuOp = menuOpRepository.findById(id);
        if (!menuOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        menuOpRepository.delete(menuOp.get());
        return ResponseEntity.ok().build();
    }

    /**
     * Put mapping to update the menu option
     * @param menuOp menu option with the updated values
     * @param id id of the original menu option
     * @return response entity 'ok' for updated menu option
     */
    @CrossOrigin(origins = "http://localhost:8081" )
    @PutMapping(path = "/{id}") // path name after '/option'
    @Transactional
    public @ResponseBody ResponseEntity<MenuOp> updateOption(@RequestBody MenuOp menuOp, @PathVariable("id") Integer id) {
        //check if the menu option already exists
        if (!menuOpRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        //set updated menu option with original id
        menuOp.setId(id);
        menuOpRepository.save(menuOp);
        return ResponseEntity.ok().build();
    }


}

