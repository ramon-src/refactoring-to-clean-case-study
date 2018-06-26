package atm;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Account;
import entities.SuiteTest;
import entities.User;
import main.Bootstrapper;
import services.AccountService;

public class TransactionTest extends SuiteTest{

	@Test
	public void shouldInitializeTransaction() {
		Account account = new Account(12345, 54321L, 10, 10);
		User user = new User();
		user.setAccount(account);
		Bootstrapper.setUser(user);
		
		Deposit deposit = new Deposit();
		
		assertEquals(12345, deposit.getAccountNumber());

		assertEquals(Screen.class, deposit.getScreen().getClass());

		assertEquals(AccountService.class, deposit.getBankDatabase().getClass());
	}
}
