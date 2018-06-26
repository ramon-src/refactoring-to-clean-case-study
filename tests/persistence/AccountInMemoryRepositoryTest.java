

package persistence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import entities.Account;
import entities.SuiteTest;

public class AccountInMemoryRepositoryTest extends SuiteTest{
	
	@Test
	public void shouldFindAllAgents() {
		AccountInMemoryRepository agentRepo = new AccountInMemoryRepository();
		assertEquals(2, agentRepo.findAll().size());
	}
	
	@Test
	public void shouldFindOneAccount() {
		AccountInMemoryRepository agentRepo = new AccountInMemoryRepository();
		assertEquals(Account.class, agentRepo.find(12345).getClass());
	}
	
	@Test
	public void shouldGetNullWhenNotFindAccount() {
		AccountInMemoryRepository agentRepo = new AccountInMemoryRepository();
		assertEquals(null, agentRepo.find(1235));
	}
}
