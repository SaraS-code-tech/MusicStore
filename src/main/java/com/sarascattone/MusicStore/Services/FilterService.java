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
public class FilterService {

	@Autowired
	private DataSource dataSource;

	public List<String> getSingleCategorie(String type) {

		List<String> categories = new ArrayList<String>();

		String sql = "SELECT name FROM categories WHERE type = ?";

		try (Connection conn = dataSource.getConnection();) {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, type);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				categories.add(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return categories;
	}

	public List<Products> getFilteredProducts(String name) {

		List<Products> products = new ArrayList<Products>();

		String sql = "SELECT * FROM products AS P " + "JOIN categories AS C ON P.category_id = C.id "
				+ "WHERE C.name = ? ORDER BY P.name";

		try (Connection conn = dataSource.getConnection();) {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return products;
	}

	public List<Products> getProductsFromSearch(String search) {

		List<Products> products = new ArrayList<Products>();

		String sql = "SELECT * FROM products AS P " + "JOIN categories AS C ON P.category_id = C.id "
				+ "WHERE UPPER(P.name) LIKE UPPER(?) " + "OR UPPER(P.artist) LIKE UPPER(?) "
				+ "OR UPPER(P.format) LIKE UPPER(?) " + "OR UPPER(C.name) LIKE UPPER(?) " + "OR P.release_year = ? ORDER BY P.name";

		try (Connection conn = dataSource.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + search + "%");
			stmt.setString(2, "%" + search + "%");
			stmt.setString(3, "%" + search + "%");
			stmt.setString(4, "%" + search + "%");

			int year = 0;
	        try {
	            year = Integer.parseInt(search);
	        } catch (NumberFormatException e) {
	            year = -1;
	        }

	        stmt.setInt(5, year);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return products;
	}
}
