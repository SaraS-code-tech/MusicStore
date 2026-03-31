package com.sarascattone.MusicStore.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private DataSource dataSource;
	
	public int doLogin(String email, String password) {
		
		String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
		
		try(Connection conn = dataSource.getConnection()) {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return 200;
			}
		
			return 204;
		}
		catch (Exception e) {
			e.printStackTrace();
			
			return 503;
		}
	}
}
