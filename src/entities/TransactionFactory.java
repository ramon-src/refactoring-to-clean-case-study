package entities;

import atm.BalanceInquiry;
import atm.Deposit;
import atm.Transaction;
import atm.Withdrawal;

public class TransactionFactory {
	
	public Transaction getTransaction(Integer type) {

		if (type.equals(TransactionType.BALANCE_INQUIRY)) {
			return new BalanceInquiry();
		}
		if (type.equals(TransactionType.WITHDRAWAL)) {
			return new Withdrawal();
		}
		if (type.equals(TransactionType.DEPOSIT)) {
			return new Deposit();
		}

		return null;
	}
	
}
