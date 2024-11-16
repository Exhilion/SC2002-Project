package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Medication class represents a medication in the inventory.
 * It includes details like the medicine ID, name, quantity, and whether a low stock alert is triggered.
 */
public class Medication {
	private int medicineID;
	private String medicineName;
	private int quantity;
	private boolean lowStockAlert;
	private int lowQuantity;

	/**
     * Constructs a new Medication object with the specified details.
     *
     * @param medicineID The unique ID of the medication.
     * @param medicineName The name of the medication.
     * @param quantity The current stock quantity of the medication.
     * @param lowQuantity The threshold quantity for low stock alert.
     * @param lowStockAlert The status indicating if the medication has triggered a low stock alert.
     */
	public Medication(int medicineID, String medicineName, int quantity, int lowQuantity, boolean lowStockAlert) {
		this.medicineID = medicineID;
		this.medicineName = medicineName;
		this.quantity = quantity;
		this.lowQuantity = lowQuantity; 
		this.lowStockAlert = lowStockAlert;

	}

	/**
     * Returns the unique ID of the medication.
     *
     * @return The medicine ID.
     */
	public int getMedicineID() {
		return medicineID;
	}

	/**
     * Returns the name of the medication.
     *
     * @return The medicine name.
     */
	public String getMedicineName() {
		return medicineName;
	}

	/**
     * Returns the current stock quantity of the medication.
     *
     * @return The medication quantity.
     */
	public int getQuantity() {
		return quantity;
	}
	
	/**
     * Returns the threshold for low stock alert.
     *
     * @return The low quantity threshold.
     */
	public int getLowQuantity() {
		return lowQuantity;
	}

	// true = high stock, false = low stock
	// admin can have a function to check for stock, if returns false then replenish
	/**
     * Returns whether the medication has triggered a low stock alert.
     *
     * @return true if the stock is low, false otherwise.
     */
	public boolean getLowStockAlert() {
		return lowStockAlert;
	}

	/**
     * Sets the unique ID of the medication.
     *
     * @param medicineID The new medicine ID.
     */
	public void setMedicineID(int medicineID) {
		this.medicineID = medicineID;
	}

	/**
     * Sets the name of the medication.
     *
     * @param medicineName The new medicine name.
     */
	public void quantity(String medicineName) {
		this.medicineName = medicineName;
	}

	/**
     * Sets the current stock quantity of the medication.
     *
     * @param quantity The new stock quantity.
     */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
     * Sets the threshold for low stock alert.
     *
     * @param lowQuantity The new low quantity threshold.
     */
	public void setLowQuantity(int lowQuantity) {
		this.lowQuantity = lowQuantity; 
	}

	/**
     * Sets the low stock alert status.
     *
     * @param lowStockAlert true if the stock is low, false otherwise.
     */
	public void setlowstockAlert(boolean lowStockAlert) {
		this.lowStockAlert = lowStockAlert;
	}
	
	/**
     * Reduces the stock quantity by the specified amount.
     *
     * @param amount The amount to reduce the stock by.
     */
	public void reduceQuantity(int amount) {
	    if (this.quantity >= amount) {
	        this.quantity -= amount;
	    } else {
	        System.out.println("Insufficient stock available.");
	    }
	}
	
	/**
     * Prints the details of the medication.
     */
    public void printDetails() {
        System.out.println("Medication Details:");
        System.out.println("Medicine Name: " + medicineName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Low Quantity Threshold: " + lowQuantity);
        System.out.println("Low Stock Alert: " + lowStockAlert);
    }
    
    /**
     * Displays the list of medications in the inventory.
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
                System.out.printf("%-10d %-20s %-10d %-15s%n", 
                                  med.getMedicineID(), 
                                  med.getMedicineName(), 
                                  med.getQuantity(), 
                                  med.getLowStockAlert() ? "Yes" : "No");
            }
            System.out.println("---------------------------------------------");
        }
    }
    
    /**
     * Updates the low stock alert status based on the current stock quantity and low quantity threshold.
     *
     * @param med The medication object to update the low stock alert status for.
     */
	public void updateLowStockAlert(Medication med)
	{
		if(med.getLowQuantity() >= med.getQuantity())	
		med.setlowstockAlert(true);
		else 
		med.setlowstockAlert(false);
	}
	
	

}
