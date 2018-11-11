package cscie55.hw5.rentals;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class AccountTest {
	@Test
	public void testAccount() {
	}

	@Test
	public void testEqualAccount() {
		Account account1 = new Account("Bob", "Lee", "anon@gmail.com");
		Account account2 = new Account("Bob", "Lee", "anon@gmail.com");
		assertEquals(true, account1.equals(account2));
	}

	@Test
	public void testEqualFname() {
		Account account1 = new Account("Bob", "Lee", "anon@gmail.com");
		Account account2 = new Account("Bobb", "Lee", "anon@gmail.com");
		assertEquals(false, account1.equals(account2));
	}

	@Test
	public void testEqualLname() {
		Account account1 = new Account("Bob", "Lee", "anon@gmail.com");
		Account account2 = new Account("Bob", "Li", "anon@gmail.com");
		assertEquals(false, account1.equals(account2));
	}

	@Test
	public void testEqualEmail() {
		Account account1 = new Account("Bob", "Lee", "anon@gmail.com");
		Account account2 = new Account("Bob", "Lee", "anon@yahoo.com");
		assertEquals(false, account1.equals(account2));
	}

	@Test
	public void testHasRental() throws VideoException {
		Video video1 = new Video("Moana", 2014);
		Video video2 = new Video("Big", 1988);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental1 = new VideoRental(video1, account, LocalDate.now().plusDays(5));
		VideoRental videoRental2 = new VideoRental(video2, account, LocalDate.now().plusDays(5));
		assertEquals(true, account.hasOpenRental("Moana"));
		assertEquals(true, account.hasOpenRental("Big"));
		assertEquals(false, account.hasOpenRental("Gladiator"));
	}

	@Test
	public void testHasOverdueRental() throws VideoException {
		Video video1 = new Video("Moana", 2014);
		Video video2 = new Video("Big", 1988);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental1 = new VideoRental(video1, account, LocalDate.now().plusDays(5));
		VideoRental videoRental2 = new VideoRental(video2, account, LocalDate.now().plusDays(5));
		assertEquals(true, account.getOverdueRentals().size() > 0 );
//		has bug, these videos are not overdue, but videoRental isOverdue method has bug
	}

	@Test
	public void testSettleRental() throws VideoException {
		Video video1 = new Video("Moana", 2014);
		Video video2 = new Video("Big", 1988);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental1 = new VideoRental(video1, account, LocalDate.now().plusDays(5));
		assertEquals(false, video1.isAvailable());
		assertEquals(true, video2.isAvailable());
		VideoRental videoRental2 = new VideoRental(video2, account, LocalDate.now().plusDays(5));
		assertEquals(false, video2.isAvailable());
		assertEquals(2, account.getNumberOpenRentals());
		assertEquals(0, account.getNumberClosedRentals());
		account.settleRental("Moana");
		assertEquals(true, video1.isAvailable());
		assertEquals(false, videoRental1.isOpen()); // bug, video associated with account is returned but videoRental is still open
		assertEquals(1, account.getNumberOpenRentals());
		assertEquals(1, account.getNumberClosedRentals());
	}



}
