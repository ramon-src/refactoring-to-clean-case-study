package entities;

import atm.BalanceInquiry;
import atm.CashDispenser;
import atm.Deposit;
import atm.Screen;
import atm.Transaction;
import atm.Withdrawal;
import services.AccountService;

public class TransactionFactory {
	
	public Transaction getTransaction(Integer type) {

		if (type.equals(TransactionType.BALANCE_INQUIRY)) {
			return new BalanceInquiry(0, new Screen(), new AccountService());
		}
		if (type.equals(TransactionType.WITHDRAWAL)) {
			return new Withdrawal(0, new Screen(), new AccountService(), new CashDispenser());
		}
		if (type.equals(TransactionType.DEPOSIT)) {
			return new Deposit();
		}

		return null;
	}
	
}
