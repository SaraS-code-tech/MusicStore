package com.sarascattone.MusicStore.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarascattone.MusicStore.Utilities.Products;

@Service
public class CartService {

	@Autowired
	private DataSource dataSource;
	
	public List<Products> getCart(int id) {
		
		List<Products> products = new ArrayList<Products>();
		
		// (C.quantity * P.price) AS total, 
		String sql = "SELECT P.name, P.artist, P.release_year, P.format, P.price, C.quantity, P.image_url "
				+ "FROM products AS P "
				+ "INNER JOIN cart_items AS C ON P.id = C.product_id "
				+ "WHERE C.user_id = ? "
				+ "ORDER BY P.name";
		
		try(Connection conn = dataSource.getConnection()) {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Products p = new Products();
				
				p.setName(rs.getString("name"));
				p.setArtist(rs.getString("artist"));
				p.setRelease_year(rs.getInt("release_year"));
				p.setPrice(rs.getDouble("price"));
				p.setImage_url(rs.getString("image_url"));
				p.setStock_quantity(rs.getInt("quantity"));
				p.setFormat(rs.getString("format"));
				
				products.add(p);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}
}
