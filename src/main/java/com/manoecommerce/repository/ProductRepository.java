package com.manoecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manoecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p " + "WHERE (p.category.name = :category OR :category IS NULL OR :category = '') "
			+ "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice)) "
			+ "AND (:minDiscount IS NULL OR p.discountedPercent >= :minDiscount) " + "ORDER BY "
			+ "CASE WHEN :sort = 'price_low' THEN p.discountedPrice END ASC, "
			+ "CASE WHEN :sort = 'price_high' THEN p.discountedPrice END DESC")
	public List<Product> filteredProduct(@Param("category") String category, @Param("minPrice") Integer minPrice,
			@Param("maxPrice") Integer maxPrice, @Param("minDiscount") Integer minDiscount, @Param("sort") String sort);

//	@Query("SELECT p FROM Product p " + "WHERE (p.category.name = :category OR :category = '') "
//			+ "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice)) "
//			+ "AND (:minDiscount IS NULL OR p.discountedPercent >= :minDiscount) " + "ORDER BY "
//			+ "CASE WHEN :sort = 'price_low' THEN p.discountedPrice END ASC, "
//			+ "CASE WHEN :sort = 'price_high' THEN p.discountedPrice END DESC")
//	public List<Product> filteredProduct(@Param("category") String category, @Param("minPrice") Integer minPrice,
//			@Param("maxPrice") Integer maxPrice, @Param("minDiscount") Integer minDiscount, @Param("sort") String sort);

}
