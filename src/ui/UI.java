package ui;

import domain.TransactionController;
import main.Bootstrapper;
import services.AccountService;
import services.AuthService;
import services.CashDispenserService;
import services.DepositSlotService;

public class UI {
	private Screen screen;
	private Input keypad;
	private AuthService auth;
	private DepositSlotService depositSlotService;
	private AccountService accountService;
	private CashDispenserService cashDispenser;

	public UI() {
		screen = new Screen();
		setKeypad(new Keypad());
		auth = new AuthService();
		depositSlotService = new DepositSlotService();
		accountService = new AccountService();
		cashDispenser = new CashDispenserService();
	}

	public void menu() {
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
						new TransactionController(menuSelected, null).performTransaction();
						break;
					case 2:
						Integer amount = 0;
						Boolean cashDispensed = false;
						do {
							Integer[] amounts = { 0, 20, 40, 60, 100, 200 };
							while (amount == 0) {
								screen.displayMessageLine("\nWithdrawal Menu:");
								screen.displayMessageLine("1 - $20");
								screen.displayMessageLine("2 - $40");
								screen.displayMessageLine("3 - $60");
								screen.displayMessageLine("4 - $100");
								screen.displayMessageLine("5 - $200");
								screen.displayMessageLine("6 - Cancel transaction");
								screen.displayMessage("\nChoose a withdrawal amount: ");

								amount = getKeypad().getInput();

								if (amount == 0 || amount > 6) {
									screen.displayMessageLine("\nInvalid selection. Try again.");
									amount = 0;
									break;
								}
							}

							if (amount == 6) {
								screen.displayMessageLine("\nCanceling transaction...");
								screen.displayMessageLine("\nBye!");
								return;
							}

							amount = amounts[amount];

							Double balance = accountService
									.getAvailableBalance(Bootstrapper.user().getAccount().getNumber());

							if (amount <= balance) {
								if (cashDispenser.isSufficientCashAvailable(amount)) {
									new TransactionController(menuSelected, amount).performTransaction();
									cashDispenser.dispenseCash(amount);
									cashDispensed = true;

									screen.displayMessageLine(
											"\nYour cash has been dispensed. Please take your cash now.");
								} else {
									screen.displayMessageLine(
											"\nInsufficient cash available in the ATM.\n\nPlease choose a smaller amount.");
								}
							} else {
								screen.displayMessageLine(
										"\nInsufficient funds in your account.\n\nPlease choose a smaller amount.");
							}
						} while (!cashDispensed);

						break;
					case 3:
						screen.displayMessage("\nPlease enter a deposit amount in CENTS (or 0 to cancel): ");
						int amountValue = getKeypad().getInput();

						if (amountValue == 0) {
							screen.displayMessageLine("\nCanceling transaction...");
							break;
						}
						screen.displayMessage("\nPlease insert a deposit envelope containing ");
						screen.displayDollarAmount(amountValue);
						screen.displayMessageLine(".");

						if (depositSlotService.isEnvelopeReceived()) {
							screen.displayMessageLine(
									"\nYour envelope has been " + "received.\nNOTE: The money just deposited will not "
											+ "be available until we verify the amount of any "
											+ "enclosed cash and your checks clear.");
							new TransactionController(menuSelected, amountValue).performTransaction();
						} else {
							screen.displayMessageLine(
									"\nYou did not insert an envelope, so the ATM has canceled your transaction.");
						}
						break;

					}
				}
				if (menuSelected > 4)
					screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
			}

			Bootstrapper.initDependencies();
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
