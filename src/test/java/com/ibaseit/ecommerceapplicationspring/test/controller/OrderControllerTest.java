package com.ibaseit.ecommerceapplicationspring.test.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibaseit.ecommerceapplicationspring.controller.OrderController;
import com.ibaseit.ecommerceapplicationspring.dto.OrderSearchDTO;
import com.ibaseit.ecommerceapplicationspring.entity.Order;
import com.ibaseit.ecommerceapplicationspring.entity.Product;
import com.ibaseit.ecommerceapplicationspring.entity.User;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.OrderServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private OrderServiceImpl orderService;

	@InjectMocks
	private OrderController orderController;
	@Autowired
	private OrderSearchDTO criteriaDTO;
	
	
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
	
	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void checkOrderProductsTest() throws Exception {
		// SearchOrder searchOrder = getcheckOrderProducts();
		// Order order = getMockOrder();
		Mockito.when(orderService.checkOrderProducts(3)).thenReturn(getMockOrder());
		List<Order> result = orderController.checkOrderProducts(3);
		Assert.assertEquals(1,result.get(0).getOrderId());

	}
	
	@Test
	public void saveOrderProductTest() throws Exception {
		Mockito.when(orderService.saveOrderProduct(3, null)).thenReturn(1);
		int result = orderController.saveOrderProduct(3);
		Assert.assertEquals(1,result);
	}
	@Test
	public void searchOrderTest() throws Exception {
		Mockito.when(orderService.searchOrder(criteriaDTO)).thenReturn(getMockSearchOrder());
		List<Order> result = orderController.searchOrder(criteriaDTO);
		Assert.assertEquals(1,result.get(0).getOrderId());
	}

	public List<Order> getMockOrder() {
		Order order = new Order();
		order.setOrderId(1);
		return Arrays.asList(order);
	}
	public List<Order> getMockSearchOrder(){
		Order order = new Order();
	
		order.setOrderId(10);
		order.setTotalPrice(5000.00);
		
		order.setCreatedDate(new Date());
	
		
		return Arrays.asList(order);
		
	}
	
	}


