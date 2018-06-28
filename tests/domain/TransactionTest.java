package domain;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.Deposit;
import entities.Account;
import entities.SuiteTest;
import main.Bootstrapper;
import services.AccountService;
import ui.Screen;

public class TransactionTest extends SuiteTest{

	@Test
	public void shouldInitializeTransactionWhenInstantiate() {
		Account account = new Account(12345, 54321L, 10, 10);
		User user = new User();
		user.setAccount(account);
		Bootstrapper.setUser(user);
		
		Deposit deposit = new Deposit();
		
		assertEquals(12345, deposit.getAccountNumber());

		assertEquals(AccountService.class, deposit.getAccountService().getClass());
	}
}
