package com.example.CafeGoogooExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/users") // This means URL's start with /demo (after Application path)
public class UserController {

    public static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //ADD
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<User> addNewUser (@RequestParam String username,
            @RequestParam String password, @RequestParam String email , @RequestParam String phone) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        String encodedPassword  = passwordEncoder.encode(password);

        LOG.info("Name: {}, Email: {}", username, email);
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole("ROLE_USER");
        user.setEnabled(1);
        userRepository.save(user);


        return ResponseEntity.ok(user);
    }

    //GET
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<User>> getAllUsers() {
        // This returns a JSON or XML with the users
        return ResponseEntity.ok(userRepository.findAll());
    }

}
