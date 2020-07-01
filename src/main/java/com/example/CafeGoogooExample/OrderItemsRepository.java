package com.example.CafeGoogooExample;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for order items
 */
public interface OrderItemsRepository extends CrudRepository<OrderItems, Integer>  {

}
