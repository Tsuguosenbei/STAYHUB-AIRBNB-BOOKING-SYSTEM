public class VillaProperty extends Property {
    private boolean hasPrivatePool;
    private int maxGuests;

    public VillaProperty(String propertyId, String title, String location, double pricePerNight, boolean hasPrivatePool, int maxGuests) {
        super(propertyId, title, location, pricePerNight);
        this.hasPrivatePool = hasPrivatePool;
        this.maxGuests = maxGuests;
    }

    @Override
    public void displayDetails() {
        System.out.println("[Villa] ID: " + getPropertyId() + " | Title: " + getTitle() + 
                           " | Location: " + getLocation() + " | ₱" + getPricePerNight() + "/night" +
                           " | Private Pool: " + (hasPrivatePool ? "Yes" : "No") + 
                           " | Max Guests: " + maxGuests +
                           " | Status: " + (isAvailable() ? "Available" : "Booked"));
    }
}