package Listing;

import java.util.HashMap;
import java.util.Map;

public class Listing {
    private String id;
    private String description;
    private double price;
    private boolean searched;
    private int viewCount;
    private boolean available;

    private static Map<String, Listing> listingsDatabase = new HashMap<>();

    public Listing(String id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.searched = false;
        this.viewCount = 0;
        this.available = true;
    }

    public boolean isAvailable(Listing listing) {
        return listing != null && listing.available;
    }

    public String retrieveDetails(Listing listing) {
        if (listing == null) {
            return "Listing not found";
        }

        listing.searched = true;
        listing.viewCount++;

        return String.format("Listing ID: %s\nDescription: %s\nPrice: $%.2f\nAvailable: %s",
                listing.id, listing.description, listing.price, listing.available ? "Yes" : "No");
    }

    public static Listing createListing(String description, double price) {
        String newId = "LIST" + System.currentTimeMillis();
        Listing newListing = new Listing(newId, description, price);
        listingsDatabase.put(newId, newListing);
        return newListing;
    }

    public static Listing retrieveItem(String itemId) {
        return listingsDatabase.get(itemId);
    }


    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSearched() {
        return searched;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void saveViewCount() {
        System.out.println("View count saved for listing " + id + ": " + viewCount + " views");
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public static Map<String, Listing> getAllListings() {
        return listingsDatabase;
    }
}
