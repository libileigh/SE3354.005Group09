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
    private static boolean samples = false;
    private static Map<String, Listing> listingsDatabase = new HashMap<>();

    public Listing(String id, String description, double price) {
        this.id = id;
        this.description = description != null ? description : "No description";
        this.price = price;
        this.searched = false;
        this.viewCount = 0;
        this.available = true;

        if (!samples) {
            long idTime = System.currentTimeMillis();
            String id1 = "LIST" + (idTime + 1);
            Listing test1 = new Listing(id1, "iPhone 15 Pro", 600.00);
            listingsDatabase.put(id1, test1);

            String id2 = "LIST" + (idTime + 2);
            Listing test2 = new Listing(id2, "Calculus Textbook", 45.00);
            test2.setAvailable(false);
            listingsDatabase.put(id2, test2);

            String id3 = "LIST" + (idTime + 3);
            Listing test3 = new Listing(id3, "Yamaha Electric Guitar", 280.00);
            listingsDatabase.put(id3, test3);

            String id4 = "LIST" + (idTime + 4);
            Listing test4 = new Listing(id4, "Size L UTD Shirt", 14.00);
            listingsDatabase.put(id4, test4);

            String id5 = "LIST" + (idTime + 5);
            Listing test5 = new Listing(id5, "Various stationary lot", 23.00);
            listingsDatabase.put(id5, test5);

            samples = true;
        }
    }

    public boolean isAvailable(Listing listing) {
        return listing != null && listing.available;
    }

    public String getItemDetails(Listing listing) {
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

    public static Listing getItem(String itemId) {
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
