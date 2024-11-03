package com.manoecommerce.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manoecommerce.com.config.JwtProvider;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.UserException;
import com.manoecommerce.repository.UserRepository;
import com.manoecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	JwtProvider jwtProvider;

	@Override
	public User finduserById(Long userId) throws UserException {

		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			return user.get();
		}

		throw new UserException("User Not Found For Given Id - " + userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {

		String email = jwtProvider.getEmailFromToken(jwt);
		User user = userRepo.findByEmail(email);
		if (user == null) {
			throw new UserException("User Not Found with Email - " + email);
		}

		return user;
	}

}
