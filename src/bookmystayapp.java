/**
 * Book My Stay App
 * Hotel Booking Management System
 * Use Case 12: Data Persistence & System Recovery
 * @author Shivam puri
 * @version 12.0
 */

import java.util.*;
import java.io.*;

class RoomInventory {
    // Changed to Map<String, Integer> for flexibility with file labels
    Map<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        // Default initial state [cite: 126, 127, 128]
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    synchronized boolean bookRoom(String type) {
        if (inventory.containsKey(type) && inventory.get(type) > 0) {
            inventory.put(type, inventory.get(type) - 1);
            return true;
        }
        return false;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }
}

/**
 * Service responsible for persisting critical system state to a plain text file. [cite: 65, 66]
 * No database or serialization framework is used. [cite: 71, 72]
 */
class FilePersistenceService {

    /**
     * Saves room inventory state to a file in format: roomType=availableCount [cite: 79, 80, 81]
     */
    public void saveInventory(RoomInventory inventory, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : inventory.getInventory().entrySet()) {
                writer.println(entry.getKey() + "=" + entry.getValue());
            }
            System.out.println("Inventory saved successfully."); [cite: 129]
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }

    /**
     * Loads room inventory state from a file. [cite: 89]
     * Handles missing files by allowing the system to start fresh.
     */
    public void loadInventory(RoomInventory inventory, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh."); [cite: 124]
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    inventory.getInventory().put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading inventory (corrupted file). Using defaults."); [cite: 43, 53]
        }
    }
}

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("System Recovery"); [cite: 123]
        System.out.println("Version 12.0");

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();
        String storageFile = "inventory.txt";

        // 1. Restore state from file during startup [cite: 27, 28, 53]
        persistenceService.loadInventory(inventory, storageFile);

        // 2. Display Current State [cite: 125]
        System.out.println("Current Inventory:");
        inventory.getInventory().forEach((type, count) ->
                System.out.println(type + ": " + count));

        // 3. Simulate a booking operation to change state
        System.out.println("\nSimulating a booking for 'Single' room...");
        if (inventory.bookRoom("Single")) {
            System.out.println("Booking Successful!");
        }

        // 4. Persist updated state before shutdown [cite: 18, 19, 53]
        persistenceService.saveInventory(inventory, storageFile);
    }
}