package com.gilson.productms.controlle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gilson.productms.dtos.ProductDTO;
import com.gilson.productms.entity.ProductEntity;
import com.gilson.productms.service.ProductService;
import com.gilson.productms.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value="/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO){
		ProductEntity productEntity = this.productService.save(productDTO);
		
		return new ResponseEntity<>(this.productService.converteEntityToDTO(productEntity), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable String id){
		ProductEntity productEntity = new ProductEntity();
		
		try {
			productEntity = this.productService.findById(id);
			productDTO.setId(productEntity.getId());
			
			productEntity = this.productService.save(productDTO);
		}catch(ObjectNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(this.productService.converteEntityToDTO(productEntity), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ProductDTO> findByIdProduct(@PathVariable String id){
		ProductEntity productEntity = new ProductEntity();
		
		try {
			productEntity = this.productService.findById(id);
		}catch(ObjectNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(this.productService.converteEntityToDTO(productEntity), HttpStatus.OK);
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<List<ProductDTO>> findByProduct(@RequestParam(value = "q", required = false, defaultValue = "") String q,
				    									  @RequestParam(value = "min_price", required = false) Double minPrice,
				    									  @RequestParam(value = "max_price", required = false) Double maxPrice){
		List<ProductDTO> productEntityList = new ArrayList<>();
		
		productEntityList = this.productService.findByNameAndPrice(q , minPrice, maxPrice).stream()
				.map(p -> this.productService.converteEntityToDTO(p)).collect(Collectors.toList());
		
		return new ResponseEntity<>(productEntityList, HttpStatus.OK);
	}
    
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ProductDTO>> findAllProduct(){
		List<ProductDTO> productEntityList = new ArrayList<>();
		
		productEntityList = this.productService.findAll().stream()
				.map(p -> this.productService.converteEntityToDTO(p)).collect(Collectors.toList());
		
		return new ResponseEntity<>(productEntityList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<List<ProductDTO>> deleteProduct(@PathVariable String id){
		try {
			this.productService.delete(this.productService.findById(id));
		}catch(ObjectNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}