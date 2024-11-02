package com.manoecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manoecommerce.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	


	public User findByEmail(String username);

}
