package entities;

import org.junit.Before;

import main.Bootstrapper;

public class SuiteTest {

	@Before
	public void initialize() {
		Bootstrapper.initDependencies();
	}
}
