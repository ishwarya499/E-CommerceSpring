package com.ibaseit.ecommerceapplicationspring.service;

import com.ibaseit.ecommerceapplicationspring.entity.User;

public interface UserService {
	public User getUserDetails(long userId) throws Exception;

	int saveUser(User user) throws Exception;

	public User updateUser(User user) throws Exception;

}
