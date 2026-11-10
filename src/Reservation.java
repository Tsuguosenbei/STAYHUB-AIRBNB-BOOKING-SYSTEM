public class Reservation {
    private String reservationId;
    private Property property;
    private String guestName;
    private int nights;

    public Reservation(String reservationId, Property property, String guestName, int nights) {
        this.reservationId = reservationId;
        this.property = property;
        this.guestName = guestName;
        this.nights = nights;
    }

    public String getReservationId() { return reservationId; }
    public Property getProperty() { return property; }
    public String getGuestName() { return guestName; }
    public int getNights() { return nights; }

    public double calculateTotalCost() {
        return property.getPricePerNight() * nights;
    }

    public void printReceipt() {
        System.out.println("====== STAYHUB RESERVATION RECEIPT ======");
        System.out.println("Reservation ID : " + reservationId);
        System.out.println("Guest Name     : " + guestName);
        System.out.println("Property Title : " + property.getTitle());
        System.out.println("Location       : " + property.getLocation());
        System.out.println("Duration       : " + nights + " night(s)");
        System.out.println("Total Amount   : ₱" + calculateTotalCost());
        System.out.println("=========================================");
    }
}