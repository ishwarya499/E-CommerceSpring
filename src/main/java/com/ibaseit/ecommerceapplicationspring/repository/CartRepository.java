package com.ibaseit.ecommerceapplicationspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibaseit.ecommerceapplicationspring.entity.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {

	 public long deleteByUser_UserIdAndProductId(long userId ,long productId);
	 Cart findByUser_UserIdAndProductId(long userId, Long productId) throws Exception;
	public List<Cart> findByUser_UserId(long userId);
	
	public  double deleteByUser_UserId(long userId);
		

	
}