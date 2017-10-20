package com.caique.city.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.caique.city.factory.ConnectionFactory;
import com.caique.city.model.City;


public class CityDao {
	
	public List<City> findAll() throws SQLException {
		StringBuilder sql = new StringBuilder("select * from city");
		List<City> cities = new ArrayList<>();
		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				Double latitude = rs.getDouble("latitude");
				Double longitude = rs.getDouble("id");
				cities.add(new City(id, name, latitude, longitude));
			}
			
			return cities;
		} catch (SQLException error) {
			error.printStackTrace();
			throw error;
		}
	}
	
}
