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
		TransactionController transactionController = new TransactionController(2, 40);
		transactionController.performTransaction();
		
		assertEquals(1160, Bootstrapper.user().getAccount().getTotalBalance(), 0);
	}
	
	@Test
	public void shouldPerformTransactionDepositWithValue() {
		User user = new User();
		user.setAccount(Bootstrapper.getAccountRepo().find(12345));
		Bootstrapper.setUser(user);
		TransactionController transactionController = new TransactionController(3, 10000);		
		transactionController.performTransaction();

		assertEquals(1300, Bootstrapper.user().getAccount().getTotalBalance(), 0);
	}
}
