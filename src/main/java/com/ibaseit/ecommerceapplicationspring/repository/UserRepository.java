package com.ibaseit.ecommerceapplicationspring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibaseit.ecommerceapplicationspring.entity.Product;
import com.ibaseit.ecommerceapplicationspring.entity.User;

@Repository
	public interface UserRepository extends CrudRepository<User,Long> {

	User findByUserId(long userId);
	
	}
