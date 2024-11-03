package com.manoecommerce.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.manoecommerce.entity.Category;
import com.manoecommerce.entity.Product;
import com.manoecommerce.exception.ProductException;
import com.manoecommerce.repository.CategoryRepository;
import com.manoecommerce.repository.ProductRepository;
import com.manoecommerce.request.CreateProductReq;
import com.manoecommerce.service.ProductService;
import com.manoecommerce.service.UserService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	CategoryRepository categoryRepo;

	@Autowired
	private UserService userService;

	@Override
	public Product createProduct(CreateProductReq req) {

		Category topLevel = categoryRepo.findByName(req.getTopLevelCategory());
		if (topLevel == null) {
			Category topLevelCategory = new Category();
			topLevelCategory.setName(req.getTopLevelCategory());
			topLevelCategory.setLevel(1);
			topLevel = categoryRepo.save(topLevelCategory);
		}

		Category secondLevel = categoryRepo.findByNameAndParent(req.getSecondLevelCategory(), topLevel.getName());
		if (secondLevel == null) {
			Category secondLevelCategory = new Category();
			secondLevelCategory.setName(req.getSecondLevelCategory());
			secondLevelCategory.setLevel(2);
			secondLevelCategory.setParentCategory(topLevel);
			secondLevel = categoryRepo.save(secondLevelCategory);
		}

		Category thirdLevel = categoryRepo.findByNameAndParent(req.getThirdLevelCategory(), secondLevel.getName());
		if (thirdLevel == null) {
			Category thirdLevelCategory = new Category();
			thirdLevelCategory.setName(req.getSecondLevelCategory());
			thirdLevelCategory.setLevel(3);
			thirdLevelCategory.setParentCategory(secondLevel);
			thirdLevel = categoryRepo.save(thirdLevelCategory);
		}

		Product product = new Product();
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		product.setDiscountedPrice(req.getDiscountedPrice());
		product.setDiscountedPercent(req.getDiscountedPercent());
		product.setImageUrl(req.getImageUrl());
		product.setBrand(req.getBrand());
		product.setPrice(req.getPrice());
		product.setSizes(req.getSize());
		product.setQuantity(req.getQuantity());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());

		Product savedProduct = productRepo.save(product);

		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		Product product = findProductById(productId);
		product.getSizes().clear();
		productRepo.delete(product);
		return "Product Deleted Succsfully";
	}

	@Override
	public Product updateProduct(Long productId, Product req) throws ProductException {

		Product product = findProductById(productId);
		if (req.getQuantity() != 0) {
			product.setQuantity(req.getQuantity());
		}

		return productRepo.save(product);
	}

	@Override
	public Product findProductById(Long id) throws ProductException {

		Optional<Product> product = productRepo.findById(id);
		if (product.isPresent()) {
			return product.get();
		}
		throw new ProductException("Product not found for the given id - " + id);
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getAllProduct(String category, List<String> color, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {

		Pageable pageble = PageRequest.of(pageNumber, pageSize);
		List<Product> products = productRepo.filteredProduct(category, minPrice, maxPrice, minDiscount, sort);
		if (!color.isEmpty()) {
			products = products.stream().filter(p -> color.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
					.collect(Collectors.toList());
		}

		if (Strings.isNullOrEmpty(stock)) {
			if (stock.equals("in-stock")) {
				products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
			} else if (stock.equals("out-of-stock")) {
				products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
			}
		}

		int startIndex = (int) pageble.getOffset();
		int endIndex = Math.min(startIndex + pageble.getPageSize(), products.size());

		List<Product> pageContent = products.subList(endIndex, endIndex);

		Page<Product> filteredProducts = new PageImpl<>(pageContent, pageble, products.size());

		return filteredProducts;
	}

}
