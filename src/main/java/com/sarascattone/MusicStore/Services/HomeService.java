package com.sarascattone.MusicStore.Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarascattone.MusicStore.Utilities.Categories;
import com.sarascattone.MusicStore.Utilities.Products;

@Service
public class HomeService {
	
	@Autowired
	private DataSource dataSource;
	
	public List<Products> getAllProducts() {
	    
	    List<Products> products = new ArrayList<Products>();
	    
	    String sql = "SELECT id, name, artist, format, image_url, price, stock_quantity, category_id, release_year FROM products ORDER BY name";
	    
	    try(Connection conn = dataSource.getConnection(); 
	        Statement stmt = conn.createStatement(); 
	        ResultSet rs = stmt.executeQuery(sql)) {
	        
	        while(rs.next()) {
	            Products p = new Products();
	            	            
	            p.setId(rs.getLong("id"));
	            p.setName(rs.getString("name"));
	            p.setArtist(rs.getString("artist"));
	            p.setFormat(rs.getString("format"));
	            p.setImage_url(rs.getString("image_url"));
	            p.setPrice(rs.getDouble("price"));
	            p.setStock_quantity(rs.getInt("stock_quantity"));
	            p.setCategory_id(rs.getInt("category_id"));
	            p.setRelease_year(rs.getInt("release_year"));
	            
	            products.add(p);
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return products;
	}
	
	public List<Categories> getCategories() {
		
		List<Categories> categories = new ArrayList<Categories>();
		
		String sql = "SELECT id, name, type FROM categories";
		
		try(Connection conn = dataSource.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			
			while(rs.next()) {
				Categories c = new Categories();
				
				c.setId(rs.getLong("id"));
				c.setName(rs.getString("name"));
				c.setType(rs.getString("type"));
				
				categories.add(c);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return categories;
	}
}
