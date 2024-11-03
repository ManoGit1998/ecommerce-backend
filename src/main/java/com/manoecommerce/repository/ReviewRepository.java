package com.manoecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manoecommerce.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query("from Review r where r.product.id=:productId")
	List<Review> getAllReviewByProductId(Long productId);

}
