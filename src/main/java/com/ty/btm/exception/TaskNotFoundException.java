package com.ty.btm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskNotFoundException extends RuntimeException {
	private String message;
	

}
