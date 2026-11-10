public abstract class Property implements Bookable {
    private String propertyId;
    private String title;
    private String location;
    private double pricePerNight;
    private boolean isAvailable;

    public Property(String propertyId, String title, String location, double pricePerNight) {
        this.propertyId = propertyId;
        this.title = title;
        this.location = location;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    public String getPropertyId() { return propertyId; }
    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) { this.isAvailable = available; }

    @Override
    public void book() throws PropertyUnavailableException {
        if (!isAvailable) {
            throw new PropertyUnavailableException("Property " + title + " is currently unavailable!");
        }
        this.isAvailable = false;
    }

    @Override
    public void cancelBooking() {
        this.isAvailable = true;
    }

    public abstract void displayDetails();
}