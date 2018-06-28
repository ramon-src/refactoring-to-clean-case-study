package domain;

import services.AccountService;
import ui.Screen;

public class BalanceInquiry extends Transaction
{
	
   @Override
   public void execute()
   {
      AccountService bankDatabase = getAccountService();
      Screen screen = new Screen();

      double availableBalance = 
         bankDatabase.getAvailableBalance(getAccountNumber());
    
      double totalBalance = 
         bankDatabase.getTotalBalance(getAccountNumber());
    
      screen.displayMessageLine("\nBalance Information:");
      screen.displayMessage(" - Available balance: "); 
      screen.displayDollarAmount(availableBalance);
      screen.displayMessage("\n - Total balance:     ");
      screen.displayDollarAmount(totalBalance);
      screen.displayMessageLine("");
   }

	@Override
	public void setValue(Integer value) {
			
	}
}



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