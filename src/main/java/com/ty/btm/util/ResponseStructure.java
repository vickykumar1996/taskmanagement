package com.ty.btm.util;

import lombok.Getter;
import lombok.Setter;

//<T> it is generalized class

@Getter
@Setter
public class ResponseStructure  <T> {
	private int statusCode;
	private String message;
	private T data;

}
