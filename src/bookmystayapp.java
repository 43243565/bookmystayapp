/**
 * Book My Stay App
 * Hotel Booking Management System
 * @author SHivam puri
 * @version 11.0
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

    synchronized boolean bookRoom(String type) {

        if (inventory.get(type) > 0) {
            inventory.put(type, inventory.get(type) - 1);
            return true;
        }

        return false;
    }
}

class BookingTask implements Runnable {

    Reservation reservation;
    RoomInventory inventory;

    BookingTask(Reservation reservation, RoomInventory inventory) {
        this.reservation = reservation;
        this.inventory = inventory;
    }

    public void run() {

        if (inventory.bookRoom(reservation.roomType)) {

            System.out.println("Booking Successful for " + reservation.guestName);
            System.out.println("Room Type: " + reservation.roomType);
            System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println();

        } else {

            System.out.println("Booking Failed for " + reservation.guestName);
            System.out.println("No rooms available (" + reservation.roomType + ")");
            System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println();
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {

        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 11.0");

        RoomInventory inventory = new RoomInventory();

        Thread t1 = new Thread(new BookingTask(new Reservation("Aman", "Single Room"), inventory));
        Thread t2 = new Thread(new BookingTask(new Reservation("Neha", "Single Room"), inventory));
        Thread t3 = new Thread(new BookingTask(new Reservation("Rahul", "Single Room"), inventory));

        t1.start();
        t2.start();
        t3.start();
    }
}