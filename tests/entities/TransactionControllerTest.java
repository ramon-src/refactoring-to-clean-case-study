package entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import atm.ATM;
import atm.Withdrawal;
import main.Bootstrapper;
import mocks.KeypadMock;

public class TransactionControllerTest {

	@Test
	public void shouldPerformTransactionWithdrawal() {
		ATM atm = new ATM();
		Account account = new Account(12345, 54321L, 10, 10);
		User user = new User();
		user.setAccount(account);
		Bootstrapper.setUser(user);
		TransactionController transactionController = new TransactionController();
		
		transactionController.performTransaction(2);

		assertEquals(1160, account.getTotalBalance(), 0);
	}

}
