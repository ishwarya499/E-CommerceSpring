package com.ibaseit.ecommerceapplicationspring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibaseit.ecommerceapplicationspring.entity.Cart;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.CartServiceImpl;

@RestController
@RequestMapping(path = "/cart")
public class CartController {
	@Autowired
	private CartServiceImpl cartService;

	@GetMapping(value = "/saveCartProduct/{userId}/{productId}")
	public int saveCartProduct(@PathVariable("productId") long productId, @PathVariable("userId") long userId)
			throws Exception {
		return cartService.saveCartProduct(userId, productId);
	}

	@GetMapping(path = "/cartdetails", produces = "application/json")
	public List<Cart> getAllCarts() throws Exception {
		return cartService.getAllCarts();
	}

	@DeleteMapping(value = "/deleteCart/{userId}/{productId}")
	public long deleteCart(@PathVariable("productId") long productId, @PathVariable("userId") long userId)
			throws Exception {
		return cartService.deleteCart(userId, productId);
	}
	@GetMapping(value = "/checkCartProducts/{userId}")
	public double checkCartProducts( @PathVariable("userId") long userId) throws Exception {
	return cartService.checkCartProducts(userId);
		
	}
	

}
