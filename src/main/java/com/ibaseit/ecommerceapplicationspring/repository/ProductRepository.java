package com.ibaseit.ecommerceapplicationspring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibaseit.ecommerceapplicationspring.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

	
	Product save(long productId);
	List<Product> findByProductIdIn(List<Long> productIds);
	Product findByProductId(long productId);
	
	

	
	


}
