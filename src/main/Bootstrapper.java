
package main;

import atm.ATM;
import entities.User;

public class Bootstrapper
{
	private static User user;
	
	public static void main(String[] args) {
		setUser(new User());
		
		ATM theATM = new ATM();
		theATM.run();
	}
	
	public static User user() {
		return user;		
	}
	
	public static void setUser(User user) {
		Bootstrapper.user = user;		
	}
}