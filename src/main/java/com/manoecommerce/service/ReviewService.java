package com.manoecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manoecommerce.entity.Review;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.ProductException;
import com.manoecommerce.request.ReviewRequest;

@Service
public interface ReviewService {

	public Review createReview(ReviewRequest req, User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
}
