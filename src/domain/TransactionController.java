package domain;

public class TransactionController {

	private TransactionFactory factory;
	private Transaction transaction;

	public TransactionController(Integer transactionType, Integer value) {
		factory = new TransactionFactory();
		transaction = factory.getTransaction(transactionType);
		transaction.setValue(value);
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
	
	public void performTransaction() {
		getTransaction().execute();
	}
	
}
