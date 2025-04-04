import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListingTest {

    @Test
    void testCreateListingWithValidInput() {
        Listing listing = Listing.createListing("Bike", 150.0);

        assertNotNull(listing);
        assertEquals("Bike", listing.getDescription());
        assertEquals(150.0, listing.getPrice());
        assertTrue(Listing.getAllListings().containsKey(listing.getId()));
    }

    @Test
    void testCreateListingWithNullDescription() {
        Listing listing = Listing.createListing(null, 50.0);

        assertNotNull(listing);
        assertEquals("No description", listing.getDescription());
    }

    @Test
    void testCreateListingWithNegativePrice() {
        Listing listing = Listing.createListing("Lamp", -10.0);

        assertNotNull(listing);
        assertEquals("Lamp", listing.getDescription());
        assertEquals(-10.0, listing.getPrice());
    }

    @Test
    void testCreateListingWithEmptyDescription() {
        Listing listing = Listing.createListing("", 25.0);

        assertNotNull(listing);
        assertEquals("", listing.getDescription());
    }
    @Test
    void testCreateListingWithZeroPrice() {
        Listing listing = Listing.createListing("Free stuff", 0.00);
        assertNotNull(listing);
        assertEquals("Free stuff", listing.getDescription());
        assertEquals(0.00, listing.getPrice());
    }

    @Test
    void testCreateListingWithEmptyStringDescription() {
        Listing listing = Listing.createListing("", 10.00);
        assertNotNull(listing);
        assertEquals("", listing.getDescription());
        assertEquals(10.00, listing.getPrice());
    }
    @Test
    void testGetItemDetailsWithValidListing() {
        Listing listing = Listing.createListing("MacBook Pro", 999.99);
        String details = listing.getItemDetails(listing);

        assertTrue(details.contains("MacBook Pro"));
        assertTrue(details.contains("999.99"));
        assertTrue(details.contains("Available: Yes"));
        assertTrue(listing.isSearched());
        assertEquals(1, listing.getViewCount());
    }

    @Test
    void testGetItemDetailsWithNullListing() {
        Listing listing = null;
        String details = new Listing("LIST999", "Dummy", 0.0).getItemDetails(listing);

        assertEquals("Listing not found", details);
    }

}
