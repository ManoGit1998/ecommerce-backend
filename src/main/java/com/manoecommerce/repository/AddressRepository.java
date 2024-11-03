package com.manoecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manoecommerce.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
