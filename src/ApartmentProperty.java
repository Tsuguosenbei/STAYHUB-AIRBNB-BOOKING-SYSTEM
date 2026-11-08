public class ApartmentProperty extends Property {
    private int floorNumber;
    private boolean hasElevator;

    public ApartmentProperty(String propertyId, String title, String location, double pricePerNight, int floorNumber, boolean hasElevator) {
        super(propertyId, title, location, pricePerNight);
        this.floorNumber = floorNumber;
        this.hasElevator = hasElevator;
    }

    @Override
    public void displayDetails() {
        System.out.println("[Apartment] ID: " + getPropertyId() + " | Title: " + getTitle() + 
                           " | Location: " + getLocation() + " | ₱" + getPricePerNight() + "/night" +
                           " | Floor: " + floorNumber + " | Elevator: " + (hasElevator ? "Yes" : "No") +
                           " | Status: " + (isAvailable() ? "Available" : "Booked"));
    }
}