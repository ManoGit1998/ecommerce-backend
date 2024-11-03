package com.manoecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manoecommerce.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{

	
	@Query("from Rating r where r.product.id=:productId")
	List<Rating> findAllRatingByProductId(Long productId);

}
