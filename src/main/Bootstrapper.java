
package main;

import domain.User;
import persistence.AccountInMemoryRepository;
import ui.UI;

public class Bootstrapper
{
	private static User user;
	private static AccountInMemoryRepository accountRepo;
	
	public static void main(String[] args) {
		initDependencies();
		
		UI ui = new UI();
		ui.menu();
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