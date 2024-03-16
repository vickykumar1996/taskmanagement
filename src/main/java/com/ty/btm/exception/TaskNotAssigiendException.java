package com.ty.btm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TaskNotAssigiendException extends RuntimeException {
	private String message;
	
	

}
