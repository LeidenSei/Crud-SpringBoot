package com.tutorial.apidemo.Springboot.tutorial.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String productName;
	private int productYear;
	private Double price;
	private String url;
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String productName, int productYear, Double price, String url) {
		super();
		this.productName = productName;
		this.productYear = productYear;
		this.price = price;
		this.url = url;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getProductYear() {
		return productYear;
	}

	public void setProductYear(int productYear) {
		this.productYear = productYear;
	}

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return super.toString();
	}
}
