package com.example.CafeGoogooExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for users, with path '/users' after application path
 */
@Controller
@RequestMapping(path="/users")
public class UserController {

    public static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    //Auto-generated bean to handle userRepository data
    @Autowired
    private UserRepository userRepository;

    //Auto-generated bean to handle password encoding
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Post mapping to add a user to repository
     * @param username user set username
     * @param password user set password
     * @param email user's email
     * @param phone user's phone
     * @return response entity 'ok' for added user
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(path="/add") // path name after '/users'
    public @ResponseBody ResponseEntity<User> addNewUser (@RequestParam String username,
            @RequestParam String password, @RequestParam String email , @RequestParam String phone) {

        //encodes password from plain text
        String encodedPassword  = passwordEncoder.encode(password);
        LOG.info("Name: {}, Email: {}", username, email);
        //create new user based on params
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole("ROLE_USER");
        user.setEnabled(1);
        //save the user in the repository database, return the response entity
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    /**
     * Get mapping for users
     * @return all users stored in the database
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(path="/all") // path name after '/users'
    public @ResponseBody ResponseEntity<Iterable<User>> getAllUsers() {
        // This returns a JSON with the users
        return ResponseEntity.ok(userRepository.findAll());
    }

}
