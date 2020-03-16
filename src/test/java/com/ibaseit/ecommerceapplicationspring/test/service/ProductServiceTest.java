package com.ibaseit.ecommerceapplicationspring.test.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibaseit.ecommerceapplicationspring.entity.Cart;
import com.ibaseit.ecommerceapplicationspring.entity.Product;
import com.ibaseit.ecommerceapplicationspring.repository.ProductRepository;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)

public class ProductServiceTest {

	@Mock
	private ProductRepository repository;
	@InjectMocks
	private ProductServiceImpl productService;
	Product product;

	@Before
	public void init() {
		product = getMockProduct();
		MockitoAnnotations.initMocks(this);
	}

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void saveProductTest() throws Exception {
		Mockito.when(productService.saveProduct(getMockProduct())).thenReturn(1);
		// Product result =repository.save(product);

	}

	@Test
	public void updateProductTest() throws Exception {
		Mockito.when(productService.updateProduct(product)).thenReturn(getMockProduct());
		Product result = productService.updateProduct(product);
		Assert.assertEquals(product.getNameofProduct(), result.getNameofProduct());

	}

	@Test
	public void deleteProductTest() throws Exception {
		Mockito.when(productService.deleteProduct(26)).thenReturn(getMockDeleteProduct());
	}

	@Test
	public void getProductDetailsTest() throws Exception {
		Mockito.when(productService.getProductDetails(23)).thenReturn(getMockProduct());

	}

	@Test
	public void getAllProductDetailsTest() throws Exception {

		List list = new ArrayList();
		list.add(new Long(23));
		Mockito.when(productService.getProductDetails(23)).thenReturn(getMockProduct());
		List<Product> result = repository.findByProductIdIn(list);

	}
	@Test
	public void getAllProductsTest() throws Exception {
		Mockito.when(productService.getAllProducts()).thenReturn(getAllProductsIds());
		Iterable<Product>  result = repository.findAll();
		Assert.assertEquals(getAllProductsIds(), result);

	}

	public Product getMockProduct() {
		Product product = new Product();
		product.setNameofProduct("bottle");
		product.setProductId(23);
		product.setTypeofProduct("store");
		product.setQuantity(1);
		product.setPrice(100);

		return product;

	}
	private Integer getMockDeleteProduct()
	{
		Product product = new Product();
		product.setProductId(23);
		return 1;
		
	}
	private List<Product> getAllProductsIds() {
		product.setProductId(23);
		product.setProductId(25);
		product.setProductId(27);
		product.setProductId(28);
		
		return Arrays.asList(product);
	}
}