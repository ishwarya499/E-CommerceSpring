package com.ibaseit.ecommerceapplicationspring.test.controller;

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
import com.ibaseit.ecommerceapplicationspring.controller.UserController;
import com.ibaseit.ecommerceapplicationspring.dto.OrderSearchDTO;
import com.ibaseit.ecommerceapplicationspring.entity.Product;
import com.ibaseit.ecommerceapplicationspring.entity.User;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.OrderServiceImpl;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@Mock
	private UserServiceImpl userService;

	@InjectMocks
	private UserController userController;
	User user;
	@Before
	public void init() {
		user = getMockUser();
	    MockitoAnnotations.initMocks(this);
	}
	
ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void saveUserTest() throws Exception {
		
		Mockito.when(userService.saveUser(getMockUser())).thenReturn(1);	
	
		String result = userController.saveUser(getMockUser());
		Assert.assertEquals("succes",result);
	}
	@Test
	public void updateUserTest() throws Exception {
		Mockito.when(userService.updateUser(user)).thenReturn(getMockUser());
		User result = userController.updateUser(user);
		Assert.assertEquals(user.getUserName(),result.getUserName());
	}
	

	@Test
	public void getUserDetailsTest() throws Exception {
		Mockito.when(userService.getUserDetails(26)).thenReturn(getMockUser());
		User result = userController.getUserDetails(26);
		Assert.assertEquals(26,result.getUserId());
	}
	public User getMockUser(){
User user=new User();		
		user.setUserId(26);
		user.setName("ishu");
		user.setUserName("ishwarya");
		user.setPassword("ishu");
		return user;
	
	}
	
}
