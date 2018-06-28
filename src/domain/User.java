package domain;

import entities.Account;

public class User {
	
	private Account account;
	
	private Boolean isAuthenticated;
	
	public User() {
		setIsAuthenticated(false);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setIsAuthenticated(Boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
}
