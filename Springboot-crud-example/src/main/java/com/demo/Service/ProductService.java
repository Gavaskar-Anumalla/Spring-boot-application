package com.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Model.Product;
import com.demo.Repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo repository;

	// post methods
	/*
	 * name:saveProduct this will save a product
	 * 
	 * @param product
	 * 
	 * @return product
	 */

	public Product saveProduct(Product product) {
		return repository.save(product);

	}

	/*
	 * name:saveProducts this will save a list product
	 * 
	 * @param List<Product>
	 * 
	 * @return List<Product>
	 */
	public List<Product> saveProducts(List<Product> products) {
		return repository.saveAll(products);
	}

	// get methods
	/*
	 * this method will fetch all product details
	 * 
	 * @return List<Product>
	 */
	public List<Product> getProducts() {
		return repository.findAll();
	}
	/*
	 * this method will fetch product details by id
	 * 
	 * @param id
	 * 
	 * @return Product --if id is not present it will return null
	 * 
	 */

	public Product getProductById(int id) {
		return repository.findById(id).orElse(null);
	}
	/*
	 * this method will fetching product details by name
	 * 
	 * @param -name
	 * 
	 * @return- Product
	 */

	public Product getProductByName(String name) {
		return repository.findByName(name);
	}

	// Delete methods

	public String deleteProduct(int id) {
		repository.deleteById(id);
		return "product deleted succesfully!!!" + id;
	}

	// Update methods

	public Product updateProduct(Product product) {
		Product existingProduct = repository.findById(product.getId()).orElse(null);
		existingProduct.setName(product.getName());
		existingProduct.setQuantity(product.getQuantity());
		existingProduct.setPrice(product.getPrice());
		return repository.save(existingProduct);
	}

}
