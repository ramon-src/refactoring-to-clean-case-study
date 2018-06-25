package services;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Account;
import services.AccountService;

public class AccountServiceTest {

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
	
}
