import java.util.*;

class Room {
    int roomNumber;
    String type;
    boolean isBooked;
    double price;

    Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }
}

class Booking {
    String customerName;
    Room room;
    int nights;
    double totalAmount;

    Booking(String customerName, Room room, int nights) {
        this.customerName = customerName;
        this.room = room;
        this.nights = nights;
        this.totalAmount = room.price * nights;
    }
}

public class HotelReservationSystem {
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            switch (option) {
                case 1 -> viewAvailableRooms();
                case 2 -> makeReservation();
                case 3 -> viewBookings();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void initializeRooms() {
        rooms.add(new Room(101, "Single", 100));
        rooms.add(new Room(102, "Single", 100));
        rooms.add(new Room(201, "Double", 150));
        rooms.add(new Room(202, "Double", 150));
        rooms.add(new Room(301, "Suite", 250));
    }

    static void viewAvailableRooms() {
        System.out.print("Enter room type (Single/Double/Suite): ");
        String type = scanner.next().trim();

        System.out.println("\nAvailable " + type + " Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked && room.type.equalsIgnoreCase(type)) {
                System.out.println("Room " + room.roomNumber + " - $" + room.price + " per night");
            }
        }
    }

    static void makeReservation() {
        System.out.print("Enter your name: ");
        scanner.nextLine(); // clear buffer
        String name = scanner.nextLine();

        System.out.print("Enter room type (Single/Double/Suite): ");
        String type = scanner.nextLine();

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (!room.isBooked && room.type.equalsIgnoreCase(type)) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("No available rooms of this type.");
            return;
        }

        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();

        double total = selectedRoom.price * nights;
        System.out.println("Total cost: $" + total);
        System.out.print("Proceed with payment? (yes/no): ");
        String pay = scanner.next();

        if (pay.equalsIgnoreCase("yes")) {
            selectedRoom.isBooked = true;
            Booking booking = new Booking(name, selectedRoom, nights);
            bookings.add(booking);
            System.out.println("Booking confirmed for " + name + " in Room " + selectedRoom.roomNumber);
        } else {
            System.out.println("Payment cancelled. Booking not completed.");
        }
    }

    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings made yet.");
            return;
        }

        System.out.println("\n--- Booking Details ---");
        for (Booking b : bookings) {
            System.out.println("Customer: " + b.customerName);
            System.out.println("Room: " + b.room.roomNumber + " (" + b.room.type + ")");
            System.out.println("Nights: " + b.nights);
            System.out.println("Total Paid: $" + b.totalAmount);
            System.out.println("-----------------------------");
        }
    }
}
1
