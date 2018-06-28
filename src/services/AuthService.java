package services;

import domain.User;
import entities.Account;
import main.Bootstrapper;

public class AuthService {
	private AccountService service;
	private User user;

	public AuthService() {
		service = new AccountService();
		user = new User();
	}

	public Boolean authenticate(int userAccountNumber, int userPIN) {
		Account account = service.find(userAccountNumber);
		if (account != null && account.validatePIN(userPIN)) {
			user.setAccount(account);
			user.setIsAuthenticated(true);
			Bootstrapper.setUser(user);
			return true;
		}
		return false;
	}

}