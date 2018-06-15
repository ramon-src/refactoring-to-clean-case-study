package entities;

import static org.junit.Assert.*;
import org.junit.Test;
import atm.ATM;

public class ATMTest {

	@Test
	public void shouldAuthenticateUser() {
		ATM atm = new ATM();
		atm.authenticateUser(12345, 54321);
		assertEquals(12345, atm.getCurrentAccountNumber());
	}	
	
	@Test
	public void shouldNotAuthenticateUser() {
		ATM atm = new ATM();
		atm.authenticateUser(125, 54321);
		assertEquals(0, atm.getCurrentAccountNumber());
	}
	
}
