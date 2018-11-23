package org.xyz.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.xyz.model.CustomerBean;
import org.xyz.model.LoginBean;

public class LoginDaoImpl implements ILoginDao{

	private Connection getConnection() {
		Connection connection = null;
		try {
			
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mysqldb.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			
			Class.forName(properties.getProperty("driverClass"));
			connection = DriverManager.getConnection(properties.getProperty("url")
					, properties.getProperty("user"), properties.getProperty("password"));
			return connection;
		} catch (ClassNotFoundException | SQLException | IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public CustomerBean isValidLogin(LoginBean loginBean) {		
		try(Connection connection = getConnection()) {

			String sql="select * from customer where emailId=? and custPwd=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				CustomerBean customer = new CustomerBean();
				customer.setFirstName(resultSet.getString("firstName"));
				customer.setLastName(resultSet.getString("lastName"));
				customer.setDateOfBirth(resultSet.getDate("dateOfBirth").toLocalDate());
				customer.setEmailId(resultSet.getString("emailId"));
				customer.setMobileNo(resultSet.getString("mobileNumber"));
				customer.setCustomerId(Long.parseLong(resultSet.getString("custId")));
				
				return customer;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
