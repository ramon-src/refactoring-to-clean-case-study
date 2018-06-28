package entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import atm.Deposit;
import atm.Withdrawal;
import main.Bootstrapper;
import mocks.KeypadMock;

public class TransactionControllerTest extends SuiteTest{

	@Test
	public void shouldPerformTransactionWithdrawal() {
		User user = new User();
		user.setAccount(Bootstrapper.getAccountRepo().find(12345));
		Bootstrapper.setUser(user);
		TransactionController transactionController = new TransactionController(2);
		Withdrawal withdrawal = (Withdrawal) transactionController.getTransaction();
		KeypadMock keyPad = new KeypadMock();
		keyPad.setInput(2);
		withdrawal.setKeypad(keyPad);
		
		transactionController.performTransaction();

		assertEquals(1160, Bootstrapper.user().getAccount().getTotalBalance(), 0);
	}

	@Test
	public void shouldPerformTransactionDeposit() {
		User user = new User();
		user.setAccount(Bootstrapper.getAccountRepo().find(12345));
		Bootstrapper.setUser(user);
		TransactionController transactionController = new TransactionController(3);
		Deposit deposit = (Deposit) transactionController.getTransaction();
		KeypadMock keyPad = new KeypadMock();
		keyPad.setInput(10000);
		deposit.setKeypad(keyPad);
		
		transactionController.performTransaction();

		assertEquals(1300, Bootstrapper.user().getAccount().getTotalBalance(), 0);
	}
}
