package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import entities.SuiteTest;

public class TransactionFactoryTest extends SuiteTest{
	
	public TransactionFactory factory;
	
	@Before
	public void initialize(){ 
		super.initialize();
		factory = new TransactionFactory();
	}
	
	@Test
	public void shouldCreateBalanceTransaction() {
		assertEquals(BalanceInquiry.class, factory.getTransaction(1).getClass());
	}

	@Test
	public void shouldCreateWithdrawalTransaction() {
		assertEquals(Withdrawal.class, factory.getTransaction(2).getClass());
	}

	@Test
	public void shouldCreateDepositTransaction() {
		assertEquals(Deposit.class, factory.getTransaction(3).getClass());
	}

	@Test
	public void shouldExitWhenTypeIsNotTransaction() {
		assertEquals(null, factory.getTransaction(4));
	}
	
}
