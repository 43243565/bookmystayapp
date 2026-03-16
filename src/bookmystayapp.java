/**
 * Book My Stay App
 * Hotel Booking Management System
 * @author SHIVANSH DHINGRA
 * @version 9.0
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

class Validator {

    boolean validateReservation(Reservation r) {

        if (r.guestName == null || r.guestName.trim().isEmpty()) {
            System.out.println("Error: Guest name cannot be empty.");
            System.out.println();
            return false;
        }

        if (r.roomType == null || r.roomType.trim().isEmpty()) {
            System.out.println("Error: Room type must be selected.");
            System.out.println();
            return false;
        }

        return true;
    }
}

class BookingService {

    Set<String> validRoomTypes;

    BookingService() {
        validRoomTypes = new HashSet<>();
        validRoomTypes.add("Single Room");
        validRoomTypes.add("Double Room");
        validRoomTypes.add("Suite Room");
    }

    void processBooking(Reservation r) {

        try {

            if (!validRoomTypes.contains(r.roomType)) {
                throw new IllegalArgumentException("Invalid room type selected.");
            }

            System.out.println("Reservation Successful");
            System.out.println("Guest Name: " + r.guestName);
            System.out.println("Room Type: " + r.roomType);
            System.out.println();

        } catch (IllegalArgumentException e) {

            System.out.println("Booking Failed: " + e.getMessage());
            System.out.println();
        }
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {

        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 9.0");

        Validator validator = new Validator();
        BookingService bookingService = new BookingService();

        Reservation r1 = new Reservation("Aman", "Single Room");
        Reservation r2 = new Reservation("", "Double Room");
        Reservation r3 = new Reservation("Rahul", "Luxury Room");

        if (validator.validateReservation(r1)) {
            bookingService.processBooking(r1);
        }

        if (validator.validateReservation(r2)) {
            bookingService.processBooking(r2);
        }

        if (validator.validateReservation(r3)) {
            bookingService.processBooking(r3);
        }
    }
}