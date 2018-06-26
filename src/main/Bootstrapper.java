
package main;

import atm.ATM;
import entities.User;
import persistence.AccountInMemoryRepository;

public class Bootstrapper
{
	private static User user;
	private static AccountInMemoryRepository accountRepo;
	
	public static void main(String[] args) {
		initDependencies();
		
		ATM theATM = new ATM();
		theATM.run();
	}

	public static void initDependencies() {
		setUser(new User());
		setAccountRepo(new AccountInMemoryRepository());
	}
	
	public static User user() {
		return Bootstrapper.user;		
	}
	
	public static void setUser(User user) {
		Bootstrapper.user = user;		
	}

	public static AccountInMemoryRepository getAccountRepo() {
		return Bootstrapper.accountRepo;
	}

	public static void setAccountRepo(AccountInMemoryRepository accountRepo) {
		Bootstrapper.accountRepo = accountRepo;
	}
}