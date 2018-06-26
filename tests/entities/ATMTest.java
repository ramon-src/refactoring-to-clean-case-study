package entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import atm.ATM;
import atm.Withdrawal;
import main.Bootstrapper;
import mocks.KeypadMock;

public class ATMTest {

	@Test
	public void shouldPerformWithdrawalAction() {
		ATM atm = new ATM();
		Account account = new Account(12345, 54321L, 10, 10);
		User user = new User();
		user.setAccount(account);
		Bootstrapper.setUser(user);
		Withdrawal transaction = (Withdrawal) atm.createTransaction(2);
		KeypadMock keypad = new KeypadMock();
		keypad.setInput(2);
		
		transaction.setKeypad(keypad);
		transaction.execute();
		Double balanceResult = transaction.getBankDatabase().getTotalBalance(12345);
		assertEquals(1160, balanceResult, 0);
	}
}
