package com.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/*import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;*/

/*@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor*/
//this will persist the data into database
@Entity
@Table(name = "product_details")

public class Product {

	@Id
	@GeneratedValue(generator = "sequence_generator")
	@GenericGenerator(name = "sequence_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "product_sequence"),
			@Parameter(name = "initial_value", value = "20000"), @Parameter(name = "increment_size", value = "1")

	})

	private int id;
	private String name;
	private int quantity;
	private double price;

	public Product(int id, String name, int quantity, double price) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public Product() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
