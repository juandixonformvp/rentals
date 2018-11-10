package cscie55.hw5.rentals;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class VideoTest { 
	@Test
	public void testVideo() {
	}

	public void testEmptyTitle() {

		try {
			Video video = new Video(" ", 2005);
			fail();
		} catch (VideoException e) {
			// Expected
		}
	}
}
