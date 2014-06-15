package common;
public class Product {

	private int id;
	private String productName;
	private String description;
	private double price;
	
	
	Product (String productName, String description, Double price){
		
		this.id = 0;
		this.setProductName(productName);
		this.setDescription(description);
		this.setPrice(price);	
	}
	
	Product (int id, String productName, String description, Double price){
		
		this.id = id;
		this.setProductName(productName);
		this.setDescription(description);
		this.setPrice(price);
	}
	

	public int getId() {
		return id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
}
