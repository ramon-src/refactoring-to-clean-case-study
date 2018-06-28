package atm;

import entities.TransactionController;
import main.Bootstrapper;
import services.AuthService;
import ui.Input;
import ui.Keypad;

public class ATM {
	private Screen screen;
	private Input keypad;
	private AuthService auth;
	private DepositSlotService depositSlotService;

	public ATM() {
		screen = new Screen();
		setKeypad(new Keypad());
		auth = new AuthService();
		depositSlotService = new DepositSlotService();
		
	}

	public void run() {

		while (true) {

			while (!Bootstrapper.user().isAuthenticated()) {
				screen.displayMessageLine("\nWelcome!");
				screen.displayMessage("\nPlease enter your account number: ");
				int accountNumber = getKeypad().getInput();
				screen.displayMessage("\nEnter your PIN: ");
				int pin = getKeypad().getInput();

				Boolean isAuthenticated = auth.authenticate(accountNumber, pin);
				if (!isAuthenticated)
					screen.displayMessageLine("Invalid account number or PIN. Please try again.");
			}

			boolean userExited = false;
			while (!userExited) {
				int menuSelected = displayMainMenu();

				if (menuSelected == 4) {
					screen.displayMessageLine("\nExiting the system...");
					break;
				}

				if (menuSelected < 4) {
					switch (menuSelected) {
					case 1:
						new TransactionController(menuSelected).performTransaction();
						break;
					case 2:
						new TransactionController(menuSelected).performTransaction();
						break;
					case 3:
						screen.displayMessage("\nPlease enter a deposit amount in CENTS (or 0 to cancel): ");
						int amountValue = getKeypad().getInput();
						
						if(amountValue == 0) {
							screen.displayMessageLine("\nCanceling transaction...");
							break;
						}
						screen.displayMessage("\nPlease insert a deposit envelope containing ");
						screen.displayDollarAmount(amountValue);
						screen.displayMessageLine(".");

						if (depositSlotService.isEnvelopeReceived()) {
							screen.displayMessageLine("\nYour envelope has been "
									+ "received.\nNOTE: The money just deposited will not "
									+ "be available until we verify the amount of any " + "enclosed cash and your checks clear.");
							new TransactionController(menuSelected, amountValue).performTransaction();
						} else {
							screen.displayMessageLine("\nYou did not insert an envelope, so the ATM has canceled your transaction.");
						}
						break;

					}
				}
				if (menuSelected > 4)
					screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
			}

			Bootstrapper.setUser(null);
			screen.displayMessageLine("\nThank you! Goodbye!");
		}
	}

	private int displayMainMenu() {
		screen.displayMessageLine("\nMain menu:");
		screen.displayMessageLine("1 - View my balance");
		screen.displayMessageLine("2 - Withdraw cash");
		screen.displayMessageLine("3 - Deposit funds");
		screen.displayMessageLine("4 - Exit\n");
		screen.displayMessage("Enter a choice: ");
		return getKeypad().getInput();
	}

	public Input getKeypad() {
		return keypad;
	}

	public void setKeypad(Input keypad) {
		this.keypad = keypad;
	}

}
