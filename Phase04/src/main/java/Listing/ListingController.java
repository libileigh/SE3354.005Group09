package Listing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ListingController {
    private ArrayList<String> filters;

    public ListingController() {
        this.filters = new ArrayList<>();
    }

    //Retrieves available listings from the listing database
    private List<Listing> getAvailableListings() {
        Map<String, Listing> allListings = Listing.getAllListings();
        return allListings.values().stream().filter(listing -> listing.isAvailable(listing)).toList();
    }

    //Gets the number of listings that match the specified keywords and filters
    public int numListings(String keywords, ArrayList<String> filters) {
        String searchTerms = (keywords == null) ? "" : keywords.toLowerCase();

        List<Listing> availableItems = getAvailableListings();

        long matchingCount = availableItems.stream().filter(listing -> matchesSearch(listing, searchTerms)).filter(listing -> matchesFilters(listing, filters)).count();

        return (int) matchingCount;
    }

    //Validates if a listing's description contains the keywords
    private boolean matchesSearch(Listing listing, String searchTerms) {
        return listing.getDescription().toLowerCase().contains(searchTerms);
    }

    //Applies filters to a listing (incomplete)
    private boolean matchesFilters(Listing listing, ArrayList<String> filters) {
        return true; //to be completed later
    }

    //Filters and sorts listings (incomplete)
    public List<Listing> runSortFilterItems(ArrayList<String> filters, String sortOrder) {
        List<Listing> filteredListings = new ArrayList<>(getAvailableListings());

        //Applies filters
        if (filters != null && !filters.isEmpty()) {
            // to be completed later
        }

        //Applies sorting
        if (sortOrder != null) {
            switch (sortOrder.toLowerCase()) {
                case "price_asc":
                    filteredListings.sort(Comparator.comparingDouble(Listing::getPrice));
                    break;
                case "price_desc":
                    filteredListings.sort(Comparator.comparingDouble(Listing::getPrice).reversed());
                    break;
                case "views_desc":
                    filteredListings.sort(Comparator.comparingInt(Listing::getViewCount).reversed());
                    break;
                default:
                    break;
            }
        }

        return filteredListings;
    }

    //View item details
    public String getItemDetails(String itemID) {
        Listing listing = Listing.getItem(itemID);
        if (listing != null) {
            return listing.getItemDetails(listing);
        }
        return "Listing not found";
    }

    //Increments view count for a listing
    public void incrementViewCount(String itemID) {
        Listing listing = Listing.getItem(itemID);
        if (listing != null) {
            listing.saveViewCount();
        }
    }

    //Gets the seller rating (incomplete)
    public double getSellerRating(String sellerID) {
        return 0.0; //default value, actual logic will be implemented later
    }

    //Gets the filters
    public ArrayList<String> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<String> filters) {
        this.filters = filters;
    }
}