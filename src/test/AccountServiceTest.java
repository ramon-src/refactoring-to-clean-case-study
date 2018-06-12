package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Account;
import services.AccountService;

public class AccountServiceTest {

	@Test
	public void shouldFindAccount () {
		AccountService service = new AccountService();
		assertEquals(new Account(12345,15,123,12).getNumber(), service.find(12345).getNumber());
	}

	@Test
	public void shouldNotFindAccount () {
		AccountService service = new AccountService();
		assertEquals(null, service.find(14));
	}
	
	@Test
	public void shouldAuthenticateUser() {
		AccountService service = new AccountService();
		assertEquals(true, service.authenticateUser(12345, 54321));
	}	
	
	@Test
	public void shouldNotAuthenticateUser() {
		AccountService service = new AccountService();
		assertEquals(false, service.authenticateUser(1245, 54321));
	}
}
