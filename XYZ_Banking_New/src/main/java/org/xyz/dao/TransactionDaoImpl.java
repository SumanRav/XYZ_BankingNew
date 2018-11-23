package org.xyz.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

import org.xyz.model.Account;
import org.xyz.model.AccountType;
import org.xyz.model.Transaction;
import org.xyz.model.TransactionType;

public class TransactionDaoImpl implements ITransactionDao{

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
	public boolean createTransaction(Transaction transaction) {
		try(Connection connection = getConnection()) {
			
			String query = "insert into transaction values(null,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDate(1, Date.valueOf(transaction.getTransactionDate()));
			statement.setLong(2, transaction.getFromAccount().getAccountNumber());
			statement.setLong(3, transaction.getToAccount().getAccountNumber());
			statement.setDouble(4, transaction.getAmount());
			statement.setString(5, transaction.getTransactionType().toString());
			statement.setString(6, transaction.getDescription());
			
			if(statement.executeUpdate()>0) {
				System.out.println("Transaction inserted.");
				return true;
			}else {
				System.out.println("Error occured.");
			} 
		} catch (SQLException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public Set<Transaction> getAllTransactionsOfCustomer(long customerId) {
		try(Connection connection = getConnection()) {
			String sql = "select t.transactionId, t.transactionDate, t.amount, t.transactionType, t.description, "
					+ "a1.accountNumber, a1.customerId, a1.accountType, a1.openingDate, a1.openingBalance, a1.description,"
					+ "a2.accountNumber, a2.customerId, a2.accountType, a2.openingDate, a2.openingBalance, a2.description"
					+ " from transaction t, account a1 , account a2 "
					+ "where t.fromAccountNumber = a1.accountNumber and t.toAccountNumber = a2.accountNumber"
					+ " and (a1.customerId = ? or a2.customerId=?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, customerId);
			statement.setLong(2, customerId);
			
			ResultSet resultSet = statement.executeQuery();
			return getAllTransactionsFromCursor(resultSet);
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Set<Transaction> getAllTransactionsOfCustomerInDateRange(long customerId,LocalDate fromDate,LocalDate toDate){
		try(Connection connection = getConnection()) {
			String sql = "select t.transactionId, t.transactionDate, t.amount, t.transactionType, t.description, "
					+ "a1.accountNumber, a1.customerId, a1.accountType, a1.openingDate, a1.openingBalance, a1.description,"
					+ "a2.accountNumber, a2.customerId, a2.accountType, a2.openingDate, a2.openingBalance, a2.description"
					+ " from transaction t, account a1 , account a2 "
					+ "where t.fromAccountNumber = a1.accountNumber and t.toAccountNumber = a2.accountNumber"
					+ " and (a1.customerId = ? or a2.customerId=?)"
					+ " and (t.transactionDate between ? and ?)"
					+ " order by t.transactionId desc";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, customerId);
			statement.setLong(2, customerId);
			statement.setDate(3, Date.valueOf(fromDate));
			statement.setDate(4, Date.valueOf(toDate));
			
			ResultSet resultSet = statement.executeQuery();
			return getAllTransactionsFromCursor(resultSet);
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Set<Transaction> getAllTransactionsOfAccount(long accountNumber) {
		try(Connection connection = getConnection()) {
			String sql = "select t.transactionId, t.transactionDate, t.amount, t.transactionType, t.description, "
					+ "a1.accountNumber, a1.customerId, a1.accountType, a1.openingDate, a1.openingBalance, a1.description,"
					+ "a2.accountNumber, a2.customerId, a2.accountType, a2.openingDate, a2.openingBalance, a2.description"
					+ " from transaction t, account a1 , account a2 "
					+ "where t.fromAccountNumber = a1.accountNumber and t.toAccountNumber = a2.accountNumber"
					+ " and (fromAccountNumber=? or toAccountNumber=?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, accountNumber);
			statement.setLong(2, accountNumber);
			
			ResultSet resultSet = statement.executeQuery();
			return getAllTransactionsFromCursor(resultSet);
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	private Set<Transaction> getAllTransactionsFromCursor(ResultSet resultSet) throws SQLException {
		Set<Transaction> transactions = new LinkedHashSet<>();
		
		while(resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionId(resultSet.getLong("t.transactionId"));
				transaction.setTransactionDate(resultSet.getDate("t.transactionDate").toLocalDate());
				
				Account fromAccount = new Account();
				fromAccount.setAccountNumber(resultSet.getLong("a1.accountNumber"));
				fromAccount.setAccountType(AccountType.valueOf(resultSet.getString("a1.accountType")));
				fromAccount.setOpeningDate(resultSet.getDate("a1.openingDate").toLocalDate());
				fromAccount.setOpeningBalance(resultSet.getDouble("a1.openingBalance"));
				fromAccount.setDescription(resultSet.getString("a1.description"));

				Account toAccount = new Account();
				toAccount.setAccountNumber(resultSet.getLong("a2.accountNumber"));
				toAccount.setAccountType(AccountType.valueOf(resultSet.getString("a2.accountType")));
				toAccount.setOpeningDate(resultSet.getDate("a2.openingDate").toLocalDate());
				toAccount.setOpeningBalance(resultSet.getDouble("a2.openingBalance"));
				toAccount.setDescription(resultSet.getString("a2.description"));

				transaction.setFromAccount(fromAccount);
				transaction.setToAccount(toAccount);
				
				transaction.setAmount(resultSet.getDouble("t.amount"));
				transaction.setTransactionType(TransactionType.valueOf(resultSet.getString("t.transactionType").toUpperCase()));
				transaction.setDescription(resultSet.getString("t.description"));
				
				transactions.add(transaction);
			}
		return transactions;
	}
}

