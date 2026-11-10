public class SharedRoomProperty extends Property {
    private int totalBedsInRoom;

    public SharedRoomProperty(String propertyId, String title, String location, double pricePerNight, int totalBedsInRoom) {
        super(propertyId, title, location, pricePerNight);
        this.totalBedsInRoom = totalBedsInRoom;
    }

    @Override
    public void displayDetails() {
        System.out.println("[Shared Room] ID: " + getPropertyId() + " | Title: " + getTitle() + 
                           " | Location: " + getLocation() + " | ₱" + getPricePerNight() + "/night" +
                           " | Total Beds: " + totalBedsInRoom +
                           " | Status: " + (isAvailable() ? "Available" : "Booked"));
    }
}