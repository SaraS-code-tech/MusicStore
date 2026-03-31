package com.sarascattone.MusicStore.Utilities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Categories {
	
	/*
	 * TODO capire perchè non funziona lombok ed eliminare tutte le dichiarazione
	 * esplicite di getter e setter
	 */

	@Id
	private Long id;
	
	private String name;
	private String type;
	
	public Categories() {}
	
	public Categories(Long id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
}
