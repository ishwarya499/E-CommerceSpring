package com.ibaseit.ecommerceapplicationspring.test.service;

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
import com.ibaseit.ecommerceapplicationspring.entity.User;
import com.ibaseit.ecommerceapplicationspring.repository.UserRepository;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)

public class UserServiceTest {
	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserServiceImpl userService;
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
	}

	@Test(expected=Exception.class)
	public void updateExceptionUserTest() throws Exception {
		Mockito.when(userService.updateUser(user)).thenReturn(getMockUser());
		User result = repository.save(user);
		Assert.assertEquals(user.getUserName(), result.getUserName());

	}
	@Test
	public void updateUserTest() throws Exception {
		Mockito.when(repository.save(user)).thenReturn(getMockUser());
	}
	
	

	@Test
	public void getUserDetailsTest() throws Exception {
		Mockito.when(userService.getUserDetails(26)).thenReturn(getMockUser());
		//User result = repository.findByUserId(26);

		
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

}
