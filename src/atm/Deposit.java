// Deposit.java
// Represents a deposit ATM transaction
package atm;

import services.AccountService;
import ui.Input;
import ui.Keypad;

public class Deposit extends Transaction {
	private double amount;
	private Input keypad;
	private DepositSlot depositSlot;
	private final static int CANCELED = 0;

	public Deposit() {
	
		super(0, new Screen(), new AccountService());

		setKeypad(new Keypad());
		depositSlot = new DepositSlot();
	}
	
	@Override
	public void execute() {
		AccountService bankDatabase = getBankDatabase(); // get reference
		Screen screen = getScreen(); // get reference

		amount = promptForDepositAmount(); // get deposit amount from user

		// check whether user entered a deposit amount or canceled
		if (amount != CANCELED) {
			// request deposit envelope containing specified amount
			screen.displayMessage("\nPlease insert a deposit envelope containing ");
			screen.displayDollarAmount(amount);
			screen.displayMessageLine(".");

			// receive deposit envelope
			boolean envelopeReceived = depositSlot.isEnvelopeReceived();

			// check whether deposit envelope was received
			if (envelopeReceived) {
				screen.displayMessageLine("\nYour envelope has been "
						+ "received.\nNOTE: The money just deposited will not "
						+ "be available until we verify the amount of any " + "enclosed cash and your checks clear.");

				// credit account to reflect the deposit
				bankDatabase.credit(getAccountNumber(), amount);
			} // end if
			else // deposit envelope not received
			{
				screen.displayMessageLine(
						"\nYou did not insert an " + "envelope, so the ATM has canceled your transaction.");
			} // end else
		} // end if
		else // user canceled instead of entering amount
		{
			screen.displayMessageLine("\nCanceling transaction...");
		} // end else
	} // end method execute

	private double promptForDepositAmount() {
		Screen screen = getScreen();

		screen.displayMessage("\nPlease enter a deposit amount in " + "CENTS (or 0 to cancel): ");
		int input = getKeypad().getInput(); // receive input of deposit amount

		// check whether the user canceled or entered a valid amount
		if (input == CANCELED)
			return CANCELED;
		else {
			return (double) input / 100; // return dollar amount
		} // end else
	} // end method promptForDepositAmount

	public Input getKeypad() {
		return keypad;
	}

	public void setKeypad(Input keypad) {
		this.keypad = keypad;
	}
} // end class Deposit
