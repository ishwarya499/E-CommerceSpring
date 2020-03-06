package com.ibaseit.ecommerceapplicationspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibaseit.ecommerceapplicationspring.entity.Order;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.OrderServiceImpl;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
	@Autowired
	private OrderServiceImpl orderService;

	@GetMapping(value = "/checkOrderProducts/{userId}")
	public List<Order> checkOrderProducts(@PathVariable("userId") long userId) throws Exception {
		return orderService.checkOrderProducts(userId);

	}
	@GetMapping(value = "/saveOrderProduct/{userId}")
	public int saveOrderProduct(@PathVariable("userId") long userId)
			throws Exception {
		return orderService.saveOrderProduct(userId, null);
	}
}
