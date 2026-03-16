/**
 * Book My Stay App
 * Hotel Booking Management System
 * @author SHIVANSH DHINGRAgit add .
 * @version 8.0
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

class BookingHistory {

    List<Reservation> history;

    BookingHistory() {
        history = new ArrayList<>();
    }

    void addBooking(Reservation r) {
        history.add(r);
    }

    void showAllBookings() {

        System.out.println("Booking History Report");
        System.out.println("----------------------");

        for (Reservation r : history) {
            System.out.println("Guest Name: " + r.guestName);
            System.out.println("Room Type: " + r.roomType);
            System.out.println();
        }
    }
}

class ReportService {

    void generateSummary(List<Reservation> history) {

        HashMap<String, Integer> roomCount = new HashMap<>();

        for (Reservation r : history) {
            roomCount.put(r.roomType, roomCount.getOrDefault(r.roomType, 0) + 1);
        }

        System.out.println("Booking Summary Report");
        System.out.println("----------------------");

        for (String room : roomCount.keySet()) {
            System.out.println(room + " Bookings: " + roomCount.get(room));
        }

        System.out.println();
    }
}

public class UseCase8BookingHistoryReporting {
    public static void main(String[] args) {

        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 8.0");

        BookingHistory bookingHistory = new BookingHistory();
        ReportService reportService = new ReportService();

        bookingHistory.addBooking(new Reservation("Aman", "Single Room"));
        bookingHistory.addBooking(new Reservation("Neha", "Double Room"));
        bookingHistory.addBooking(new Reservation("Rahul", "Suite Room"));
        bookingHistory.addBooking(new Reservation("Priya", "Single Room"));

        bookingHistory.showAllBookings();

        reportService.generateSummary(bookingHistory.history);
    }
}