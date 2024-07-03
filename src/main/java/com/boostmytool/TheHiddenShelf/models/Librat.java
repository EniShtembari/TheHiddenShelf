package com.boostmytool.TheHiddenShelf.models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="Librat")
public class Librat {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
    
	private String title;
    private String author;
    private String publisher;
    private String category;
    private int copiesNo;
    private float price;
    private String imageFileName;
    private Date createdAt;
	
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
    
    

}

