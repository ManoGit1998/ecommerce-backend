package com.manoecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manoecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p from Product p where (p.category.name=:category OR :category='') and ((minPrice IS NULL AND :maxPrice IS NULL) OR (p.))")
	public List<Product> filteredProduct(@Param("category") String category, @Param("minPrice") Integer minPrice,
			@Param("maxPrice") Integer maxPrice, @Param("minDiscount") Integer minDiscount, @Param("sort") String sort,
			@Param("stock") String stock, @Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);

//	String category, List<String> color, List<String> sizes, Integer minPrice,
//	Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize
}
