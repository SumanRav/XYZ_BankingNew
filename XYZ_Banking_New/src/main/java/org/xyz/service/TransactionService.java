package org.xyz.service;

import java.time.LocalDate;
import java.util.Set;

import org.xyz.dao.ITransactionDao;
import org.xyz.dao.TransactionDaoImpl;
import org.xyz.model.Transaction;
import org.xyz.model.TransactionType;

public class TransactionService implements ITransactionService{
	ITransactionDao transactionDao = new TransactionDaoImpl();
	
	@Override
	public boolean createTransaction(Transaction transaction) {
		if(transaction.getTransactionType()==TransactionType.WITHDRAWAL
				|| transaction.getTransactionType()==TransactionType.FUND_TRANSFER) {
			IAccountService accountService = new AccountService();
			double balance = accountService.getCurrentBalanceOfAccount(transaction.getFromAccount());
			if(balance<transaction.getAmount()) {
				return false;
			}
		}
		return transactionDao.createTransaction(transaction);		
	}

	@Override
	public Set<Transaction> getAllTransactionsOfCustomer(long customerId) {
		return transactionDao.getAllTransactionsOfCustomer(customerId);
	}
	
	@Override
	public Set<Transaction> getAllTransactionsOfCustomerInDateRange(long customerId,LocalDate fromDate,LocalDate toDate){
		return transactionDao.getAllTransactionsOfCustomerInDateRange(customerId,fromDate,toDate);
	}

	@Override
	public Set<Transaction> getAllTransactionsOfAccount(long accountNumber) {
		return transactionDao.getAllTransactionsOfAccount(accountNumber);
	}

}
