package com.example.CafeGoogooExample;

import org.springframework.data.repository.CrudRepository;
import com.example.CafeGoogooExample.Menu;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MenuRepository extends CrudRepository<Menu, Integer>  {

}
