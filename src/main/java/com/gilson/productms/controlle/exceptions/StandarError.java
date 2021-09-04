package com.gilson.productms.controlle.exceptions;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandarError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer status_code;
	private String message;
	
	public StandarError(Integer status_code, String message) {
		this.status_code = status_code;
		this.message = message;
	}

}