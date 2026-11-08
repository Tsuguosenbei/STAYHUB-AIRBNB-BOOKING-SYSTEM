import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Property> properties = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static final String HOST_PIN = "1234";

    public static void main(String[] args) {
        // Seed initial sample data
        properties.add(new ApartmentProperty("P101", "Cozy Studio Apartment", "Makati City", 2500.00, 5, true));
        properties.add(new VillaProperty("P102", "Sunset Oceanfront Villa", "Batangas", 8500.00, true, 10));
        properties.add(new SharedRoomProperty("P103", "Backpacker Bed in Bunk", "Tagaytay", 750.00, 4));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("==================================================");
        System.out.println("       WELCOME TO STAYHUB BOOKING SYSTEM         ");
        System.out.println("==================================================");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Browse Available Properties");
            System.out.println("2. Book a Property (Guest)");
            System.out.println("3. Cancel Reservation (Guest)");
            System.out.println("4. Add New Listing (Host Portal)");
            System.out.println("5. View All Active Reservations");
            System.out.println("6. Exit");
            System.out.print("Select an option (1-6): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayProperties();
                    break;
                case "2":
                    bookProperty(scanner);
                    break;
                case "3":
                    cancelReservation(scanner);
                    break;
                case "4":
                    hostPortal(scanner);
                    break;
                case "5":
                    viewReservations();
                    break;
                case "6":
                    running = false;
                    System.out.println("\nThank you for using StayHub!");
                    break;
                default:
                    System.out.println("[Error] Invalid choice! Please select 1 to 6.");
            }
        }
        scanner.close();
    }

    private static void displayProperties() {
        System.out.println("\n--- LIST OF PROPERTIES ---");
        if (properties.isEmpty()) {
            System.out.println("No properties registered in the system.");
            return;
        }
        for (Property p : properties) {
            p.displayDetails();
        }
    }

    private static void bookProperty(Scanner scanner) {
        System.out.println("\n--- BOOK A PROPERTY ---");
        displayProperties();
        System.out.print("\nEnter Property ID to book: ");
        String id = scanner.nextLine();

        Property targetProperty = null;
        for (Property p : properties) {
            if (p.getPropertyId().equalsIgnoreCase(id)) {
                targetProperty = p;
                break;
            }
        }

        if (targetProperty == null) {
            System.out.println("[Error] Property ID not found.");
            return;
        }

        try {
            targetProperty.book();
            System.out.print("Enter Guest Full Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Number of Nights: ");
            int nights = Integer.parseInt(scanner.nextLine());

            if (nights <= 0) {
                targetProperty.cancelBooking(); // Revert booking state
                throw new InvalidBookingException("Duration must be at least 1 night.");
            }

            String resId = "RES-" + (reservations.size() + 1001);
            Reservation res = new Reservation(resId, targetProperty, name, nights);
            reservations.add(res);

            System.out.println("\n[Success] Booking confirmed!");
            res.printReceipt();

        } catch (PropertyUnavailableException | InvalidBookingException e) {
            System.out.println("[Booking Failed] " + e.getMessage());
        } catch (NumberFormatException e) {
            targetProperty.cancelBooking();
            System.out.println("[Error] Invalid input for number of nights.");
        }
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.println("\n--- CANCEL RESERVATION ---");
        if (reservations.isEmpty()) {
            System.out.println("No active reservations found.");
            return;
        }
        System.out.print("Enter Reservation ID to cancel: ");
        String resId = scanner.nextLine();

        Reservation foundRes = null;
        for (Reservation r : reservations) {
            if (r.getReservationId().equalsIgnoreCase(resId)) {
                foundRes = r;
                break;
            }
        }

        if (foundRes != null) {
            foundRes.getProperty().cancelBooking();
            reservations.remove(foundRes);
            System.out.println("[Success] Reservation " + resId + " has been cancelled.");
        } else {
            System.out.println("[Error] Reservation ID not found.");
        }
    }

    private static void hostPortal(Scanner scanner) {
        System.out.println("\n--- HOST PORTAL ---");
        System.out.print("Enter Host Security PIN: ");
        String pin = scanner.nextLine();

        if (!HOST_PIN.equals(pin)) {
            System.out.println("[Access Denied] Invalid Security PIN.");
            return;
        }

        System.out.println("\nSelect Property Type to Add:");
        System.out.println("1. Apartment");
        System.out.println("2. Villa");
        System.out.println("3. Shared Room");
        System.out.print("Choice: ");
        String type = scanner.nextLine();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Price per Night (₱): ");
        double price = Double.parseDouble(scanner.nextLine());
        String newId = "P" + (properties.size() + 101);

        if ("1".equals(type)) {
            System.out.print("Enter Floor Number: ");
            int floor = Integer.parseInt(scanner.nextLine());
            System.out.print("Has Elevator? (true/false): ");
            boolean elev = Boolean.parseBoolean(scanner.nextLine());
            properties.add(new ApartmentProperty(newId, title, location, price, floor, elev));
        } else if ("2".equals(type)) {
            System.out.print("Has Private Pool? (true/false): ");
            boolean pool = Boolean.parseBoolean(scanner.nextLine());
            System.out.print("Enter Max Guests: ");
            int guests = Integer.parseInt(scanner.nextLine());
            properties.add(new VillaProperty(newId, title, location, price, pool, guests));
        } else if ("3".equals(type)) {
            System.out.print("Enter Total Beds in Room: ");
            int beds = Integer.parseInt(scanner.nextLine());
            properties.add(new SharedRoomProperty(newId, title, location, price, beds));
        } else {
            System.out.println("[Error] Invalid property type selection.");
            return;
        }

        System.out.println("[Success] New property listed with ID: " + newId);
    }

    private static void viewReservations() {
        System.out.println("\n--- ACTIVE RESERVATIONS ---");
        if (reservations.isEmpty()) {
            System.out.println("No active reservations.");
            return;
        }
        for (Reservation r : reservations) {
            r.printReceipt();
            System.out.println();
        }
    }
}  
