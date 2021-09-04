package com.gilson.productms.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class ProductDTO {

	private String id;
	
	@NotBlank(message = "Preenchimento do campo name é obrigatório")
	private String name;
	
	@NotBlank(message = "Preenchimento do campo description é obrigatório")
	private String description;
	
	@Range(min = 0L, max = (long) Double.MAX_VALUE, message = "Apenas valores maiores que zero")
	@NotNull(message = "Preenchimento do campo price é obrigatório")
	private Double price;
	
}