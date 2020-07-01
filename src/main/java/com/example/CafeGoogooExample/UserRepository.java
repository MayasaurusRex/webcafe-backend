package com.example.CafeGoogooExample;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository for the users
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    //method to find users by their username property
    public Optional<User> findByUsername(String username);

}
