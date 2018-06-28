package entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import atm.Withdrawal;
import main.Bootstrapper;
import mocks.KeypadMock;

public class ATMTest extends SuiteTest{

	@Test
	public void shouldPerformWithdrawalAction() {
		Account account = new Account(12345, 54321L, 10, 10);
		User user = new User();
		user.setAccount(account);
		Bootstrapper.setUser(user);
		TransactionController transactionController = new TransactionController(2);
		Withdrawal transaction = (Withdrawal) transactionController.getTransaction();
		KeypadMock keypad = new KeypadMock();
		keypad.setInput(2);
		
		transaction.setKeypad(keypad);
		transaction.execute();
		Double balanceResult = transaction.getAccountService().getTotalBalance(12345);
		assertEquals(1160, balanceResult, 0);
	}

}
