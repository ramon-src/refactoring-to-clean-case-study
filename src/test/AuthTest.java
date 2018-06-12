package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import entities.Auth;

public class AuthTest {

	@Test
	public void shouldAuthenticateUser() {
		Auth auth = new Auth();
		assertEquals(true, auth.authenticateUser(12345, 54321));
	}	
	
	@Test
	public void shouldNotAuthenticateUser() {
		Auth service = new Auth();
		assertEquals(false, service.authenticateUser(1245, 54321));
	}
}

