import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Property> properties = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static final String HOST_PIN = "1234";
    private static int nextReservationId = 1001;
    private static int nextPropertyId = 104;

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
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                targetProperty.cancelBooking();
                throw new InvalidBookingException(
                    "Guest name cannot be empty."
                );
            }

            System.out.print("Enter Number of Nights: ");
            int nights = Integer.parseInt(scanner.nextLine());

            if (nights <= 0) {
                targetProperty.cancelBooking(); // Revert booking state
                throw new InvalidBookingException("Duration must be at least 1 night.");
            }

            String resId = "RES-" + nextReservationId;
            Reservation res = new Reservation(resId, targetProperty, name, nights);
            reservations.add(res);

            nextReservationId++;

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
        String type = scanner.nextLine().trim();

        if (!type.equals("1") &&
            !type.equals("2") &&
            !type.equals("3")) {

            System.out.println("[Error] Invalid property type! Please select 1, 2, or 3.");
            return;
        }

        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("[Error] Property title cannot be empty.");
            return;
        }
        System.out.print("Enter Location: ");
        String location = scanner.nextLine().trim();

        if (location.isEmpty()) {
            System.out.println("[Error] Property location cannot be empty.");
            return;
        }
        double price;

        try {
            System.out.print("Enter Price per Night (₱): ");
            price = Double.parseDouble(scanner.nextLine());

            if (!Double.isFinite(price) || price <= 0) {
                System.out.println("[Error] Price must be greater than zero.");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("[Error] Invalid price! Please enter a valid number.");
            return;
        }

        String newId = "P" + nextPropertyId;

        if ("1".equals(type)) {
        	int floor;

        	try {
        	    System.out.print("Enter Floor Number: ");
        	    floor = Integer.parseInt(scanner.nextLine());

        	    if (floor < 0) {
        	        System.out.println("[Error] Floor number cannot be negative.");
        	        return;
        	    }

        	} catch (NumberFormatException e) {
        	    System.out.println("[Error] Invalid floor number! Enter a whole number.");
        	    return;
        	}
        	Boolean elev = readBoolean(scanner, "Has Elevator? (true/false): ");

        	if (elev == null) {
        	    return;
        	}
            properties.add(new ApartmentProperty(newId, title, location, price, floor, elev));
        } else if ("2".equals(type)) {
        	Boolean pool = readBoolean(scanner, "Has Private Pool? (true/false): ");

        	if (pool == null) {
        	    return;
        	}
            int guests;

            try {
                System.out.print("Enter Max Guests: ");
                guests = Integer.parseInt(scanner.nextLine());

                if (guests <= 0) {
                    System.out.println("[Error] Maximum guests must be at least 1.");
                    return;
                }

            } catch (NumberFormatException e) {
                System.out.println("[Error] Invalid guest count! Enter a whole number.");
                return;
            }
            properties.add(new VillaProperty(newId, title, location, price, pool, guests));
        } else if ("3".equals(type)) {
        	int beds;

        	try {
        	    System.out.print("Enter Total Beds in Room: ");
        	    beds = Integer.parseInt(scanner.nextLine());

        	    if (beds <= 0) {
        	        System.out.println("[Error] Number of beds must be at least 1.");
        	        return;
        	    }

        	} catch (NumberFormatException e) {
        	    System.out.println("[Error] Invalid bed count! Enter a whole number.");
        	    return;
        	}
            properties.add(new SharedRoomProperty(newId, title, location, price, beds));
        } else {
            System.out.println("[Error] Invalid property type selection.");
            return;
        }
        nextPropertyId++;
        System.out.println("[Success] New property listed with ID: " + newId);
    }
    private static Boolean readBoolean(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("true")) {
            return true;
        }

        if (input.equalsIgnoreCase("false")) {
            return false;
        }

        System.out.println("[Error] Invalid input! Please enter true or false.");
        return null;
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
