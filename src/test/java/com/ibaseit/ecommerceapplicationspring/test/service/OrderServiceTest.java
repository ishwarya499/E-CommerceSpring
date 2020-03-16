package com.ibaseit.ecommerceapplicationspring.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibaseit.ecommerceapplicationspring.dto.OrderSearchDTO;
import com.ibaseit.ecommerceapplicationspring.entity.Order;
import com.ibaseit.ecommerceapplicationspring.entity.Product;
import com.ibaseit.ecommerceapplicationspring.entity.User;
import com.ibaseit.ecommerceapplicationspring.repository.OrderRepository;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.CartServiceImpl;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.OrderServiceImpl;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.ProductServiceImpl;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.UserServiceImpl;

public class OrderServiceTest {
	@Mock
	OrderRepository repository;
	@Mock
	private UserServiceImpl userService;
	@Mock
	private CriteriaQuery<Order> criteriaQuery;
	@Mock
	private CriteriaQuery<Order> select;
	@Mock
	private CartServiceImpl cartService;
	@Mock
	private ProductServiceImpl productService;
	@Mock
	private EntityManager entityManager;
	@Mock
	private TypedQuery<Order> typedQuery;
	@Mock
	private Path<Object> productNamePath;
	@Mock
	private Expression<String> productTypePath;
	@Mock
	private Expression<String> productIdPath;
	@Mock
	private Expression<String> userIdPath;
	@Mock
	private Expression<String> dateEntryPath;
	@Mock
	private CriteriaQuery orderIdPath;

	@Mock
	private Root<Order> rootOrder_;

	@Mock
	private Join<Object, Object> rootProduct;
	@Mock
	CriteriaBuilder criteriaBuilder;
	@Mock
	Predicate mockPredicate;
	@InjectMocks
	private OrderServiceImpl orderService;
	@Mock
	Path<?> mockPath;
	Integer order;

	@Before
	public void init() {
		order = getMockOrder();
		MockitoAnnotations.initMocks(this);
	}

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void saveOrderProductTest() throws Exception {

		Mockito.when(productService.getProductDetails(23)).thenReturn(getMockProducts());
		Mockito.when(userService.getUserDetails(26)).thenReturn(getMockUser());
		List<Long> orderIds = new ArrayList<>();
		orderIds.add(23l);
		Assert.assertEquals(orderService.saveOrderProduct(26, orderIds), 1);

	}

	@Test
	public void checkOrderProductsTest() throws Exception {
		Mockito.when(orderService.checkOrderProducts(26)).thenReturn(getMockOrderList());
		List<Order> result = repository.findByUser_UserId(26);
		Assert.assertEquals(orderService.checkOrderProducts(26),result);
	}

	@Test
	public void getAllOrdersTest() throws Exception {
		Mockito.when(orderService.getAllOrders()).thenReturn(getAllOrderIds());
		List<Order> result = (List<Order>) repository.findAll();
		Assert.assertEquals(getAllOrderIds(), result);

	}

	@Test
	public void getSearchOrderTest() throws Exception {
		Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		Mockito.when(criteriaBuilder.createQuery(Order.class)).thenReturn(criteriaQuery);
		Mockito.when(criteriaQuery.from(Order.class)).thenReturn(rootOrder_);
		Mockito.when(rootOrder_.join("products", JoinType.INNER)).thenReturn(rootProduct);
		Mockito.when(criteriaBuilder.equal(Mockito.any(Expression.class), Mockito.any())).thenReturn(mockPredicate);
		Mockito.when(rootOrder_.get("user")).thenReturn((Path<Object>) mockPath);
		List<Order> mockResult = getMockOrderList();
		Mockito.when(criteriaQuery.select(rootOrder_)).thenReturn(select);
		Mockito.when(entityManager.createQuery(select)).thenReturn(typedQuery);
		Mockito.when(typedQuery.getResultList()).thenReturn(mockResult);
		List<Order> result = orderService.searchOrder(mockcriteriaDTO());
		Assert.assertEquals(result, mockResult);

	}

	@Test
	public void searchOrderTestCaseForBranches() throws Exception {
		Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		Mockito.when(criteriaBuilder.createQuery(Order.class)).thenReturn(criteriaQuery);
		Mockito.when(criteriaQuery.from(Order.class)).thenReturn(rootOrder_);
		Mockito.when(rootOrder_.join("products", JoinType.INNER)).thenReturn(rootProduct);
		List<Order> result = orderService.searchOrder(new OrderSearchDTO());
		Assert.assertTrue(result.isEmpty());
		orderService.searchOrder(getCritreaForBranches());

	}

	public Product getMockProducts() {
		Product products = new Product();
		products.setNameofProduct("bottle");
		products.setProductIds(23);
		products.setTypeofProduct("store");
		products.setQuantity(1);
		products.setPrice(100);

		return products;
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

	public Integer getMockOrder() {
		Order order = new Order();

		order.setUser(getMockUser());
		order.setTotalPrice(5000.00);

		order.setProducts(getCartProducts());
		return 1;

	}

	List<Product> getCartProducts() {

		Product products = new Product();

		products.setProductId(23);
		products.setProductId(24);
		products.setProductId(25);

		return Arrays.asList(products);

	}

	

	List<Order> getMockOrderList() {
		Order order = new Order();
		User user = new User();
		user.setUserId(26);
		order.setUser(user);
		order.setOrderId(101);

		return Arrays.asList(order);

	}

	List<Order> getAllOrderIds() {
		Order order = new Order();
		order.setOrderId(101);
		order.setOrderId(102);

		return Arrays.asList(order);

	}

	
	OrderSearchDTO mockcriteriaDTO() {
		OrderSearchDTO orderSearch = new OrderSearchDTO();
		orderSearch.setNameofProduct("dress");
		orderSearch.setTypeOfProduct("cloth");
		orderSearch.setUserId(26);
		orderSearch.setProductId(23);
		orderSearch.setStartDate(new Date());
		orderSearch.setEndDate(new Date());
		orderSearch.setOrderId(101);

		return orderSearch;

	}

	OrderSearchDTO getCritreaForBranches() {
		OrderSearchDTO orderSearch = new OrderSearchDTO();
		orderSearch.setNameofProduct("");
		orderSearch.setTypeOfProduct("");
		orderSearch.setUserId(0);
		orderSearch.setProductId(0);
		orderSearch.setStartDate(new Date());
		orderSearch.setEndDate(null);
		orderSearch.setOrderId(0);

		return orderSearch;

	}

}
