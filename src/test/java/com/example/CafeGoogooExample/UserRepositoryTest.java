package com.example.CafeGoogooExample;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
     UserRepository userRepository;

    @Test
    public void givenStudent_whenSave_thenGetOk() {
        User user = new User(1,"John","123456","email","72030303","ROLE_USER",1);
        userRepository.save(user);

        Optional<User> user2 = userRepository.findById(1);
        assertEquals("John",user2.get().getUsername());
    }
}
