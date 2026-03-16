/**
 * Book My Stay App
 * Hotel Booking Management System
 * @author SHIVANSH DHINGRA
 * @version 10.0
 */

import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {

    HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String type) {
        return inventory.get(type);
    }

    void decreaseAvailability(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }

    void increaseAvailability(String type) {
        inventory.put(type, inventory.get(type) + 1);
    }
}

class BookingService {

    HashMap<String, Reservation> activeBookings;

    BookingService() {
        activeBookings = new HashMap<>();
    }

    void confirmBooking(Reservation r, RoomInventory inventory) {

        if (inventory.getAvailability(r.roomType) > 0) {

            activeBookings.put(r.guestName, r);
            inventory.decreaseAvailability(r.roomType);

            System.out.println("Booking Confirmed");
            System.out.println("Guest: " + r.guestName);
            System.out.println("Room Type: " + r.roomType);
            System.out.println();

        } else {
            System.out.println("No rooms available for " + r.guestName);
            System.out.println();
        }
    }

    void cancelBooking(String guestName, RoomInventory inventory) {

        Reservation r = activeBookings.get(guestName);

        if (r != null) {

            inventory.increaseAvailability(r.roomType);
            activeBookings.remove(guestName);

            System.out.println("Booking Cancelled for " + guestName);
            System.out.println("Room Type Released: " + r.roomType);
            System.out.println();

        } else {
            System.out.println("No booking found for " + guestName);
            System.out.println();
        }
    }
}

public class UseCase10BookingCancellationRollback {
    public static void main(String[] args) {

        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 10.0");

        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService();

        Reservation r1 = new Reservation("Aman", "Single Room");
        Reservation r2 = new Reservation("Neha", "Double Room");

        bookingService.confirmBooking(r1, inventory);
        bookingService.confirmBooking(r2, inventory);

        bookingService.cancelBooking("Aman", inventory);
    }
}