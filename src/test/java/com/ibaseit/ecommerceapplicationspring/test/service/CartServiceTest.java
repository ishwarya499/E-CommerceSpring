package com.ibaseit.ecommerceapplicationspring.test.service;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
import com.ibaseit.ecommerceapplicationspring.entity.Cart;
import com.ibaseit.ecommerceapplicationspring.entity.Product;
import com.ibaseit.ecommerceapplicationspring.entity.User;
import com.ibaseit.ecommerceapplicationspring.repository.CartRepository;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.CartServiceImpl;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.EmailService;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.OrderServiceImpl;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.ProductServiceImpl;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)

public class CartServiceTest {
	@Mock
	private ProductServiceImpl productService;
	@Mock
	CartRepository repository;
	@Mock
	private UserServiceImpl userService;
	@Mock
	private EmailService emailService;
	@Mock
	private OrderServiceImpl orderService;
	@InjectMocks
	private CartServiceImpl cartService;
Cart cart;
	@Before
	public void init() {
		cart = getMockCart();

		MockitoAnnotations.initMocks(this);
	}

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void saveCartProductTest() throws Exception {
		Mockito.when(productService.getProductDetails(23)).thenReturn(getMockProduct());
		Mockito.when(userService.getUserDetails(26)).thenReturn(getMockUser());

		Assert.assertEquals(1, cartService.saveCartProduct(26, 23));
	}

	@Test(expected = Exception.class)
	public void saveCartProduct_QuantityTest() throws Exception {
		Mockito.when(productService.getProductDetails(23)).thenReturn(getMockExceptionProduct());

		cartService.saveCartProduct(26, 23);
		
	}
	

	@Test
	public void deleteCartProductTest() throws Exception {
		Mockito.when(cartService.getCartDetails(26, 23)).thenReturn(getMockCart());
		Mockito.when(productService.getProductDetails(23)).thenReturn(getMockProduct());
	   
		//long result = repository.deleteByUser_UserIdAndProductId(26, 23);
		//Assert.assertEquals(0,result);
	
		Assert.assertEquals(0, repository.deleteByUser_UserIdAndProductId(26, 23));
		
	}

	@Test(expected = Exception.class)
	public void deleteCartProduct_QuantityTest() throws Exception {
		Mockito.when(cartService.getCartDetails(26, 23)).thenReturn(getMockExceptionCart());

		cartService.deleteCart(26, 23);
	}
	
	@Test
	public void checkCartProductsTest() throws Exception {
		
		Mockito.when(repository.findByUser_UserId(26)).thenReturn(getCartProducts());
		double result=repository.deleteByUser_UserId(26);
		Assert.assertEquals(5000.00, 5000.00, result);
		
	}
	
	public Product getMockProduct() {
		Product product = new Product();
		product.setNameofProduct("bottle");
		product.setProductId(23);
		product.setTypeofProduct("store");
		product.setQuantity(1);
		product.setPrice(100);

		return product;
	}

	public User getMockUser() {
		User user = new User();
		user.setUserId(26);
		user.setUserName("ishu");
		user.setPassword("ish");
		user.setUserType("admin");
		user.setPhoneNumber(9999999);
		user.setName("ishwarya");
		return user;

	}

	public Product getMockExceptionProduct() {
		Product product = new Product();
		product.setNameofProduct("bottle");
		product.setProductId(23);
		product.setTypeofProduct("store");
		product.setQuantity(0);
		product.setPrice(100);

		return product;
	}
	public Cart getMockCart() {
		Cart cart = new Cart();
		User user=new User();
		cart.setNameofProduct("bottle");
		cart.setProductId(23);
		user.setUserId(26);
		cart.setTypeofProduct("store");
		cart.setQuantity(1);
		cart.setPrice(100);
		cart.setUser(user);
		return cart;
	}
	public Cart getMockExceptionCart() {
		Cart cart = new Cart();
		User user=new User();
		cart.setNameofProduct("bottle");
		cart.setProductId(23);
		user.setUserId(26);
		cart.setTypeofProduct("store");
		cart.setQuantity(0);
		cart.setPrice(100);
		cart.setUser(user);
		return cart;
	}

	List<Cart> getCartProducts(){
		Cart cart = new Cart();

		User user=new User();
        user.setUserId(26);
		cart.setProductId(23);
		cart.setProductId(24);
		cart.setProductId(25);
        cart.setUser(user);

  return   Arrays.asList(cart);
		
	}
}
