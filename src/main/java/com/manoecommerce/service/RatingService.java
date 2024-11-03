package com.manoecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manoecommerce.entity.Rating;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.ProductException;
import com.manoecommerce.request.RatingRequset;

@Service
public interface RatingService {

	public Rating createrating(RatingRequset req,User user) throws ProductException;
	
	public List<Rating> getProductRating(Long productId);
	
}
