package com.ibaseit.ecommerceapplicationspring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibaseit.ecommerceapplicationspring.entity.Cart;
import com.ibaseit.ecommerceapplicationspring.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
	public List<Order> findByUser_UserId(long userId);

}