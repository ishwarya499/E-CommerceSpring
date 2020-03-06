package com.ibaseit.ecommerceapplicationspring.serviceimpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibaseit.ecommerceapplicationspring.entity.Cart;
import com.ibaseit.ecommerceapplicationspring.entity.Order;
import com.ibaseit.ecommerceapplicationspring.entity.Product;
import com.ibaseit.ecommerceapplicationspring.entity.User;
import com.ibaseit.ecommerceapplicationspring.repository.OrderRepository;
import com.ibaseit.ecommerceapplicationspring.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired

	OrderRepository repository;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private CartServiceImpl cartService;
	@Autowired
	private ProductServiceImpl productService;
	@Override
	public List<Order> getAllOrders() throws Exception {

		return (List<Order>) repository.findAll();
	}


@Override

public int saveOrderProduct(long userId, List<Long> productIds) throws Exception {
Order order=new Order();
List<Product> products = (List<Product>) productService.getProductDetails(productIds);
User user = userService.getUserDetails(userId);
order.setUser(user);
order.setProducts(products);
order.setTotalPrice(products.stream().mapToDouble(Product::getPrice).sum());
repository.save(order);
	
	return 1;
}





@Override
public List<Order> checkOrderProducts(long userId) throws Exception {
	return repository.findByUser_UserId(userId);
	
	

}
}
