package com.ibaseit.ecommerceapplicationspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibaseit.ecommerceapplicationspring.entity.Product;
import com.ibaseit.ecommerceapplicationspring.serviceimpl.ProductServiceImpl;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
	
	
	@Autowired
	private ProductServiceImpl productService;

	@GetMapping(path = "/productdetailsbyid", produces = "application/json")
	public Product getProductDetails(@RequestParam("productId") long productId) throws Exception {
		return productService.getProductDetails(productId);
	}

	@PostMapping(path = "/saveProduct", consumes = "application/json", produces = "application/json")
	public String saveProduct(@RequestBody Product product) throws Exception {
		productService.saveProduct(product);
		return "succes";
	}

	@DeleteMapping(path = "/deleteProduct", produces = "application/json")
	public int deleteProduct(@RequestParam("productId") long productId) throws Exception {
		return productService.deleteProduct(productId);

	}

	@PutMapping(path = "/updateProduct", produces = "application/json")
	public Product updateProduct(@RequestBody Product product) throws Exception {
		return productService.updateProduct(product);

	}
	@GetMapping(path = "/productdetails", produces = "application/json")
	public List<Product> getAllProducts() throws Exception {
		return productService.getAllProducts();
	}
}
