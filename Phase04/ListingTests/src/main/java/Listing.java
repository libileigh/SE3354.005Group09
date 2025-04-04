import java.util.HashMap;
import java.util.Map;

public class Listing {
    private String id;
    private String description;
    private double price;
    private boolean searched;
    private int viewCount;
    private boolean available;

    private static boolean samplesAdded = false;
    private static Map<String, Listing> listingsDatabase = new HashMap<>();

    // ✅ Static initializer block to add sample data only once
    static {
        if (!samplesAdded) {
            long idTime = System.currentTimeMillis();

            Listing test1 = new Listing("LIST" + (idTime + 1), "iPhone 15 Pro", 600.00);
            listingsDatabase.put(test1.getId(), test1);

            Listing test2 = new Listing("LIST" + (idTime + 2), "Calculus Textbook", 45.00);
            test2.setAvailable(false);
            listingsDatabase.put(test2.getId(), test2);

            Listing test3 = new Listing("LIST" + (idTime + 3), "Yamaha Electric Guitar", 280.00);
            listingsDatabase.put(test3.getId(), test3);

            Listing test4 = new Listing("LIST" + (idTime + 4), "Size L UTD Shirt", 14.00);
            listingsDatabase.put(test4.getId(), test4);

            Listing test5 = new Listing("LIST" + (idTime + 5), "Various stationary lot", 23.00);
            listingsDatabase.put(test5.getId(), test5);

            samplesAdded = true;
        }
    }

    public Listing(String id, String description, double price) {
        this.id = id;
        this.description = description != null ? description : "No description";
        this.price = price;
        this.searched = false;
        this.viewCount = 0;
        this.available = true;
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

    public static Map<String, Listing> getAllListings() {
        return listingsDatabase;
    }

    public boolean isAvailable(Listing listing) {
        return listing != null && listing.available;
    }

    public String getItemDetails(Listing listing) {
        if (listing == null) return "Listing not found";

        listing.searched = true;
        listing.viewCount++;

        return String.format("Listing ID: %s\nDescription: %s\nPrice: $%.2f\nAvailable: %s",
                listing.id, listing.description, listing.price, listing.available ? "Yes" : "No");
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
}
