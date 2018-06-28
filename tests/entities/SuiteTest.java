package entities;

import org.junit.Before;

import domain.User;
import main.Bootstrapper;

public class SuiteTest {

	@Before
	public void initialize() {
		Bootstrapper.initDependencies();
		Account account = new Account(12345, 54321L, 10, 10);
		User user = new User();
		user.setAccount(account);
		Bootstrapper.setUser(user);
	}
}
