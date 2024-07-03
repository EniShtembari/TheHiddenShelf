package com.boostmytool.TheHiddenShelf.models;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;

public class LibratDto {
	@NotEmpty(message="The title is required")
	private String title;
	
	@NotEmpty(message="The author is required")
	private String author;
	
	@NotEmpty(message="The publisher is required")
	private String publisher;
	
	@NotEmpty(message="The category is required")
	private String category;
	
	@Min(0)
	private int copiesNo;
	
	@Min(0)
    private float price;
	
	private MultipartFile imageFile;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCopiesNo() {
		return copiesNo;
	}

	public void setCopiesNo(int copiesNo) {
		this.copiesNo = copiesNo;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
	

}
