package services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import entities.Account;
import entities.SuiteTest;
import main.Bootstrapper;
import services.AuthService;

public class AuthServiceTest extends SuiteTest {

	@Test
	public void shouldAuthenticateUser() {
		AuthService auth = new AuthService();
		assertEquals(true, auth.authenticate(12345, 54321));
	}	
	
	@Test
	public void shouldNotAuthenticateUser() {
		AuthService service = new AuthService();
		assertEquals(false, service.authenticate(3451, 54321));
	}
	
	@Test
	public void shouldNotAuthenticateUserWhenPinIsNotValid() {
		AuthService service = new AuthService();
		assertEquals(false, service.authenticate(12345, 545484));
	}
	
	@Test
	public void shouldUserExistsAfterAuthenticate() {
		AuthService auth = new AuthService();
		auth.authenticate(12345, 54321);
		assertEquals(new Account(12345, 54321L, 0, 0).getNumber(), Bootstrapper.user().getAccount().getNumber());
	}
}

