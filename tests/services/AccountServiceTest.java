package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import entities.Account;
import entities.SuiteTest;

public class AccountServiceTest extends SuiteTest {

	@Test
	public void shouldFindAccount () {
		AccountService service = new AccountService();
		assertEquals(new Account(12345,15L,123,12).getNumber(), service.find(12345).getNumber());
	}

	@Test
	public void shouldNotFindAccount () {
		AccountService service = new AccountService();
		assertEquals(null, service.find(14));
	}

	@Ignore
	@Test
	public void shouldValidatePin() {
		AccountService service = new AccountService();
		Account account = new Account(12345, 54321L, 10, 10);
		assertTrue(service.validatePin(account, 54321L));
	}	
	
	@Test
	public void shouldNotValidatePin() {
		AccountService service = new AccountService();
		Account account = new Account(12345, 54321L, 10, 10);
		assertFalse(service.validatePin(account, 54320L));
	}
	
	@Ignore
	@Test
	public void shouldGetAvailableBalance() {
		AccountService service = new AccountService();
		assertEquals(1000, service.getAvailableBalance(12345), 0);
	}
}
