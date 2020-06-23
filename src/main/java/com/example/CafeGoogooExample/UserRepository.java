package com.example.CafeGoogooExample;

import org.springframework.data.repository.CrudRepository;

import com.example.CafeGoogooExample.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public Optional<User> findByUsername(String username);

}
