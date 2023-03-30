package com.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Model.Product;
import com.demo.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService service;

	@PostMapping("/addProduct")
	public Product saveProduct(@RequestBody Product product) {
		return service.saveProduct(product);
	}

	@PostMapping("/addProducts")
	public List<Product> saveProducts(@RequestBody List<Product> products) {
		return service.saveProducts(products);
	}

	@GetMapping("/getProducts")
	public List<Product> findAllProducts() {
		return service.getProducts();
	}

	@GetMapping("/getProductById/{id}")
	public Product findProductById(@PathVariable int id) {
		return service.getProductById(id);
	}

	@GetMapping("/getProductByName/{name}")
	public Product findProductByName(@PathVariable String name) {
		return service.getProductByName(name);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable int id) {
		return service.deleteProduct(id);
	}

	@PutMapping("/update")
	public Product updateById(@RequestBody Product product) {
		return service.updateProduct(product);
	}

}
