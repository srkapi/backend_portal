package com.quebic.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Order not found")
public class OrderProcessNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 6920349436066566185L;
}
