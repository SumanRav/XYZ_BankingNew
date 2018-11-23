package org.xyz.dao;

import java.util.Set;

import org.xyz.model.Account;

public interface IAccountDao {

	public boolean addAccount(Account account);
	public Set<Account> getAccountsOfCustomer(long customerId);
	public Set<Account> getAccountsOfOtherCustomers(long excludedCustomerId);
	public Account getAccountFromAccountId(long accountId);
}
