package com.manoecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manoecommerce.entity.Product;
import com.manoecommerce.exception.ProductException;
import com.manoecommerce.request.CreateProductReq;
import com.manoecommerce.response.ApiResponse;
import com.manoecommerce.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
	@Autowired
	private ProductService service;

	@PostMapping("/")
	public ResponseEntity<Product> createProduct(@RequestBody CreateProductReq req) {
		Product product = service.createProduct(req);
		return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{productId}/delete")
	public ResponseEntity<ApiResponse> deleteProducts(@PathVariable Long productId) throws ProductException {
		service.deleteProduct(productId);
		ApiResponse res = new ApiResponse();
		res.setMessage("Product Deleted Successfully");
		res.setStatus(true);

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> allProducts = service.findAllProduct();
		return new ResponseEntity<List<Product>>(allProducts, HttpStatus.ACCEPTED);
	}

	@PostMapping("/{productId}/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product req, @PathVariable Long productId)
			throws ProductException {
		Product product = service.updateProduct(productId, req);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

	@PostMapping("/creates")
	public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductReq[] req) {
		for (CreateProductReq requset : req) {
			service.createProduct(requset);
		}
		ApiResponse res = new ApiResponse();
		res.setMessage("Multiple product createrd successfully Successfully");
		res.setStatus(true);

		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
}
