package com.ibaseit.ecommerceapplicationspring.service;

import java.util.List;

import com.ibaseit.ecommerceapplicationspring.entity.Product;

public interface ProductService {
	public Product getProductDetails(long productId) throws Exception;

	int saveProduct(Product product) throws Exception;

	public int deleteProduct(long productId) throws Exception;

	public Product updateProduct(Product product) throws Exception;
  public List<Product> getAllProducts();
}

