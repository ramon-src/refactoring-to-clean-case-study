package services;

import entities.Account;
import main.Bootstrapper;
import persistence.DataRepository;

public class AccountService {
	private DataRepository<Account> accountRepo;

	public AccountService() {
		accountRepo = Bootstrapper.getAccountRepo();
	}

	public Account find(int userAccountNumber) {
		return accountRepo.find(userAccountNumber);
	}

	public Boolean validatePin(Account account, Long pin) {
		return account.getPin() == pin;
	}

	public double getAvailableBalance(int userAccountNumber) {
		return accountRepo.find(userAccountNumber).getAvailableBalance();
	}

	public double getTotalBalance(int userAccountNumber) {
		return accountRepo.find(userAccountNumber).getTotalBalance();
	}

	public void credit(int userAccountNumber, double amount) {
		accountRepo.find(userAccountNumber).credit(amount);
	}

	public void debit(int userAccountNumber, double amount) {
		accountRepo.find(userAccountNumber).debit(amount);
	}
}
