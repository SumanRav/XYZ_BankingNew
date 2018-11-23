package org.xyz.service;

import java.util.Set;

import org.xyz.model.Account;

public interface IAccountService {

	public boolean addAccount(Account account);
	public Set<Account> getAccountsOfCustomer(long customerId);
	public Set<Account> getAccountsOfOtherCustomers(long excludedCustomerId);

	public Account getAccountFromAccountNumber(long accountNumber);
	public double getCurrentBalanceOfAccount(Account account);
}
