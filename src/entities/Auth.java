package entities;

import services.AccountService;

public class Auth {
	public Account[] accounts;
	private AccountService service;

	public Auth() {
		service = new AccountService();
	}

	public boolean authenticateUser(int userAccountNumber, int userPIN) {
		// attempt to retrieve the account with the account number
		Account userAccount = service.find(userAccountNumber);

		// if account exists, return result of Account method validatePIN
		if (userAccount != null)
			return userAccount.validatePIN(userPIN);
		else
			return false; // account number not found, so return false
	}
}