package com.hospital.HospitalManagementSystem.connection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class GetConnectionImpl implements GetConnection {
	private Properties readProperties(String fileName) {
		FileInputStream fis = null;
		Properties property = null;
		
		try {
			fis = new FileInputStream(fileName);
			property = new Properties();
			property.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return property;
	}
	
	@Override
	public Connection getConnection() {
		Properties properties = readProperties("src/resources/database.properties");
		
		String registerDriver = properties.getProperty("registerDriver");
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		
		Connection con = null;
		
		try {
			Class.forName(registerDriver);
			
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
		
	}

}
