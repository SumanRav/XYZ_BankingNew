package org.xyz.model;

import java.time.LocalDate;

public class Transaction {

	private long transactionId;
	private LocalDate transactionDate;
	private Account fromAccount;
	private Account toAccount;
	private double amount;
	private TransactionType transactionType;
	private String description;
	
	public Transaction() {
		super();
	}
	
	public Transaction(long transactionId, LocalDate transactionDate, Account fromAccount, Account toAccount,
			double amount, TransactionType transactionType, String description) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.transactionType = transactionType;
		this.description = description;
	}
	
	public long getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public Account getFromAccount() {
		return fromAccount;
	}
	
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	
	public Account getToAccount() {
		return toAccount;
	}
	
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public TransactionType getTransactionType() {
		return transactionType;
	}
	
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionDate=" + transactionDate + ", fromAccount="
				+ fromAccount + ", toAccount=" + toAccount + ", amount=" + amount + ", transactionType="
				+ transactionType + ", description=" + description + "]";
	}
	
	
	
}
