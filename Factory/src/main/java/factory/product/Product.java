package factory.product;

import java.math.BigDecimal;

public class Product {

	private String name;
	private String describtion;
	private BigDecimal price;
	
	public Product(String name, String describtion, BigDecimal price) {
		super();
		this.name = name;
		this.describtion = describtion;
		this.price = price;
	}
	
	public Product() {
	}
	public Product(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribtion() {
		return describtion;
	}
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public static Product make(String name) {
		return new Product(name);
	}
	
	public Product init(String describtion, BigDecimal price) {
		this.describtion = describtion;
		this.price = price;
		return this;
	}

	//Used to override equals
	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}


		if (!(o instanceof Product)) {
			return false;
		}


		Product c = (Product) o;

		// Comparing members
		if(getName().equals(c.getName()) && getDescribtion().equals(c.getDescribtion())){
			return true;
		}

		return false;
	}

}




