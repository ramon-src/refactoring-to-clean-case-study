package entities;

import atm.Transaction;

public class TransactionController {

	private TransactionFactory factory;
	
	public TransactionController() {
		factory = new TransactionFactory();
	}
	
	public void performTransaction(int type) {
		Transaction transaction = factory.getTransaction(type);
		transaction.execute();
	}
	
}
