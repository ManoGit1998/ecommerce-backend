package com.manoecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.manoecommerce.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	public Category findByName(String brand);
	
	
    @Query("from Category c where c.name=:name and c.parentCategory.name=:parentCategoryname")
	public Category findByNameAndParent(@Param("name") String name,@Param("parentCategoryname") String parentCategoryname);

}
