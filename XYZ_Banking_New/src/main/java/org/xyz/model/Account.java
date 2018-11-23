package org.xyz.model;

import java.time.LocalDate;

public class Account {

	private long accountNumber;
	private AccountType	accountType;
	private LocalDate openingDate;
	private double openingBalance;
	private String description;
	private CustomerBean customer;
	
	public Account() {
		super();
	}
	
	public Account(long accountNumber, AccountType accountType, LocalDate openingDate, double openingBalance,
			String description,CustomerBean customer) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.openingDate = openingDate;
		this.openingBalance = openingBalance;
		this.description = description;
		this.customer = customer;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public LocalDate getOpeningDate() {
		return openingDate;
	}
	
	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}
	
	public double getOpeningBalance() {
		return openingBalance;
	}
	
	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	public String getAccountDetails() {
		return "***Account Details***\nAccount Number: " + accountNumber + "\nAccount Type: " + accountType + "\nOpening Date: "
				+ openingDate + "\nOpeningBalance: " + openingBalance + "\nDescription: " + description;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", openingDate="
				+ openingDate + ", openingBalance=" + openingBalance + ", description=" + description + "]";
	}
	
	
}
