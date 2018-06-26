package atm;

import main.Bootstrapper;
import services.AccountService;
import services.AuthService;
import ui.Input;
import ui.Keypad;

public class ATM {
	private int currentAccountNumber; // current user's account number
	private Screen screen; // ATM's screen
	private Input keypad; // ATM's keypad
	private CashDispenser cashDispenser; // ATM's cash dispenser
	private DepositSlot depositSlot; // ATM's deposit slot
	private AuthService auth;
	private AccountService accountService;

	// constants corresponding to main menu options
	private static final int BALANCE_INQUIRY = 1;
	private static final int WITHDRAWAL = 2;
	private static final int DEPOSIT = 3;
	private static final int EXIT = 4;

	public ATM() {
		setCurrentAccountNumber(0); // no current account number to start
		screen = new Screen(); // create screen
		keypad = new Keypad(); // create keypad
		cashDispenser = new CashDispenser(); // create cash dispenser
		depositSlot = new DepositSlot(); // create deposit slot
		accountService = new AccountService(); // create acct info database
		auth = new AuthService();
	} // end no-argument ATM constructor

	// start ATM
	public void run() {
		// welcome and authenticate user; perform transactions
		while (true) {
			// loop while user is not yet authenticated
			while (!Bootstrapper.user().isAuthenticated()) {
				screen.displayMessageLine("\nWelcome!");
				screen.displayMessage("\nPlease enter your account number: ");
				int accountNumber = keypad.getInput(); // input account number
				screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
				int pin = keypad.getInput(); // input PIN

				Boolean isAuthenticated = auth.authenticate(accountNumber, pin);
				if (!isAuthenticated)
					screen.displayMessageLine("Invalid account number or PIN. Please try again.");
			} // end while

			boolean userExited = false;
			while (!userExited) {
				int mainMenuSelection = displayMainMenu();
				userExited = performTransactions(mainMenuSelection);
			} // user is now authenticated
			Bootstrapper.setUser(null);
			screen.displayMessageLine("\nThank you! Goodbye!");
		} // end while
	} // end method run

	// display the main menu and perform transactions
	protected Boolean performTransactions(int mainMenuSelection) {
		Transaction currentTransaction = null;

		boolean userExited = false;

		switch (mainMenuSelection) {

		case BALANCE_INQUIRY:
		case WITHDRAWAL:
		case DEPOSIT:
			currentTransaction = createTransaction(mainMenuSelection);

			currentTransaction.execute();
			break;
		case EXIT:
			screen.displayMessageLine("\nExiting the system...");
			userExited = true;
			break;
		default:
			screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
			break;
		}
		return userExited;
	}

	// display the main menu and return an input selection
	private int displayMainMenu() {
		screen.displayMessageLine("\nMain menu:");
		screen.displayMessageLine("1 - View my balance");
		screen.displayMessageLine("2 - Withdraw cash");
		screen.displayMessageLine("3 - Deposit funds");
		screen.displayMessageLine("4 - Exit\n");
		screen.displayMessage("Enter a choice: ");
		return keypad.getInput(); // return user's selection
	} // end method displayMainMenu

	// return object of specified Transaction subclass
	public Transaction createTransaction(int type) {
		Transaction temp = null; // temporary Transaction variable

		// determine which type of Transaction to create
		Integer numberAccount = Bootstrapper.user().getAccount().getNumber();
		switch (type) {
		case BALANCE_INQUIRY: // create new BalanceInquiry transaction
			temp = new BalanceInquiry(numberAccount, screen, accountService);
			break;
		case WITHDRAWAL: // create new Withdrawal transaction
			temp = new Withdrawal(numberAccount, screen, accountService, cashDispenser);
			break;
		case DEPOSIT: // create new Deposit transaction
			temp = new Deposit(numberAccount, screen, accountService, depositSlot);
			break;
		} // end switch

		return temp; // return the newly created object
	} // end method createTransaction

	public int getCurrentAccountNumber() {
		return currentAccountNumber;
	}

	public void setCurrentAccountNumber(int currentAccountNumber) {
		this.currentAccountNumber = currentAccountNumber;
	}
} // end class ATM

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 *************************************************************************/