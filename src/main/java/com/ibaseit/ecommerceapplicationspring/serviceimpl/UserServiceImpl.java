package com.ibaseit.ecommerceapplicationspring.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibaseit.ecommerceapplicationspring.entity.Product;
import com.ibaseit.ecommerceapplicationspring.entity.User;
import com.ibaseit.ecommerceapplicationspring.repository.UserRepository;
import com.ibaseit.ecommerceapplicationspring.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	
	UserRepository repository;
	
	public User getUserDetails(long userId) throws Exception {
		return (repository.findById(userId)).get();
	}

	public int saveUser(User user) throws Exception {
		repository.save(user);
		return 1;
	}
	

	@Override
	public User updateUser(User user) throws Exception {
	User persistedUser= repository.findByUserId(user.getUserId());
	if (persistedUser == null) {
		throw new Exception("USER not found");
	}
		return repository.save(user);
	}
	

}
