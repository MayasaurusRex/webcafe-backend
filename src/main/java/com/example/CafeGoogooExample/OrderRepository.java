package com.example.CafeGoogooExample;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for orders, extends the CRUD repository
 */
public interface OrderRepository extends CrudRepository<UserOrder, Integer>  {

}
