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
		Screen screen = getScreen();

		amount = promptForDepositAmount();

		if (amount != CANCELED) {

			screen.displayMessage("\nPlease insert a deposit envelope containing ");
			screen.displayDollarAmount(amount);
			screen.displayMessageLine(".");

			boolean envelopeReceived = depositSlot.isEnvelopeReceived();

			if (envelopeReceived) {
				screen.displayMessageLine("\nYour envelope has been "
						+ "received.\nNOTE: The money just deposited will not "
						+ "be available until we verify the amount of any " + "enclosed cash and your checks clear.");

				getAccountService().credit(getAccountNumber(), amount);
			} else {
				screen.displayMessageLine(
						"\nYou did not insert an " + "envelope, so the ATM has canceled your transaction.");
			}
		} else {
			screen.displayMessageLine("\nCanceling transaction...");
		}
	}

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
}
