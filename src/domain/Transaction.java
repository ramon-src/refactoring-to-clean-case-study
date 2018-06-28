package domain;

import main.Bootstrapper;
import services.AccountService;

public abstract class Transaction {
	private int accountNumber;
	private AccountService accountService;

	public Transaction() {
		accountNumber = Bootstrapper.user().getAccount().getNumber();
		accountService = new AccountService();
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	abstract public void execute();
	abstract public void setValue(Integer value);

}
