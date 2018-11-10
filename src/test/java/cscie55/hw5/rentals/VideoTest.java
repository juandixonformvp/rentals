package cscie55.hw5.rentals;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class VideoTest { 
	@Test
	public void testVideo() {
	}

	@Test
	public void testEmptyTitle() {

		try {
			Video video = new Video("",2005);
			fail();
		} catch (VideoException e) {
			// Expected
		}
	}

	@Test
	public void testTooOldYear() {

		try {
			Video video = new Video("Big",1899);
			fail();
		} catch (VideoException e) {
			// Expected
		}
	}

	@Test
	public void testTooNewYear() {

		try {
			Video video = new Video("Big",2020);
			fail();
		} catch (VideoException e) {
			// Expected
		}
	}
}
