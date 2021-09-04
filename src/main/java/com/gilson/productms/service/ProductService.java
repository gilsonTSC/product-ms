package com.gilson.productms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gilson.productms.dtos.ProductDTO;
import com.gilson.productms.entity.ProductEntity;
import com.gilson.productms.reprository.ProductReprository;
import com.gilson.productms.service.exceptions.IdNotExistException;
import com.gilson.productms.service.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductReprository productreprository;
	
	public ProductEntity save(ProductDTO productDTO) {
		ProductEntity productEntity = this.converteDTOtoEntity(productDTO);
		
		return this.productreprository.save(productEntity);
	}
	
	public ProductEntity findById(String id) {
		Optional<ProductEntity> obj = this.productreprository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + "."));
	}
	
	public List<ProductEntity> findAll() {
		return this.productreprository.findAll();
	}
	
	public void delete(String id) {
		this.findById(id);
		
		try {
			this.productreprository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new IdNotExistException("Id not exist!");
		}
	}
	
	public ProductDTO converteEntityToDTO(ProductEntity productEntity) {
		ProductDTO productDTO = new ProductDTO();
		BeanUtils.copyProperties(productEntity, productDTO);
		
		return productDTO;
	}
	
	public ProductEntity converteDTOtoEntity(ProductDTO productDTO) {
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(productDTO, productEntity);
		
		return productEntity;
	}
	
}