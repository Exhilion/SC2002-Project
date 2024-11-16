package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Medication {
	private int medicineID;
	private String medicineName;
	private int quantity;
	private boolean lowStockAlert;
	private int lowQuantity;

	public Medication(int medicineID, String medicineName, int quantity, int lowQuantity, boolean lowStockAlert) {
		this.medicineID = medicineID;
		this.medicineName = medicineName;
		this.quantity = quantity;
		this.lowQuantity = lowQuantity; 
		this.lowStockAlert = lowStockAlert;

	}

	public int getMedicineID() {
		return medicineID;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public int getQuantity() {
		return quantity;
	}
	public int getLowQuantity() {
		return lowQuantity;
	}

	// true = high stock, false = low stock
	// admin can have a function to check for stock, if returns false then replenish
	public boolean getLowStockAlert() {
		return lowStockAlert;
	}

	public void setMedicineID(int medicineID) {
		this.medicineID = medicineID;
	}

	public void quantity(String medicineName) {
		this.medicineName = medicineName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setLowQuantity(int lowQuantity) {
		this.lowQuantity = lowQuantity; 
	}

	public void setlowstockAlert(boolean lowStockAlert) {
		this.lowStockAlert = lowStockAlert;
	}
	
	public void reduceQuantity(int amount) {
	    if (this.quantity >= amount) {
	        this.quantity -= amount;
	    } else {
	        System.out.println("Insufficient stock available.");
	    }
	}
	
    public void printDetails() {
        System.out.println("Medication Details:");
        System.out.println("Medicine Name: " + medicineName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Low Quantity Threshold: " + lowQuantity);
        System.out.println("Low Stock Alert: " + lowStockAlert);
    }
    
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
    
	public void updateLowStockAlert(Medication med)
	{
		if(med.getLowQuantity() >= med.getQuantity())	
		med.setlowstockAlert(true);
		else 
		med.setlowstockAlert(false);
	}
	
	

}
