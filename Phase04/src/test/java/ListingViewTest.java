package Listing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListingViewEdgeCaseTest {

    Listing emptyDescriptionListing;
    Listing negativePriceListing;
    Listing specialCharsListing;
    Listing longDescriptionListing;

    @BeforeEach
    public void setUp() {
        // setting up edge case listings
        emptyDescriptionListing = Listing.createListing("", 50.00);
        negativePriceListing = Listing.createListing("Broken Laptop", -20.00);
        specialCharsListing = Listing.createListing("UTD Stuff! 🚀📚🔥", 10.00);

        // making a long description
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("VeryLongText");
        }
        longDescriptionListing = Listing.createListing(sb.toString(), 100.00);
    }

    // tc1: empty description should still show something
    @Test
    public void TC1() {
        String details = emptyDescriptionListing.getItemDetails(emptyDescriptionListing);
        assertTrue(details.contains("Description: "));
    }

    // tc2: negative price still shows up 
    @Test
    public void TC2() {
        String details = negativePriceListing.getItemDetails(negativePriceListing);
        assertTrue(details.contains("Price: $-20.00"));
    }

    // tc3: handles emojis and random symbols fine
    @Test
    public void TC3() {
        String details = specialCharsListing.getItemDetails(specialCharsListing);
        assertTrue(details.contains("UTD Stuff!"));
        assertTrue(details.contains("🚀📚🔥"));
    }

    // tc4: long description shouldn't crash anything
    @Test
    public void TC4() {
        String details = longDescriptionListing.getItemDetails(longDescriptionListing);
        assertTrue(details.contains("Description: VeryLongText"));
        assertTrue(details.length() > 500);
    }

    // tc5: calling getItemDetails(null) multiple times shouldn't break
    @Test
    public void TC5() {
        String result1 = emptyDescriptionListing.getItemDetails(null);
        String result2 = negativePriceListing.getItemDetails(null);
        assertEquals("Listing not found", result1);
        assertEquals("Listing not found", result2);
    }

    // tc6: make sure view counts don't mix between listings
    @Test
    public void TC6() {
        int viewsA = emptyDescriptionListing.getViewCount();
        int viewsB = specialCharsListing.getViewCount();

        emptyDescriptionListing.getItemDetails(emptyDescriptionListing);
        specialCharsListing.getItemDetails(specialCharsListing);
        specialCharsListing.getItemDetails(specialCharsListing);

        assertEquals(viewsA + 1, emptyDescriptionListing.getViewCount());
        assertEquals(viewsB + 2, specialCharsListing.getViewCount());
    }
}
