package com.ibaseit.ecommerceapplicationspring.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibaseit.ecommerceapplicationspring.entity.Cart;
import com.ibaseit.ecommerceapplicationspring.entity.Product;
import com.ibaseit.ecommerceapplicationspring.entity.User;
import com.ibaseit.ecommerceapplicationspring.repository.CartRepository;
import com.ibaseit.ecommerceapplicationspring.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired

	CartRepository repository;
	@Autowired
	private ProductServiceImpl productService;
	@Autowired
	private CartServiceImpl cartService;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private OrderServiceImpl orderService;

	@Override
	public int saveCartProduct(long userId, long productId) throws Exception {
		Cart cart = new Cart();
		Product product = productService.getProductDetails(productId);
		if (product.getQuantity() == 0) {
			throw new Exception("insufficientProducts");
		}
		BeanUtils.copyProperties(product, cart);
		User user = userService.getUserDetails(userId);
		cart.setUser(user);
		repository.save(cart);
		product.setQuantity(product.getQuantity() - 1);
		productService.saveProduct(product);

		return 1;
	}

	@Override
	public List<Cart> getAllCarts() throws Exception {

		return (List<Cart>) repository.findAll();
	}

	@Override
	public Cart getCartDetails(long userId, long productId) throws Exception {
		return repository.findByUser_UserIdAndProductId(userId, productId);
	}

	/* @Transactional(rollbackFor = {Exception.class}) */
	@Override
	public long deleteCart(long userId, long productId) throws Exception {
		Cart cart = cartService.getCartDetails(userId, productId);
		if (cart.getQuantity() == 0) {
			throw new Exception("This product not available in cart");
		}
		Product product = productService.getProductDetails(productId);
		product.setQuantity(product.getQuantity() + 1);
		productService.saveProduct(product);
		cart.setQuantity(cart.getQuantity() - 1);
		cartService.saveCartProduct(userId, productId);
		return repository.deleteByUser_UserIdAndProductId(userId, productId);
	}
@Transactional
	@Override
	public double checkCartProducts(long userId) throws Exception {

		List<Cart> cartProds = repository.findByUser_UserId(userId);
		
		if(cartProds.isEmpty())
		{
			throw new Exception("no products in cart");
		}
		
		Double totalPrice = cartProds.stream().mapToDouble(Cart::getPrice).sum();
		
		String to = "ishwaryabtech5@gmail.com";
		String body = "price" + totalPrice;
		String subject = "your total cart price";
		emailService.sendMail(to, subject, body);
		orderService.saveOrderProduct(userId,cartProds.stream().map(cart->cart.getProductId()).collect(Collectors.toList()));
		return repository.deleteByUser_UserId(userId);
	}



}
