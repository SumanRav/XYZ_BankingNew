package org.xyz.service;

import java.util.Set;

import org.xyz.dao.AccountDaoImpl;
import org.xyz.dao.IAccountDao;
import org.xyz.dao.ITransactionDao;
import org.xyz.dao.TransactionDaoImpl;
import org.xyz.model.Account;
import org.xyz.model.Transaction;
import org.xyz.model.TransactionType;

public class AccountService implements IAccountService{

	IAccountDao accountDao = new AccountDaoImpl();
	
	@Override
	public boolean addAccount(Account account) {
		return accountDao.addAccount(account);
	}
	
	@Override
	public Set<Account> getAccountsOfCustomer(long customerId) {
		return accountDao.getAccountsOfCustomer(customerId);
	}

	@Override
	public Set<Account> getAccountsOfOtherCustomers(long excludedCustomerId) {
		return accountDao.getAccountsOfOtherCustomers(excludedCustomerId);
	}

	@Override
	public Account getAccountFromAccountNumber(long accountId) {
		return accountDao.getAccountFromAccountId(accountId);
	}

	@Override
	public double getCurrentBalanceOfAccount(Account account) {
		ITransactionDao transactionDao = new TransactionDaoImpl();
		Set<Transaction> transactions = transactionDao.getAllTransactionsOfAccount(account.getAccountNumber());
		double balance = account.getOpeningBalance();
		for(Transaction transaction:transactions) {
			if(transaction.getTransactionType()==TransactionType.DEPOSITE) {
				balance += transaction.getAmount();
			}else if(transaction.getTransactionType()==TransactionType.WITHDRAWAL){
				balance -= transaction.getAmount();
			}else if(transaction.getTransactionType()==TransactionType.FUND_TRANSFER){
				if(transaction.getFromAccount().getAccountNumber() == account.getAccountNumber()) {
					balance -= transaction.getAmount();
				}
				if(transaction.getToAccount().getAccountNumber() == account.getAccountNumber()){
					balance += transaction.getAmount();
				}
			}
		}
		return balance;
	}

}
