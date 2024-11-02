package com.manoecommerce.service;

import com.manoecommerce.entity.User;
import com.manoecommerce.exception.UserException;

public interface UserService {
	
	public User finduserById(Long  userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt)  throws UserException;
	
	

}
