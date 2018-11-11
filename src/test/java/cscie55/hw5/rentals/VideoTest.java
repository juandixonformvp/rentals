package cscie55.hw5.rentals;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class VideoTest { 

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

    @Test
    public void testEqualVideo() throws VideoException {
	    Video video1 = new Video("Moana", 2014);
        Video video2 = new Video("Moana", 2014);
        assertEquals(true, video1.equals(video2));
    }

    @Test
    public void testUnequalYear() throws VideoException {
        Video video1 = new Video("Moana", 2014);
        Video video2 = new Video("Moana", 2015);
        assertEquals(false, video1.equals(video2));
    }

    @Test
    public void testUnequalName() throws VideoException {
        Video video1 = new Video("Moana", 2014);
        Video video2 = new Video("Moanaa", 2014);
        assertEquals(false, video1.equals(video2));
    }

    @Test
    public void testIsAvailable() throws VideoException {
        Video video = new Video("Moana", 2014);
		assertEquals(true, video.isAvailable());
    }

	@Test
	public void testCheckout() throws VideoException {
		Video video = new Video("Moana", 2014);
		video.checkOut();
		assertEquals(false, video.isAvailable());
	}

    @Test
    public void testCheckoutException() throws VideoException {
        Video video = new Video("Moana", 2014);
        video.checkOut();
        try {
            video.checkOut();
            fail();
        } catch (RuntimeException e) {
            // Expected
        }
    }

	@Test
	public void testCheckIn() throws VideoException {
		Video video = new Video("Moana", 2014);
		video.checkIn();
		assertEquals(true, video.isAvailable());
	}

	@Test
	public void testRemoveFromStock() throws VideoException {
		Video video = new Video("Moana", 2014);
		video.removeFromStock();
		assertFalse(video.isAvailable());
		assertFalse(video.isNotAvailable());
	}

    @Test
    public void testReplaceToStock() throws VideoException {
        Video video = new Video("Moana", 2014);
        video.removeFromStock();
        video.replaceToStock();
        assertEquals(true, video.isAvailable());
    }

	@Test
	public void testCompareToTrue() throws VideoException {
		Video video1 = new Video("Moana", 2014);
		Video video2 = new Video("Moana", 2014);
		assertEquals(0, video1.compareTo(video2));
	}

	@Test
	public void testCompareToFalse() throws VideoException {
		Video video1 = new Video("Moana", 2014);
		Video video2 = new Video("Moanaa", 2014);
		assertEquals(-1, video1.compareTo(video2));
	}

}
