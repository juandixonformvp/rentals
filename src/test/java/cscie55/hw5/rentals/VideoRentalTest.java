package cscie55.hw5.rentals;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class VideoRentalTest {
	@Test
	public void testRentalCheckout() throws VideoException {
		Video video = new Video("Moana", 2014);
		Account account = new Account("Bob", "Lee", "anon@gmail.com");
		VideoRental videoRental = new VideoRental(video, account);
		assertEquals(false, video.isAvailable());
	}
}
