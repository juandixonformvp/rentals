package cscie55.hw5.rentals;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.Period;

public class VideoRentalTest {
	// tests that rentalCheckout makes Video available
	@Test
	public void testRentalCheckout() throws VideoException {
		Video video = new Video("Moana", 2014);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental = new VideoRental(video, account);
		assertEquals(false, video.isAvailable());
	}

	// double checks that the DateDue is 14 days after the current date
	@Test
	public void testDueDate() throws VideoException {
		Video video = new Video("Moana", 2014);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental = new VideoRental(video, account);
		Period period = Period.between( LocalDate.now() , videoRental.getDateDue());
		assertEquals(14, period.getDays());
	}

	// checks that a custom due date can be used in the constructor
	@Test
	public void testCustomDueDate() throws VideoException {
		Video video = new Video("Moana", 2014);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental = new VideoRental(video, account, LocalDate.now().plusDays(5));
		Period period = Period.between( LocalDate.now() , videoRental.getDateDue());
		assertEquals(5, period.getDays());
	}

	// checks that equals method fails when due dates differ
	@Test
	public void testDateEquals() throws VideoException {
		Video video1 = new Video("Moana", 2014);
		Account account1 = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental1 = new VideoRental(video1, account1, LocalDate.now().plusDays(5));
		Video video2 = new Video("Moana", 2014);
		Account account2 = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental2 = new VideoRental(video2, account2, LocalDate.now().plusDays(6));
		assertEquals(false, videoRental1.equals(videoRental2));
	}

	// checks that equals method fails when Video differs
	@Test
	public void testVideoEquals() throws VideoException {
		Video video1 = new Video("Moana", 2014);
		Account account1 = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental1 = new VideoRental(video1, account1, LocalDate.now().plusDays(5));
		Video video2 = new Video("Moana", 2015);
		Account account2 = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental2 = new VideoRental(video2, account2, LocalDate.now().plusDays(5));
		assertEquals(false, videoRental1.equals(videoRental2));
	}

	// checks that equals method fails when Account differs
	@Test
	public void testAccountEquals() throws VideoException {
		Video video1 = new Video("Moana", 2014);
		Account account1 = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental1 = new VideoRental(video1, account1, LocalDate.now().plusDays(5));
		Video video2 = new Video("Moana", 2014);
		Account account2 = new Account("Bob", "Li", "anon@gmail.com");
		VideoRental videoRental2 = new VideoRental(video2, account2, LocalDate.now().plusDays(5));
		assertEquals(false, videoRental1.equals(videoRental2));
	}

	// checks that new VideoRental instances return true for isOpen method
	@Test
	public void testIsOpen() throws VideoException {
		Video video = new Video("Moana", 2014);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental = new VideoRental(video, account, LocalDate.now().plusDays(5));
		assertEquals(true, videoRental.isOpen());
	}

	// checks that new returnRental method causes isOpen method to return false
	@Test
	public void testRentalReturn() throws VideoException {
		Video video = new Video("Moana", 2014);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental = new VideoRental(video, account, LocalDate.now().plusDays(5));
		videoRental.rentalReturn();
		assertEquals(false, videoRental.isOpen());
	}

	// checks if today's date is after due date isOverDue returns true, bug found
    @Test
    public void testIsOverdue() throws VideoException {
        Video video = new Video("Moana", 2014);
        Account account = new Account("Bob", "Lee", "anon@gmail.com");
        VideoRental videoRental = new VideoRental(video, account, LocalDate.now().plusDays(5));
        Period period = Period.between( LocalDate.now() , videoRental.getDateDue());
        assertEquals(videoRental.isOverDue(), period.getDays() >= 0 ); // bug in code, if the period between due date and today's date is positive, then isOverDue should be false
    }

}
