package com.manoecommerce.request;

import java.util.HashSet;
import java.util.Set;

import com.manoecommerce.entity.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductReq {

	private String title;
	
	private String description;
	
	private int price;
	
	private int discountedPrice;
	
	private int quality;
	
	private String brand;
	
	private String color;
	
	private Set<Size> size = new HashSet<>();
	
	private String imageUrl;
	
	private String topLevelCategory;
	
	private String secondLevelCategory;
	
	private String thirdLevelCategory;
	
	   
	
}
