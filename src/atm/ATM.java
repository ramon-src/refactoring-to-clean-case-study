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


	public ATM() {
		screen = new Screen();
		keypad = new Keypad();
		auth = new AuthService();
	}

	public void run() {

		while (true) {

			while (!Bootstrapper.user().isAuthenticated()) {
				screen.displayMessageLine("\nWelcome!");
				screen.displayMessage("\nPlease enter your account number: ");
				int accountNumber = keypad.getInput();
				screen.displayMessage("\nEnter your PIN: ");
				int pin = keypad.getInput();

				Boolean isAuthenticated = auth.authenticate(accountNumber, pin);
				if (!isAuthenticated)
					screen.displayMessageLine("Invalid account number or PIN. Please try again.");
			}

			boolean userExited = false;
			while (!userExited) {
				int menuSelected = displayMainMenu();
				
				if(menuSelected == 4) {
					screen.displayMessageLine("\nExiting the system...");
					break;
				}

				if(menuSelected < 4) {
					TransactionController transactionController = new TransactionController(menuSelected);
					transactionController.performTransaction();
				}
				
				if(menuSelected > 4)
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
		return keypad.getInput();
	}
	
}
