package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Medication} class represents a medication item in the inventory system.
 * It includes details such as the medicine's ID, name, quantity, low stock threshold,
 * and restock request status.
 */
public class Medication {

    private int medicineID;
    private String medicineName;
    private int quantity;
    private boolean lowStockAlert;
    private int lowQuantity;
    private String requestRestock;

    /**
     * Constructs a new {@code Medication} object with the specified details.
     *
     * @param medicineID     The unique ID of the medication.
     * @param medicineName   The name of the medication.
     * @param quantity       The current stock quantity of the medication.
     * @param lowQuantity    The threshold below which the stock is considered low.
     * @param lowStockAlert  Whether a low stock alert is triggered.
     * @param requestRestock The restock request status for the medication.
     */
    public Medication(int medicineID, String medicineName, int quantity, int lowQuantity, boolean lowStockAlert,
                      String requestRestock) {
        this.medicineID = medicineID;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.lowQuantity = lowQuantity;
        this.lowStockAlert = lowStockAlert;
        this.requestRestock = requestRestock;
    }

    /**
     * Gets the restock request status.
     *
     * @return The restock request status as a {@code String}.
     */
    public String getRequestRestock() {
        return requestRestock;
    }

    /**
     * Sets the restock request status.
     *
     * @param requestRestock The new restock request status.
     */
    public void setRequestRestock(String requestRestock) {
        this.requestRestock = requestRestock;
    }

    /**
     * Gets the unique ID of the medication.
     *
     * @return The medication ID as an integer.
     */
    public int getMedicineID() {
        return medicineID;
    }

    /**
     * Gets the name of the medication.
     *
     * @return The medication name as a {@code String}.
     */
    public String getMedicineName() {
        return medicineName;
    }

    /**
     * Gets the current quantity of the medication in stock.
     *
     * @return The stock quantity as an integer.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the low stock quantity threshold.
     *
     * @return The low stock threshold as an integer.
     */
    public int getLowQuantity() {
        return lowQuantity;
    }

    /**
     * Checks whether the low stock alert is triggered.
     *
     * @return {@code true} if the stock is below or equal to the threshold, {@code false} otherwise.
     */
    public boolean getLowStockAlert() {
        return lowStockAlert;
    }

    /**
     * Sets the unique ID of the medication.
     *
     * @param medicineID The new medication ID.
     */
    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }

    /**
     * Sets the name of the medication.
     *
     * @param medicineName The new medication name.
     */
    public void quantity(String medicineName) {
        this.medicineName = medicineName;
    }

    /**
     * Sets the stock quantity of the medication.
     *
     * @param quantity The new stock quantity.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the low stock quantity threshold.
     *
     * @param lowQuantity The new low stock threshold.
     */
    public void setLowQuantity(int lowQuantity) {
        this.lowQuantity = lowQuantity;
    }

    /**
     * Sets the low stock alert status.
     *
     * @param lowStockAlert The new low stock alert status.
     */
    public void setlowstockAlert(boolean lowStockAlert) {
        this.lowStockAlert = lowStockAlert;
    }

    /**
     * Reduces the stock quantity by the specified amount if sufficient stock is available.
     *
     * @param amount The amount to reduce.
     */
    public void reduceQuantity(int amount) {
        if (this.quantity >= amount) {
            this.quantity -= amount;
        } else {
            System.out.println("Insufficient stock available.");
        }
    }

    /**
     * Prints the details of the medication, including name, quantity, low stock threshold,
     * and restock request status.
     */
    public void printDetails() {
        System.out.println("Medication Details:");
        System.out.println("Medicine Name: " + medicineName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Low Quantity Threshold: " + lowQuantity);
        System.out.println("Low Stock Alert: " + lowStockAlert);
        System.out.println("Restock Request: " + requestRestock);
    }

    /**
     * Displays the inventory of medications in a tabular format.
     *
     * @param medications The list of medications to display.
     */
    public static void displayInventory(List<Medication> medications) {
        if (medications.isEmpty()) {
            System.out.println("The inventory is empty.");
        } else {
            System.out.println("Inventory List:");
            System.out.println("---------------------------------------------");
            System.out.printf("%-10s %-20s %-10s %-15s%n", "ID", "Name", "Quantity", "Low Stock Alert");
            System.out.println("---------------------------------------------");
            for (Medication med : medications) {
                System.out.printf("%-10d %-20s %-10d %-15s%n", med.getMedicineID(), med.getMedicineName(),
                        med.getQuantity(), med.getLowStockAlert() ? "Yes" : "No");
            }
            System.out.println("---------------------------------------------");
        }
    }

    /**
     * Updates the low stock alert status of a medication based on its current quantity
     * and low stock threshold.
     *
     * @param med The {@code Medication} object to update.
     */
    public void updateLowStockAlert(Medication med) {
        if (med.getLowQuantity() >= med.getQuantity())
            med.setlowstockAlert(true);
        else
            med.setlowstockAlert(false);
    }
}