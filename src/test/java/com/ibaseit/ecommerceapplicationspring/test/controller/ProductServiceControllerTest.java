package com.ibaseit.ecommerceapplicationspring.test.controller;

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
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibaseit.ecommerceapplicationspring.controller.OrderController;
import com.ibaseit.ecommerceapplicationspring.controller.ProductController;
import com.ibaseit.ecommerceapplicationspring.entity.Order;
import com.ibaseit.ecommerceapplicationspring.entity.Product;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.OrderServiceImpl;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.ProductServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceControllerTest {
	@Mock
	private ProductServiceImpl productService;

	@InjectMocks
	private ProductController productController;

	Product product;

	
	@Before
	public void init() {
		product = getMockProduct();
	    MockitoAnnotations.initMocks(this);
	}

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void getProductDetailsTest() throws Exception {
		Mockito.when(productService.getProductDetails(23)).thenReturn(getMockProduct());
		Product result = productController.getProductDetails(23);
		Assert.assertEquals(23,result.getProductId());
	}
	@Test
	public void deleteProductTest() throws Exception {
		Mockito.when(productService.deleteProduct(24)).thenReturn(0);
	int result = productController.deleteProduct(24);
		Assert.assertEquals(0,result);
	
	}
	@Test
	public void saveProductTest() throws Exception {
		
		Mockito.when(productService.saveProduct( getMockProduct())).thenReturn(1);	
	
		String result = productController.saveProduct( getMockProduct());
		Assert.assertEquals("succes",result);
	}
	@Test
	public void updateProductTest() throws Exception {
		Mockito.when(productService.updateProduct(product)).thenReturn(getMockProduct());
		Product result = productController.updateProduct(product);
		Assert.assertEquals(product.getNameofProduct(),result.getNameofProduct());

	}
	@Test
	public void getAllProductsTest() throws Exception {
		Mockito.when(productService.getAllProducts()).thenReturn(getAllProductIds());
		List<Product> result = productController.getAllProducts();
		Assert.assertEquals(getAllProductIds(),result);
	}
	
	
	
	private List<Product> getAllProductIds() {
		product.setProductId(23);
		return Arrays.asList(product);
	}
	public Product getMockProduct(){
		Product product=new Product();
		product.setNameofProduct("bottle");
		product.setProductId(23);
		product.setTypeofProduct("store");
		product.setQuantity(1);
		product.setPrice(100);
		
		return product;
		
	}

	public int getMockDeleteProduct(){
		Product product=new Product();
		product.setNameofProduct("bottle");
		product.setProductId(24);
		product.setTypeofProduct("store");
		product.setQuantity(1);
		product.setPrice(100);
		
		return 0;
	
}
	
	}
