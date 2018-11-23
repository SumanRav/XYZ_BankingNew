package org.xyz.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.xyz.model.Account;
import org.xyz.model.AccountType;

public class AccountDaoImpl implements IAccountDao{

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
	
	public boolean addAccount(Account account) {
		try(Connection connection = getConnection()) {

			String sql="insert into account values(null,?,?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setDouble(1, account.getCustomer().getCustomerId());
			preparedStatement.setString(2, account.getAccountType().toString());
			preparedStatement.setDate(3,Date.valueOf(account.getOpeningDate()));
			preparedStatement.setDouble(4, account.getOpeningBalance());
			preparedStatement.setString(5, account.getDescription());
			
			int c =preparedStatement.executeUpdate();
			
			if(c>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public Set<Account> getAccountsOfCustomer(long customerId){
		Set<Account> accounts = new HashSet<>();
		try(Connection connection = getConnection()) {
			String sql = "select * from account where customerId=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, customerId);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Account account = new Account();
				account.setAccountNumber(resultSet.getLong("accountNumber"));
				account.setAccountType(AccountType.valueOf(resultSet.getString("accountType")));
				account.setOpeningDate(resultSet.getDate("openingDate").toLocalDate());
				account.setOpeningBalance(resultSet.getDouble("openingBalance"));
				account.setDescription(resultSet.getString("description"));
				
				accounts.add(account);
			}
			return accounts;
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	public Set<Account> getAccountsOfOtherCustomers(long excludedCustomerId){
		Set<Account> accounts = new HashSet<>();
		try(Connection connection = getConnection()) {
			String sql = "select * from account where customerId != ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, excludedCustomerId);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Account account = new Account();
				account.setAccountNumber(resultSet.getLong("accountNumber"));
				account.setAccountType(AccountType.valueOf(resultSet.getString("accountType")));
				account.setOpeningDate(resultSet.getDate("openingDate").toLocalDate());
				account.setOpeningBalance(resultSet.getDouble("openingBalance"));
				account.setDescription(resultSet.getString("description"));
				
				accounts.add(account);
			}
			return accounts;
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account getAccountFromAccountId(long accountId) {
		try(Connection connection = getConnection()) {
			String sql = "select * from account where accountNumber=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, accountId);
			
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				Account account = new Account();
				account.setAccountNumber(resultSet.getLong("accountNumber"));
				account.setAccountType(AccountType.valueOf(resultSet.getString("accountType")));
				account.setOpeningDate(resultSet.getDate("openingDate").toLocalDate());
				account.setOpeningBalance(resultSet.getDouble("openingBalance"));
				account.setDescription(resultSet.getString("description"));

				return account;
			}
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return null;
	}
}
