package com.ps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.model.Product;
import com.ps.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/get")
	public ResponseEntity<?> getAllProduct() {
		return ResponseEntity.ok(service.getAllProduct());
	}

	@PostMapping("/add")
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		service.addProduct(product);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/update/{productId}")
	public ResponseEntity<?> updateProduct(@PathVariable Integer productId, @RequestBody Product product) {
		Product updateProduct = service.updateProduct(productId, product);
		return ResponseEntity.ok(updateProduct);
	}

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
		service.removeProduct(productId);
		return ResponseEntity.accepted().build();
	}
	
	@GetMapping("/get/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable Integer productId) {
		return ResponseEntity.ok(service.getProductBuId(productId));
	}

}
