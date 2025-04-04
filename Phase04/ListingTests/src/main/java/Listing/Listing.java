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
        this.id = id;	//Listing ID
        this.description = description != null ? description : "No description";	//Description of the listing
        this.price = price;	//Price of the item
        this.searched = false;	//If the listing was searched
        this.viewCount = 0;	//View count of the listing
        this.available = true;	//Availability of the listing

        //sample listings
//        if (!samples) {
//            Listing test1 = new Listing("LIST1", "iPhone 15 Pro", 600.00);
//            listingsDatabase.put("LIST1", test1);
//
//            //listing that was set to unavailable
//
//            Listing test2 = new Listing("LIST2", "Calculus Textbook", 45.00);
//            test2.setAvailable(false);
//            listingsDatabase.put("LIST2", test2);
//
//            Listing test3 = new Listing("LIST3", "Yamaha Electric Guitar", 280.00);
//            listingsDatabase.put("LIST3", test3);
//
//            Listing test4 = new Listing("LIST4", "Size L UTD Shirt", 14.00);
//            listingsDatabase.put("LIST4", test4);
//
//            Listing test5 = new Listing("LIST5", "Various stationary lot", 23.00);
//            listingsDatabase.put("LIST5", test5);
//
//            samples = true; //Check to make sure no extra sample cases are made
//        }
    }

    //Validates if a listing is available
    public boolean isAvailable(Listing listing) {
        return listing != null && listing.available;
    }

    //Gets details about the item when searched for and increments the view count for each call
    public String getItemDetails(Listing listing) {
        if (listing == null) {
            return "Listing not found";
        }

        listing.searched = true;
        listing.viewCount++;

        return String.format("Listing ID: %s\nDescription: %s\nPrice: $%.2f\nAvailable: %s",
                listing.id, listing.description, listing.price, listing.available ? "Yes" : "No");
    }

    //Creates a new listing with a unique itemID, description, and price, and puts this information into the listing database
    public static Listing createListing(String description, double price) {
        String newId = "LIST" + System.currentTimeMillis();
        Listing newListing = new Listing(newId, description, price);
        listingsDatabase.put(newId, newListing);
        return newListing;
    }

    //Gets a specified item from the listing database using the itemID
    public static Listing getItem(String itemId) {
        return listingsDatabase.get(itemId);
    }

    //Gets the itemID of a listing
    public String getId() {
        return id;
    }

    //Gets the description of a listing
    public String getDescription() {
        return description;
    }

    //Updates the description of a listing
    public void setDescription(String description) {
        this.description = description;
    }

    //Gets the price of a listing
    public double getPrice() {
        return price;
    }

    //Updates the price of a listing
    public void setPrice(double price) {
        this.price = price;
    }

    //Checks if a listing was searched
    public boolean isSearched() {
        return searched;
    }

    //Gets the view count of a listing
    public int getViewCount() {
        return viewCount;
    }

    //Saves the view count of a listing
    public void saveViewCount() {
        System.out.println("View count saved for listing " + id + ": " + viewCount + " views");
    }

    //Set the availability of a listing
    public void setAvailable(boolean available) {
        this.available = available;
    }

    //Returns the entire listing database and all of the listings stored in it
    public static Map<String, Listing> getAllListings() {
        return listingsDatabase;
    }
}
