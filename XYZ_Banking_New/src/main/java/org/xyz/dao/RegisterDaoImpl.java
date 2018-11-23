package org.xyz.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.xyz.model.AddressBean;
import org.xyz.model.CustomerBean;

public class RegisterDaoImpl implements IRegisterDao{

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
	public boolean registerCustomer(CustomerBean customerBean) {
		Connection connection = null;
		try{
			connection = getConnection();
			connection.setAutoCommit(false);
			
			String query = "insert into customer values(null,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, customerBean.getFirstName());
			statement.setString(2, customerBean.getLastName());
			statement.setDate(3, Date.valueOf(customerBean.getDateOfBirth()));
			statement.setString(4, customerBean.getEmailId());
			statement.setString(5, customerBean.getMobileNo());
			statement.setString(6, customerBean.getPassword());
			int c1 = statement.executeUpdate();
			
			int customerId = 100;
			String sql = "select max(custId) from customer";
			PreparedStatement statementForId = connection.prepareStatement(sql);
			ResultSet resultSet = statementForId.executeQuery();
			if(resultSet.next()) {
				customerId = resultSet.getInt(1);
			}
			AddressBean address = customerBean.getAddress();
			address.setCustomerId(customerId);
			
			String addressQuery = "insert into address values(null,?,?,?,?,?,?)";
			PreparedStatement addressStatement = connection.prepareStatement(addressQuery);
			addressStatement.setInt(1,customerId);
			addressStatement.setString(2,address.getAddressLine1());
			addressStatement.setString(3,address.getAddressLine2());
			addressStatement.setString(4,address.getCity());
			addressStatement.setString(5,address.getState());
			addressStatement.setString(6,address.getPincode());
			int c2 = addressStatement.executeUpdate();
				
			if(c1>0 && c2>0) {
				
				connection.commit();
				return true;
			}else {
				//System.out.println("Error occured.");
				connection.rollback();
				return false;
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(e.toString());
			e.printStackTrace();
		}finally {
			try {
				if(connection!=null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}
}
