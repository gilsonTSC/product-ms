package com.gilson.productms.controlle;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gilson.productms.dtos.ProductDTO;
import com.gilson.productms.entity.ProductEntity;
import com.gilson.productms.service.ProductService;

@RestController
@RequestMapping(value="/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO productDTO){
		ProductEntity productEntity = this.productService.save(productDTO);
		
		return new ResponseEntity<>(this.productService.converteEntityToDTO(productEntity), HttpStatus.CREATED);
	}
	
}