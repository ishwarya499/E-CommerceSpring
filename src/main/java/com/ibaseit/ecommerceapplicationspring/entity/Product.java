package com.ibaseit.ecommerceapplicationspring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

@Entity
@Table(name = "product_tbl")
public class Product {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	 private long productIds;
	 
	
	@Column(name="nameof_product")
	private String nameofProduct;
	 
	 @Column(name="typeof_product")
	private String typeofProduct;
	 
	 @Column(name="priceof_product")
	private double price;
	 
	 @Column(name="quantityof_product")
	private int quantity;
	public long getProductId()
	{
		return productId;
	}
	public void setProductId(long productId)
	{
		this.productId=productId;
	}

	public String getNameofProduct() {
		return nameofProduct;
	}

	public void setNameofProduct(String nameofProduct) {
		this.nameofProduct = nameofProduct;
	}
	 public long getProductIds() {
			return productIds;
		}
		public void setProductIds(long productIds) {
			this.productIds = productIds;
		}

	public String getTypeofProduct() {
		return typeofProduct;
	}

	public void setTypeofProduct(String typeofProduct) {
		this.typeofProduct = typeofProduct;
	}

public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
		
	
	}
	

}
