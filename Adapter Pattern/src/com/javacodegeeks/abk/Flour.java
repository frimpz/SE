package com.javacodegeeks.abk;

public class Flour {
	int weight;
	float price;
	
	public Flour(){
		
	}
	public Flour(int weight,float price) {
		this.weight = weight;
		this.price = price;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

}
