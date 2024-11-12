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
	
	

}
