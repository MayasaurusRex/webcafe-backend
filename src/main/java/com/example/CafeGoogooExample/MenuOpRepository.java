package com.example.CafeGoogooExample;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MenuOpRepository extends CrudRepository<MenuOp, Integer>  {

     public void deleteByMenuid(String menuid);

}
