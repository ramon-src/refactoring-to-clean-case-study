package atm;

import main.Bootstrapper;
import services.AccountService;

public abstract class Transaction {
	private int accountNumber;
	private Screen screen;
	private AccountService accountService;

	public Transaction(int userAccountNumber, Screen atmScreen, AccountService atmBankDatabase) {
		accountNumber = Bootstrapper.user().getAccount().getNumber();
		screen = new Screen();
		accountService = new AccountService();
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public Screen getScreen() {
		return screen;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	abstract public void execute();

}
