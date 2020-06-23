package com.example.CafeGoogooExample;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@Ignore
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void givenStudent_whenSave_thenGetOk() {
        User user = new User(1,"John","123456","email","72030303","ROLE_USER",1);
        userRepository.save(user);

        Optional<User> user2 = userRepository.findById(1);
        assertEquals("John",user2.get().getUsername());
    }
}
