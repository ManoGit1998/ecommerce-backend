package com.manoecommerce.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manoecommerce.entity.Product;
import com.manoecommerce.entity.Rating;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.ProductException;
import com.manoecommerce.repository.RatingRepository;
import com.manoecommerce.request.RatingRequset;
import com.manoecommerce.service.ProductService;
import com.manoecommerce.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingRepo;

	@Autowired
	ProductService productService;

	@Override
	public Rating createrating(RatingRequset req, User user) throws ProductException {

		Product product = productService.findProductById(req.getProductId());

		Rating rating = new Rating();
		rating.setProduct(product);
		rating.setRating(req.getRating());
		rating.setCratedAt(LocalDateTime.now());
		rating.setUser(user);

		return ratingRepo.save(rating);
	}

	@Override
	public List<Rating> getProductRating(Long productId) {

		return ratingRepo.findAllRatingByProductId(productId);
	}

}
