package com.ibaseit.ecommerceapplicationspring.dto;

import lombok.Data;

@Data
public class OrderSearchDTO {
private long orderId;
private long userId;
private long productId;
private String productName;
private String typeOfProduct;



}
