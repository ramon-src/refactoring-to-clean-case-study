package entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.Bootstrapper;

public class TransactionControllerTest extends SuiteTest{

	@Test
	public void shouldPerformTransactionWithdrawal() {
		User user = new User();
		user.setAccount(Bootstrapper.getAccountRepo().find(12345));
		Bootstrapper.setUser(user);
		TransactionController transactionController = new TransactionController();
		
		transactionController.performTransaction(2);

		assertEquals(1160, Bootstrapper.user().getAccount().getTotalBalance(), 0);
	}

}
