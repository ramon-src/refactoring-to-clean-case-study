package test;

import static org.junit.Assert.*;

import org.junit.Test;

import atm.AccountService;
import entities.Account;

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
	
}
