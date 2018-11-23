package org.xyz.service;

import java.time.LocalDate;
import java.util.Set;

import org.xyz.model.Transaction;

public interface ITransactionService {

	public Set<Transaction> getAllTransactionsOfCustomer(long customerId);
	public Set<Transaction> getAllTransactionsOfCustomerInDateRange(long customerId,LocalDate fromDate,LocalDate toDate);
	public Set<Transaction> getAllTransactionsOfAccount(long accountNumber);
	public boolean createTransaction(Transaction transaction);
}
