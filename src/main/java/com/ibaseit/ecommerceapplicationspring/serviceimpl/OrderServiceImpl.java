package com.ibaseit.ecommerceapplicationspring.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibaseit.ecommerceapplicationspring.dto.OrderSearchDTO;
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
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Order> getAllOrders() throws Exception {

		return (List<Order>) repository.findAll();
	}

	@Override

	public int saveOrderProduct(long userId, List<Long> productIds) throws Exception {
		Order order = new Order();
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

	public List<Order> searchOrder(OrderSearchDTO criteriaDTO) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
		Root<Order> rootOrder_ = criteriaQuery.from(Order.class);
		Join<Order, Product> rootProduct = rootOrder_.join("products", JoinType.INNER);
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (criteriaDTO.getNameofProduct() != null && criteriaDTO.getNameofProduct() != "") {

			Expression<String> productNamePath = rootProduct.get("nameofProduct");
			Predicate statusNamePredicte = criteriaBuilder.equal(productNamePath, criteriaDTO.getNameofProduct());
			predicates.add(statusNamePredicte);
		}
		if (criteriaDTO.getTypeOfProduct() != null && criteriaDTO.getTypeOfProduct() != "") {

			Expression<String> productTypePath = rootProduct.get("typeOfProduct");
			Predicate statusTypePredicte = criteriaBuilder.like(productTypePath, criteriaDTO.getTypeOfProduct());
			predicates.add(statusTypePredicte);

		}
		if (criteriaDTO.getProductId() != 0) {

			Expression<Long> productIdPath = rootProduct.get("productId");
			Predicate statusIdPredicte = criteriaBuilder.equal(productIdPath, criteriaDTO.getProductId());
			predicates.add(statusIdPredicte);
		}
		if (criteriaDTO.getUserId() != 0) {

			Expression<Long> userIdPath = rootOrder_.get("user").get("userId");
			Predicate statusUserIdPredicte = criteriaBuilder.equal(userIdPath, criteriaDTO.getUserId());
			predicates.add(statusUserIdPredicte);
		}
		if (criteriaDTO.getOrderId() != 0) {

			Expression<Long> orderIdPath = rootOrder_.get("orderId");
			Predicate statusOrderIdPredicte = criteriaBuilder.equal(orderIdPath, criteriaDTO.getOrderId());
			predicates.add(statusOrderIdPredicte);
		}
		if (criteriaDTO.getStartDate() != null && criteriaDTO.getEndDate() != null) {
			Expression<Date> dateEntryPath = rootOrder_.get("createdDate");
			Predicate createdDatePredicate = criteriaBuilder.between(dateEntryPath, criteriaDTO.getStartDate(), criteriaDTO.getEndDate());
			predicates.add(createdDatePredicate);
		}

		if (predicates.isEmpty()) {
			return new ArrayList<>();
		}

		criteriaQuery.where(predicates.stream().toArray(size -> new Predicate[size]));

		CriteriaQuery<Order> select = criteriaQuery.select(rootOrder_);

		TypedQuery<Order> typedQuery = entityManager.createQuery(select);

		return typedQuery.getResultList();

	}

}
