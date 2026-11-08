public interface Bookable {
    void book() throws PropertyUnavailableException;
    void cancelBooking();
}