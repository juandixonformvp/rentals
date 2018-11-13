package cscie55.hw5.rentals;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;


public class AccountTest {
	// tests the equals method
	@Test
	public void testEqualAccount() {
		Account account1 = new Account("Bob", "Lee", "anon@gmail.com");
		Account account2 = new Account("Bob", "Lee", "anon@gmail.com");
		assertEquals(true, account1.equals(account2));
	}

	// tests that equals returns false when fname differs
	@Test
	public void testEqualFname() {
		Account account1 = new Account("Bob", "Lee", "anon@gmail.com");
		Account account2 = new Account("Bobb", "Lee", "anon@gmail.com");
		assertEquals(false, account1.equals(account2));
	}

	// tests that equals returns false when lname differs
	@Test
	public void testEqualLname() {
		Account account1 = new Account("Bob", "Lee", "anon@gmail.com");
		Account account2 = new Account("Bob", "Li", "anon@gmail.com");
		assertEquals(false, account1.equals(account2));
	}

	// tests that equals returns false when email differs
	@Test
	public void testEqualEmail() {
		Account account1 = new Account("Bob", "Lee", "anon@gmail.com");
		Account account2 = new Account("Bob", "Lee", "anon@yahoo.com");
		assertEquals(false, account1.equals(account2));
	}

	// tests the hasOpenRental method
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

	// tests the overdueRental method, this method has a bug
	@Test
	public void testHasOverdueRental() throws VideoException {
		Video video1 = new Video("Moana", 2014);
		Video video2 = new Video("Big", 1988);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental1 = new VideoRental(video1, account, LocalDate.now().plusDays(5));
		VideoRental videoRental2 = new VideoRental(video2, account, LocalDate.now().plusDays(5));
		assertEquals(true, account.getOverdueRentals().size() > 0 ); // has bug, these videos are not overdue, but videoRental isOverdue method has bug
	}

	// tests that accounts can be properly settled by video title
	@Test
	public void testSettleRentalTitle() throws VideoException {
		Video video1 = new Video("Avatar", 2010);
		Video video2 = new Video("Ghost", 1992);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental1 = new VideoRental(video1, account);
		assertEquals(false, video1.isAvailable());
		assertEquals(true, video2.isAvailable());
		VideoRental videoRental2 = new VideoRental(video2, account);
		assertEquals(false, video2.isAvailable());
		assertEquals(2, account.getNumberOpenRentals());
		assertEquals(0, account.getNumberClosedRentals());
		account.settleRental("Avatar");
		assertEquals(true, video1.isAvailable());
		assertEquals(false, videoRental1.isOpen());
		assertEquals(1, account.getNumberOpenRentals());
		assertEquals(1, account.getNumberClosedRentals());
	}

	// tests that accounts can be properly settled by videoRental
	@Test
	public void testSettleVideoRental() throws VideoException {
		Video video1 = new Video("Moana", 2014);
		Video video2 = new Video("Big", 1988);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental1 = new VideoRental(video1, account);
		VideoRental videoRental2 = new VideoRental(video2, account);
		assertEquals(false, video1.isAvailable());
		assertEquals(false, video2.isAvailable());
		account.settleRental(videoRental2);
		assertEquals(false, video1.isAvailable());
		assertEquals(true, video2.isAvailable());
		assertEquals(true, videoRental1.isOpen());
		assertEquals(false, videoRental2.isOpen());
		assertEquals(1, account.getNumberOpenRentals());
		assertEquals(1, account.getNumberClosedRentals());
	}

	// tests the settleRenatls method
	@Test
	public void testSettleRentals() throws VideoException {
		Video video1 = new Video("Moana", 2014);
		Video video2 = new Video("Big", 1988);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental1 = new VideoRental(video1, account, LocalDate.now().plusDays(5));
		VideoRental videoRental2 = new VideoRental(video2, account, LocalDate.now().plusDays(10));
		assertEquals(false, video1.isAvailable());
		assertEquals(false, video2.isAvailable());
		account.settleRentals();
		assertEquals(true, video1.isAvailable());
		assertEquals(true, video2.isAvailable());
		assertEquals(false, videoRental1.isOpen());
		assertEquals(false, videoRental2.isOpen());
		assertEquals(0, account.getNumberOpenRentals());
		assertEquals(2, account.getNumberClosedRentals());
	}


	// tests settleRental by title when the user enters a custom due date, bug found
	@Test
	public void testSettleRentalTitleCustomDate() throws VideoException {
		Video video1 = new Video("Avatar", 2010);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental1 = new VideoRental(video1, account, LocalDate.now().plusDays(5));
		assertEquals(false, video1.isAvailable());;
		assertEquals(1, account.getNumberOpenRentals());
		assertEquals(0, account.getNumberClosedRentals());
		account.settleRental("Avatar");
		assertEquals(true, video1.isAvailable());
		assertEquals(false, videoRental1.isOpen()); // bug, video associated with account is returned but videoRental is still open
		assertEquals(1, account.getNumberOpenRentals()); // bug, there should be only 1 open rental, it is not removing the title from open rentals
		assertEquals(1, account.getNumberClosedRentals());
	}


	// tests settleRental by videoRental when the user enters a custom due date, bug found
	@Test
	public void testSettleVideoRentalCustomDate() throws VideoException {
		Video video1 = new Video("Moana", 2014);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental1 = new VideoRental(video1, account, LocalDate.now().plusDays(5));
		account.settleRental(videoRental1);
		assertEquals(false, video1.isAvailable());
		assertEquals(false, video1.isAvailable());
		assertEquals(true, videoRental1.isOpen()); // bug, video associated with account is returned but videoRental is still open
		assertEquals(1, account.getNumberOpenRentals()); // bug, should expect 0, no rental should be open
		assertEquals(0, account.getNumberClosedRentals()); // bug, should expect 1, 1 rental should be closed
	}
}
