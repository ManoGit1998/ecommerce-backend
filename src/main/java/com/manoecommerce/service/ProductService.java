package com.manoecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.manoecommerce.entity.Product;
import com.manoecommerce.exception.ProductException;
import com.manoecommerce.request.CreateProductReq;

@Service
public interface ProductService {

	public Product createProduct(CreateProductReq req);

	public String deleteProduct(Long productId) throws ProductException;

	public Product updateProduct(Long productId, Product product) throws ProductException;

	public Product findProductById(Long Id) throws ProductException;

	public List<Product> findProductByCategory(String category);

	public Page<Product> getAllProduct(String category, List<String> color, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

}
