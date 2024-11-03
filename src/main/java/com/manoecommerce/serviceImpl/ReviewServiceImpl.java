package com.manoecommerce.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manoecommerce.entity.Product;
import com.manoecommerce.entity.Review;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.ProductException;
import com.manoecommerce.repository.ProductRepository;
import com.manoecommerce.repository.ReviewRepository;
import com.manoecommerce.request.ReviewRequest;
import com.manoecommerce.service.ProductService;
import com.manoecommerce.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewRepository reviewRepo;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepo;
	
	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		
		Product product = productService.findProductById(req.getProductId());
		
		Review review=new Review();
		
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		return reviewRepo.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		return reviewRepo.getAllReviewByProductId(productId);
	}

}
