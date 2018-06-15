package entities;

import services.AccountService;

public class Auth {
	private Account account;
	private AccountService service;

	public Auth() {
		service = new AccountService();
	}

	public Boolean authenticate(int userAccountNumber, int userPIN) {
		Account account = service.find(userAccountNumber);
		if (account != null && account.validatePIN(userPIN)) {
			this.account = account;
			return true;
		}
		return false;
	}
	
	public Account getUser() {
		return account;
	}
	
	
}