package entities;

import services.AccountService;

public class Auth {
	private AccountService service;

	public Auth() {
		service = new AccountService();
	}

	public boolean authenticate(int userAccountNumber, int userPIN) {
		Account account = service.find(userAccountNumber);

		if (account != null)
			return account.validatePIN(userPIN);
		else
			return false;
	}
}