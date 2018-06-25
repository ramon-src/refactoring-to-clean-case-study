// BankDatabase.java
// Represents the bank account information database 
package services;

import entities.Account;
import entities.DataRepository;
import persistence.AccountInMemoryRepository;

public class AccountService
{
   private Account accounts[]; // array of Accounts
   private DataRepository<Account> accountRepo;
   // no-argument BankDatabase constructor initializes accounts
   public AccountService()
   {
	   accountRepo = new AccountInMemoryRepository();
   } // end no-argument BankDatabase constructor
   
   public Account find(int userAccountNumber)
   {
	   return accountRepo.find(userAccountNumber);
   }
   
   public Boolean validatePin(Account account, Long pin) {
	   return account.getPin() == pin;
   }
   
   // return available balance of Account with specified account number
   public double getAvailableBalance(int userAccountNumber)
   {
      return accountRepo.find(userAccountNumber).getAvailableBalance();
   } // end method getAvailableBalance

   // return total balance of Account with specified account number
   public double getTotalBalance(int userAccountNumber)
   {
      return accountRepo.find(userAccountNumber).getTotalBalance();
   } // end method getTotalBalance

   // credit an amount to Account with specified account number
   public void credit(int userAccountNumber, double amount)
   {
	   accountRepo.find(userAccountNumber).credit(amount);
   } // end method credit

   // debit an amount from Account with specified account number
   public void debit(int userAccountNumber, double amount)
   {
	   accountRepo.find(userAccountNumber).debit(amount);
   } // end method debit
} // end class BankDatabase



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