package domain;

public class Withdrawal extends Transaction {
	private int amount;

	@Override
	public void setValue(Integer value) {
		this.amount = value;		
	}
	
	@Override
	public void execute() {
		getAccountService().debit(getAccountNumber(), amount);
	}
}
