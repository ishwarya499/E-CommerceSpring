package com.ibaseit.ecommerceapplicationspring.service;

import java.util.List;
import java.util.Map;

import com.ibaseit.ecommerceapplicationspring.dto.OrderSearchDTO;
import com.ibaseit.ecommerceapplicationspring.entity.Order;

public interface OrderService {

	List<Order> getAllOrders() throws Exception;
	public List<Order> checkOrderProducts(long userId) throws Exception;
	int  saveOrderProduct(long userId, List<Long> cartProds)  throws Exception;
	public List<Order> searchOrder(OrderSearchDTO criteriaDTO);

	
}
