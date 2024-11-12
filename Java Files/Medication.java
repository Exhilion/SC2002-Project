package HospitalProject;

public class Medication {
	private int medicineID;
	private String medicineName;
	private int quantity;
	private int lowQuantity;
	private boolean lowStockAlert;

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

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setLowQuantity(int lowQuantity) {
		this.lowQuantity = lowQuantity;
	}

	public void setLowStockAlert(boolean lowStockAlert) {
		this.lowStockAlert = lowStockAlert;
	}

	public void updateLowStockAlert()
	{
		if(this.getLowQuantity() >= this.getQuantity())	
		this.setLowStockAlert(true);
		else 
		this.setLowStockAlert(false);
	}
}