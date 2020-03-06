package com.ibaseit.ecommerceapplicationspring.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderSearchDTO {
private long orderId;
private long userId;
private long productId;
private String nameofProduct;
private String typeOfProduct;
@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
private Date startDate;

@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
private Date endDate;






}
