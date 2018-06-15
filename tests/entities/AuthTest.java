package entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import entities.Auth;

public class AuthTest {

	@Test
	public void shouldAuthenticateUser() {
		Auth auth = new Auth();
		assertEquals(true, auth.authenticate(12345, 54321));
	}	
	
	@Test
	public void shouldNotAuthenticateUser() {
		Auth service = new Auth();
		assertEquals(false, service.authenticate(3451, 54321));
	}
	
	@Test
	public void shouldNotAuthenticateUserWhenPinIsNotValid() {
		Auth service = new Auth();
		assertEquals(false, service.authenticate(12345, 545484));
	}
	
	@Test
	public void shouldUserExistsAfterAuthenticate() {
		Auth auth = new Auth();
		auth.authenticate(12345, 54321);
		assertEquals(new Account(12345, 54321, 0, 0).getNumber(), auth.getUser().getNumber());
	}
}

