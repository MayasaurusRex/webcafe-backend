package com.example.CafeGoogooExample;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for the menu items
 */
public interface MenuRepository extends CrudRepository<Menu, Integer>  {

}
