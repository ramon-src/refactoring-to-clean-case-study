package entities;

import atm.Transaction;

public class TransactionController {

	private TransactionFactory factory;
	private Transaction transaction;
	
	public TransactionController(Integer transactionType) {
		factory = new TransactionFactory();
		transaction = factory.getTransaction(transactionType);
	}

	public Transaction getTransaction() {
		return transaction;
	}
	
	public void performTransaction() {
		getTransaction().execute();
	}
	
}
