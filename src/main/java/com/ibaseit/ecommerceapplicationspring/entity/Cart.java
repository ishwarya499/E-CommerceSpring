package com.ibaseit.ecommerceapplicationspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_tbl")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cartProductId;
	
	private long productId;

	@Column(name = "nameof_product")
	private String nameofProduct;

	@Column(name = "typeof_product")
	private String typeofProduct;

	@Column(name = "priceof_product")
	private double price;

	@Column(name = "quantityof_product")
	private int quantity;

	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getNameofProduct() {
		return nameofProduct;
	}

	public void setNameofProduct(String nameofProduct) {
		this.nameofProduct = nameofProduct;
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
		this.quantity = quantity;

	}
	public double total;
	

}
