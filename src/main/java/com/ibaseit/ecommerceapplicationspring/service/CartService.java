package com.ibaseit.ecommerceapplicationspring.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ibaseit.ecommerceapplicationspring.entity.Cart;

public interface CartService {

	@Transactional
	public long deleteCart(long userId, long productId) throws Exception;

	int saveCartProduct(long userId, long productId) throws Exception;

	List<Cart> getAllCarts() throws Exception;

	
	Cart getCartDetails(long userId, long productId) throws Exception;
	 double checkCartProducts(long userId) throws Exception; 
}
