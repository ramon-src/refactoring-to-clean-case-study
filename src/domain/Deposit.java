package domain;

public class Deposit extends Transaction {
	private double amount;

	@Override
	public void setValue(Integer value) {
		this.amount = (double) value / 100;
	}

	@Override
	public void execute() {
		getAccountService().credit(getAccountNumber(), amount);
	}
}
