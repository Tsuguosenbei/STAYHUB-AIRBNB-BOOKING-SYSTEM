public class SharedRoomProperty extends Property {
    private int totalBeds;

    public SharedRoomProperty(String propertyId, String title, String location, double pricePerNight, int totalBeds) {
        super(propertyId, title, location, pricePerNight);
        this.totalBeds = totalBeds;
    }

    public int getTotalBeds() { return totalBeds; }

    @Override
    public void displayDetails() {
        System.out.println("[Shared Room] ID: " + getPropertyId() + " | Title: " + getTitle() + 
                           " | Location: " + getLocation() + " | ₱" + getPricePerNight() + "/night" +
                           " | Total Beds: " + totalBeds +
                           " | Status: " + (isAvailable() ? "Available" : "Booked"));
    }
}