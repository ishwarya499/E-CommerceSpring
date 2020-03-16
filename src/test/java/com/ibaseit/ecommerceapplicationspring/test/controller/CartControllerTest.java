package com.ibaseit.ecommerceapplicationspring.test.controller;

import java.util.Arrays;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibaseit.ecommerceapplicationspring.controller.CartController;
import com.ibaseit.ecommerceapplicationspring.entity.Cart;
import com.ibaseit.ecommerceapplicationspring.entity.User;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.CartServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {
	@Mock
	private CartServiceImpl cartService;

	@InjectMocks
	private CartController cartController;

	Cart cart;
	@Before
	public void init() {
		cart = getMockCart();
	
		MockitoAnnotations.initMocks(this);
	}

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void saveCartProductTest() throws Exception {

		Mockito.when(cartService.saveCartProduct(26, 23)).thenReturn(0);

		int result = cartController.saveCartProduct(26, 23);
		Assert.assertEquals(0, result);
	}

	@Test
	public void deleteCartTest() throws Exception {
		Mockito.when(cartService.deleteCart(26, 23)).thenReturn((long) 0);
		long result = cartController.deleteCart(26, 23);
		Assert.assertEquals(0, result);
	}

	@Test
	public void getAllCartsTest() throws Exception {
		Mockito.when(cartService.getAllCarts()).thenReturn(getAllCartIds());
		List<Cart> result = cartController.getAllCarts();
		Assert.assertEquals(getAllCartIds(), result);
	}

	@Test
	public void checkCartProductsTest() throws Exception {

		Mockito.when(cartService.checkCartProducts(26)).thenReturn(getMockCheckCart());
		double result =  cartController.checkCartProducts(26);
		Assert.assertEquals("price", 5000.00, 5000.00, result);

	}

	private List<Cart> getAllCartIds() {
		User user = new User();
		cart.setProductId(23);
		user.setUserId(26);
		return Arrays.asList(cart);
	}

	public Cart getMockCart() {
		Cart cart = new Cart();
		cart.setNameofProduct("bottle");
		cart.setProductId(23);
		cart.setTypeofProduct("store");
		cart.setQuantity(1);
		cart.setPrice(100);

		return cart;
	}

	public Double getMockCheckCart() {
		User user = new User();
		user.setUserId(26);
		return 5000.00;

	}
}
