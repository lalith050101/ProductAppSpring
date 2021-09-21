package com.example.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.model.Product;
import com.example.application.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;

	@PostMapping("/saveProduct")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		Product product2 = productRepository.findByProductId(product.getproductId()).orElse(null);
		if(product2 != null){
			return new ResponseEntity<String>( "Already Exists with same Product Id", HttpStatus.CONFLICT);
		}
		productRepository.save(product);
		return new ResponseEntity<String>( "Saved Product", HttpStatus.CREATED);
	}
	
	
	@GetMapping("/deleteProduct")
	public ResponseEntity<String> deleteProduct(@RequestParam Long id) {
		Optional<Product> product2 = productRepository.findByProductId(id);
		
		if(product2 == null) {
			return new ResponseEntity<String>( "Product with given Id does not exist", HttpStatus.CONFLICT);
		}
		
		productRepository.deleteById(id);
		return new ResponseEntity<String>( "Deleted Product", HttpStatus.OK);
	}
	
	@GetMapping("/getAllProduct")
	public ResponseEntity<List<?>> getAllProducts() {
		System.out.println("Hai");
		return new ResponseEntity<List<?>>(productRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<String> home() {
		System.out.println("Hai");
		return new ResponseEntity<String>("hi", HttpStatus.OK);
	}
	
	@GetMapping("/getProduct")
	public ResponseEntity<?> getProductById(@RequestParam Long id) {
		
		Product product = productRepository.findByProductId(id).orElse(null);
		if(product == null){
			return new ResponseEntity<String>( "Product does not exists with given Product Id", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Product>(product , HttpStatus.OK);
	}
	
	@PutMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(@RequestParam Long id, @RequestBody Product product) {
		Optional<Product> product2 = productRepository.findByProductId(id);
		
		if(product2 == null) {
			return new ResponseEntity<String>( "Product with given Id does not exist", HttpStatus.CONFLICT);
		}
		product.setproductId(id);
		productRepository.save(product);
		return new ResponseEntity<String>( "Updated Product", HttpStatus.OK);
	}
	
}





