package com.manoecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manoecommerce.entity.Product;
import com.manoecommerce.exception.ProductException;
import com.manoecommerce.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService service;

	@GetMapping("products")
	public ResponseEntity<Page<Product>> findProductByCategory(@RequestParam(required = false) String category,
			@RequestParam(required = false) List<String> color, @RequestParam(required = false) List<String> sizes,
			@RequestParam(required = false) Integer minPrice, @RequestParam(required = false) Integer maxPrice,
			@RequestParam(required = false) Integer minDiscount, @RequestParam(required = false) String sort,
			@RequestParam(required = false) String stock,
			@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize) {

		Page<Product> res = service.getAllProduct(category, color, sizes, minPrice, maxPrice, minDiscount, sort, stock,
				pageNumber, pageSize);
		return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
	}

	@GetMapping("/products/id/{productId}")
	public ResponseEntity<Product> findProdctById(@PathVariable Long productId) throws ProductException {
		Product product = service.findProductById(productId);
		return new ResponseEntity<>(product	, HttpStatus.ACCEPTED);
	}

	
}
