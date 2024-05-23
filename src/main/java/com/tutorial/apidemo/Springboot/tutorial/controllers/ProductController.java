package com.tutorial.apidemo.Springboot.tutorial.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.apidemo.Springboot.tutorial.models.Product;
import com.tutorial.apidemo.Springboot.tutorial.models.ResponseObject;
import com.tutorial.apidemo.Springboot.tutorial.repositories.ProductRepository;

@RestController
@RequestMapping(path ="/api/v1/Products")
public class ProductController {
	@Autowired
	private ProductRepository repository;
	
	@GetMapping("")
	List<Product> getAll(){
		return repository.findAll();
	}
	@GetMapping("/{id}")
	ResponseEntity<ResponseObject> findById(@PathVariable Long id){
		Optional<Product> foundProduct = repository.findById(id);
		return foundProduct.isPresent()?
			 ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("oke","Query product successfully",foundProduct)
					):
			 ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseObject("false","Cannot find product with id= "+id,"")
					);
	}
	@PostMapping("/insert")
	ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
		List<Product> foundProducts =repository.findByProductName(newProduct.getProductName().trim());
		if(foundProducts.size()>0){
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("false","Product name already taken","")
					);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("oke","Insert Product successfully",repository.save(newProduct))
					);
		}
	
	}
	
	//Update, Insert
	@PutMapping("/{id}")
	ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
		Product updatedProduct= repository.findById(id)
			.map(product -> {
			product.setProductName(newProduct.getProductName());
			product.setProductYear(newProduct.getProductYear());
			product.setPrice(newProduct.getPrice());
			return repository.save(product);
		}).orElseGet(() -> {
			newProduct.setId(id);
			return repository.save(newProduct);
		});
	return ResponseEntity.status(HttpStatus.OK).body(
			new ResponseObject("oke","Update Product successfully", updatedProduct)
			);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
		boolean exists = repository.existsById(id);
		if(exists) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("oke","Delete Product successfully", "")
					);
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("failed","Cannot find product to delete", "")
				);
	}
	
}
