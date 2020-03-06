package com.ibaseit.ecommerceapplicationspring.service;

import java.util.List;

import com.ibaseit.ecommerceapplicationspring.entity.Order;

public interface OrderService {

	List<Order> getAllOrders() throws Exception;
	public List<Order> checkOrderProducts(long userId) throws Exception;
	int  saveOrderProduct(long userId, List<Long> cartProds)  throws Exception;
}
