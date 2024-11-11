package secondpart;

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

	public Medication(int medicineID, String medicineName, int quantity, boolean lowStockAlert) {
		this.medicineID = medicineID;
		this.medicineName = medicineName;
		this.quantity = quantity;
		this.lowStockAlert = lowStockAlert;

	}

	public int getmedicineID() {
		return medicineID;
	}

	public String getmedicineName() {
		return medicineName;
	}

	public int getquantity() {
		return quantity;
	}

	// true = high stock, false = low stock
	// admin can have a function to check for stock, if returns false then replenish
	public boolean getlowStockAlert() {
		return lowStockAlert;
	}

	public void setmedicineID(int medicineID) {
		this.medicineID = medicineID;
	}

	public void quantity(String medicineName) {
		this.medicineName = medicineName;
	}

	public void setquantity(int quantity) {
		this.quantity = quantity;
	}

	public void setlowstockAlert(boolean lowStockAlert) {
		this.lowStockAlert = lowStockAlert;
	}
	

}
