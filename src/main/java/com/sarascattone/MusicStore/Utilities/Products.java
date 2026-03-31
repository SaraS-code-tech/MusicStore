package com.sarascattone.MusicStore.Utilities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Products {
	
	/*TODO 
	  capire perchè non funziona lombok ed eliminare tutte le dichiarazione esplicite di getter e setter
    */
    
    @Id
    private Long id;
    private String name;
    private String artist;
    private String format;
    private String image_url;
    private Double price;
    private int stock_quantity;
    private int category_id;
    private int release_year;
    
    // Constructors
    public Products() {}
    
    public Products(Long id, String name, String artist, String format, String image_url, 
                   Double price, int stock_quantity, int category_id, int release_year) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.format = format;
        this.image_url = image_url;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.category_id = category_id;
        this.release_year = release_year;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    
    public String getImage_url() { return image_url; }
    public void setImage_url(String image_url) { this.image_url = image_url; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public int getStock_quantity() { return stock_quantity; }
    public void setStock_quantity(int stock_quantity) { this.stock_quantity = stock_quantity; }
    
    public int getCategory_id() { return category_id; }
    public void setCategory_id(int category_id) { this.category_id = category_id; }
    
    public int getRelease_year() { return release_year; }
    public void setRelease_year(int release_year) { this.release_year = release_year; }
}