package com.manoecommerce.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manoecommerce.com.config.JwtProvider;
import com.manoecommerce.entity.Cart;
import com.manoecommerce.entity.User;
import com.manoecommerce.exception.UserException;
import com.manoecommerce.repository.UserRepository;
import com.manoecommerce.request.LoginRequest;
import com.manoecommerce.response.AuthResponse;
import com.manoecommerce.service.CartService;
import com.manoecommerce.serviceImpl.CustomUserServiceImplemetation;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserRepository userrepoository;

	private JwtProvider jwtProvider;

	private PasswordEncoder passEncoder;

	private CustomUserServiceImplemetation customUserServiceImplemetation;
	
	private CartService cartService;

	public AuthController(UserRepository userrepoository, CustomUserServiceImplemetation customUserServiceImplemetation,
			PasswordEncoder passEncoder ,JwtProvider jwtProvider,CartService cartService) {
		this.userrepoository = userrepoository;
		this.customUserServiceImplemetation = customUserServiceImplemetation;
		this.passEncoder = passEncoder;
		this.jwtProvider=jwtProvider;
		this.cartService=cartService;
	}

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();

		User isEmailExists = userrepoository.findByEmail(email);

		if (isEmailExists != null) {
			throw new UserException("Email is already used with another account ");
		}

		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passEncoder.encode(password));
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		createdUser.setCreatedAt(LocalDateTime.now());

		User savedUser = userrepoository.save(createdUser);
		Cart cart=cartService.createCard(savedUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		AuthResponse response = new AuthResponse(token, "Signup Succesfully");

		return new ResponseEntity<AuthResponse>(response, HttpStatus.CREATED);

	}

	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) {

		String userName = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		Authentication authentication = authenticate(userName, password);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateToken(authentication);

		AuthResponse response = new AuthResponse(token, "Signin Succesfully");

		return new ResponseEntity<AuthResponse>(response, HttpStatus.OK);

	}

	private Authentication authenticate(String userName, String password) {

		UserDetails userDetails = customUserServiceImplemetation.loadUserByUsername(userName);
		if (userDetails == null) {
			throw new BadCredentialsException("Invalid UserName....");
		}
		if (!passEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password...");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
