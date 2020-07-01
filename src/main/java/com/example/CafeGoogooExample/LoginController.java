package com.example.CafeGoogooExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * Controller class for the login, with path '/role' after application path
 */
@Controller
@RequestMapping(path="/role")
public class LoginController {

    //Auto-generated bean to handle userRepository data
    @Autowired
    private UserRepository userRepository;

    //Import SpringSecurityConfig class to handle security data
    private SpringSecurityConfig springSecurityConfig;

    /**
     * Get Mapping that takes in entered username and password and checks if the pair exists
     * in the userRepository.
     * @param username string username that should match stored username value
     * @param password non-encrypted password to be compared to the stored encrypted password
     * @return string value of role -> "ROLE_ADMIN","ROLE_USER", or "anon"
     */
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping
    public @ResponseBody String findUser(@RequestParam String username, @RequestParam String password) {

        //check if username exists in repository
        final Optional<User> user = userRepository.findByUsername(username);

        //checks the plain-text password with stored encrypted password, returning role if matches
        // if the pair does not match, returns anon user
        if (user.isPresent() && BCrypt.checkpw(password,user.get().getPassword())) {
            return user.get().getRole() +","+ String.valueOf(user.get().getId());
        }else {
            return "anon";
        }
    }

}
