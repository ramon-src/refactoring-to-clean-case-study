package persistence;

import java.util.ArrayList;
import java.util.List;

import entities.Account;

public class AccountInMemoryRepository implements DataRepository<Account>{

	private List<Account> accounts;

	public AccountInMemoryRepository() {
		accounts = new ArrayList<Account>();
		accounts.add(new Account(12345, 54321L, 1000.0, 1200.0));
		accounts.add(new Account(98765, 56789L, 200.0, 200.0));
	}

	public List<Account> findAll() {
		return accounts;
	}

	public Account find(int id) {
		for (Account account : accounts) {
			if (account.getNumber() == id)
				return account;
		}
		return null;
	}

}
