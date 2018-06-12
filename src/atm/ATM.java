// ATM.java
// Represents an automated teller machine
package atm;

import services.AccountService;

public class ATM 
{
   private boolean userAuthenticated; // whether user is authenticated
   private int currentAccountNumber; // current user's account number
   private Screen screen; // ATM's screen
   private Keypad keypad; // ATM's keypad
   private CashDispenser cashDispenser; // ATM's cash dispenser
   private DepositSlot depositSlot; // ATM's deposit slot
   private AccountService accountService; // account information database

   // constants corresponding to main menu options
   private static final int BALANCE_INQUIRY = 1;
   private static final int WITHDRAWAL = 2;
   private static final int DEPOSIT = 3;
   private static final int EXIT = 4;

   // no-argument ATM constructor initializes instance variables
   public ATM() 
   {
      userAuthenticated = false; // user is not authenticated to start
      setCurrentAccountNumber(0); // no current account number to start
      screen = new Screen(); // create screen
      keypad = new Keypad(); // create keypad 
      cashDispenser = new CashDispenser(); // create cash dispenser
      depositSlot = new DepositSlot(); // create deposit slot
      accountService = new AccountService(); // create acct info database
   } // end no-argument ATM constructor

   // start ATM 
   public void run()
   {
      // welcome and authenticate user; perform transactions
      while (true)
      {
         // loop while user is not yet authenticated
         while (!userAuthenticated) 
         {
            screen.displayMessageLine("\nWelcome!");
            screen.displayMessage("\nPlease enter your account number: ");
            int accountNumber = keypad.getInput(); // input account number
            screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
            int pin = keypad.getInput(); // input PIN
            
            authenticateUser(accountNumber, pin); // authenticate user
         } // end while
         
         performTransactions(); // user is now authenticated 
         userAuthenticated = false; // reset before next ATM session
         setCurrentAccountNumber(0); // reset before next ATM session 
         screen.displayMessageLine("\nThank you! Goodbye!");
      } // end while   
   } // end method run

   // attempts to authenticate user against database
   public void authenticateUser(int accountNumber, int pin) 
   {      
      // set userAuthenticated to boolean value returned by database
      userAuthenticated = 
         accountService.authenticateUser(accountNumber, pin);
      
      // check whether authentication succeeded
      if (userAuthenticated)
      {
         setCurrentAccountNumber(accountNumber); // save user's account #
      } // end if
      else
         screen.displayMessageLine(
             "Invalid account number or PIN. Please try again.");
   } // end method authenticateUser

   // display the main menu and perform transactions
   private void performTransactions() 
   {
      // local variable to store transaction currently being processed
      Transaction currentTransaction = null;
      
      boolean userExited = false; // user has not chosen to exit

      // loop while user has not chosen option to exit system
      while (!userExited)
      {     
         // show main menu and get user selection
         int mainMenuSelection = displayMainMenu();

         // decide how to proceed based on user's menu selection
         switch (mainMenuSelection)
         {
            // user chose to perform one of three transaction types
            case BALANCE_INQUIRY: 
            case WITHDRAWAL: 
            case DEPOSIT:

               // initialize as new object of chosen type
               currentTransaction = 
                  createTransaction(mainMenuSelection);

               currentTransaction.execute(); // execute transaction
               break; 
            case EXIT: // user chose to terminate session
               screen.displayMessageLine("\nExiting the system...");
               userExited = true; // this ATM session should end
               break;
            default: // user did not enter an integer from 1-4
               screen.displayMessageLine(
                  "\nYou did not enter a valid selection. Try again.");
               break;
         } // end switch
      } // end while
   } // end method performTransactions
   
   // display the main menu and return an input selection
   private int displayMainMenu()
   {
      screen.displayMessageLine("\nMain menu:");
      screen.displayMessageLine("1 - View my balance");
      screen.displayMessageLine("2 - Withdraw cash");
      screen.displayMessageLine("3 - Deposit funds");
      screen.displayMessageLine("4 - Exit\n");
      screen.displayMessage("Enter a choice: ");
      return keypad.getInput(); // return user's selection
   } // end method displayMainMenu
         
   // return object of specified Transaction subclass
   public Transaction createTransaction(int type)
   {
      Transaction temp = null; // temporary Transaction variable
      
      // determine which type of Transaction to create     
      switch (type)
      {
         case BALANCE_INQUIRY: // create new BalanceInquiry transaction
            temp = new BalanceInquiry(
               getCurrentAccountNumber(), screen, accountService);
            break;
         case WITHDRAWAL: // create new Withdrawal transaction
            temp = new Withdrawal(getCurrentAccountNumber(), screen, 
               accountService, keypad, cashDispenser);
            break; 
         case DEPOSIT: // create new Deposit transaction
            temp = new Deposit(getCurrentAccountNumber(), screen, 
               accountService, keypad, depositSlot);
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
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/