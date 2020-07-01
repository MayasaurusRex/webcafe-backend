package com.example.CafeGoogooExample;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for the menu options
 */
public interface MenuOpRepository extends CrudRepository<MenuOp, Integer>  {

     //method to delete menu options by their menuid property
     public void deleteByMenuid(String menuid);

}
