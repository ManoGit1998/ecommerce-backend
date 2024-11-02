package com.manoecommerce.exception;

import lombok.Data;

@Data

public class UserException extends Exception {

	public UserException(String message) {
		super(message);
	}

}
