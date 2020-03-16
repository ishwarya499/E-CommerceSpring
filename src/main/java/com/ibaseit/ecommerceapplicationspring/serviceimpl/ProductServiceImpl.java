package com.ibaseit.ecommerceapplicationspring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibaseit.ecommerceapplicationspring.entity.Product;
import com.ibaseit.ecommerceapplicationspring.repository.ProductRepository;
import com.ibaseit.ecommerceapplicationspring.service.ProductService;

@Service

public class ProductServiceImpl implements ProductService {
	@Autowired

	ProductRepository repository;

	public Product getProductDetails(long productId) throws Exception {
		return repository.findByProductId(productId);
	}

	public List<Product> getProductDetails(List<Long> productIds) throws Exception {
		return repository.findByProductIdIn(productIds);
	}

	public int saveProduct(Product product) throws Exception {
		repository.save(product);
		return 1;

	}


	public int deleteProduct(long productId) throws Exception {
		repository.deleteById(productId);
		return 1;
	}

	
	public Product updateProduct(Product product) throws Exception {
		Product persistedProduct = repository.findByProductId(product.getProductId());
		if (persistedProduct == null) {
			throw new Exception("Product not found");
		}
		return repository.save(product);
	
	
	}

	public List<Product> getAllProducts() {
		return (List<Product>) repository.findAll();

	}

}
