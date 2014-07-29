package common;

import java.io.Serializable;

public final class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int id;
	private final String productName;
	private final String description;
	private final double price;

	public Product (int id, String productName, String description, Double price){
		
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.price = price;
	}
	

	public int getId () {
		return id;
	}

	public String getProductName () {
		return productName;
	}

	public String getDescription () {
		return description;
	}

	public double getPrice () {
		return price;
	}
	
}
