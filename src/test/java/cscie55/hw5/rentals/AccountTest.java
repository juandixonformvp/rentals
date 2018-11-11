package cscie55.hw5.rentals;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class AccountTest {
	@Test
	public void testAccount() {
	}

	@Test
	public void testEqualAccount() {
		Account account1 = new Account("Bob", "", "anon@gmail.com");
		Account account2 = new Account("Bob", "", "anon@gmail.com");
		assertEquals(true, account1.equals(account2));
	}

	@Test
	public void testEqualFname() {
		Account account1 = new Account("Bob", "", "anon@gmail.com");
		Account account2 = new Account("Bobb", "", "anon@gmail.com");
		assertEquals(false, account1.equals(account2));
	}


}
