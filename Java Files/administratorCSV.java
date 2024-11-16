package OOPProject;

/**
 * This class provides the Administrator user the methods to manage the inventory of medications.
 */
public class administratorCSV {
	/**
	 * Updates the stock of a medication in the inventory with a new stock quantity. If the medicine is not already present, it adds the medication to the inventory.
	 * 
	 * @param inv The inventory to be updated
	 * @param med The medicine to be updated
	 * @param newStock The new stock quantity for the medication
	 * @param medicationCSV The helper class to update the low-stock status
	 */
	public void updateInventory(Inventory inv, Medication med, int newStock, MedicationCSV medicationCSV) {
        int temp = -1;
        for (Medication m : inv.getMedications()) {
            if (m.getMedicineID() == med.getMedicineID()) {
                temp = m.getQuantity();
                m.setQuantity(newStock);
                System.out.println("Updated the quantity of " + m.getMedicineName() + " from " + temp + " to " + m.getQuantity() + ".");
                //m.updateLowStockAlert();
                medicationCSV.updateLowStockAlert(m); 
                return;
            }
        }
        //med.updateLowStockAlert();
        medicationCSV.updateLowStockAlert(med);
        inv.addMedication(med);
        System.out.println("Added new medication: " + med.getMedicineName() + " with quantity: " + med.getQuantity() + ".");
    }
    
	/**
	 * It retrieves and returns the current stock of a specified medication in the inventory.
	 * 
	 * @param inv The inventory to check
	 * @param med The medicine to look up
	 * @return The current quantity of the medication, or 0 if not found
	 */
    public int getStock(Inventory inv, Medication med) //returns quantity of 'med'. returns 0 otherwise
    {
        for(Medication m: inv.getMedications()){
            if(m.getMedicineID() == med.getMedicineID()){
                return m.getQuantity();
            }
        }
        return 0;
    }
    /**
     * Approves a replenishment request for a specified medication with the stated quantity.
     * If the medication does not exist in the inventory, an error message is printed.
     * 
     * @param inv The inventory to be updated
     * @param med The medication to be replenished
     * @param amount The amount of medication to be added to the current stock
     */
    public void approveReplenishmentRequest(Inventory inv, Medication med, int amount){
        int temp = -1;
        for(Medication m: inv.getMedications()){
            if(m.getMedicineID() == med.getMedicineID()){
                temp = med.getQuantity();
                m.setQuantity(med.getQuantity() + amount);
                System.out.println("Successfully replenished " + med.getMedicineName() + ": " + temp + " --> " + med.getQuantity());
                return;
            }
        }
        System.out.println("Medication " + "'" + med.getMedicineName() + "' is not identified.");  
    }
    /**
     * Approves a replenishment request for a specified medication with a default quantity of 100.
     * If the medication does not exist in the inventory, an error message is printed.
     * 
     * @param inv The inventory to be updated
     * @param med The medication to be replenished
     */
    public void approveReplenishmentRequest(Inventory inv, Medication med){
        int temp = -1;
        for(Medication m: inv.getMedications()){
            if(m.getMedicineID() == med.getMedicineID()){
                temp = med.getQuantity();
                m.setQuantity(med.getQuantity() + 100);
                System.out.println("Successfully replenished " + med.getMedicineName() + ": " + temp + " --> " + med.getQuantity());
                return;
            }
        }
        System.out.println("Medication " + "'" + med.getMedicineName() + "' is not identified.");  
    }
    /**
     * Displays the inventory list to the administrator, including the medication details such as the ID, name, quantity, and low-stock alert status.
     * 
     * @param inv The inventory to display
     * @param admin The administrator viewing the inventory
     */
    public void checkInventory(Inventory inv, Administrator admin) {
        if (inv.getMedications().isEmpty()) {
            System.out.println("Hi " + admin.getAdminName() + ", the inventory is empty.");
            return;
        }
        
        System.out.println("Hi " + admin.getAdminName() + ", this is the list:");
        System.out.println("---------------------------------------------");
        System.out.printf("%-10s %-20s %-10s %-15s%n", "ID", "Name", "Quantity", "Low Stock Alert");
        System.out.println("---------------------------------------------");
    
        // Iterate through the list of medications and print each one
        for (Medication med : inv.getMedications()) {
            System.out.printf("%-10d %-20s %-10d %-15s%n", 
                              med.getMedicineID(), 
                              med.getMedicineName(), 
                              med.getQuantity(), 
                              med.getLowStockAlert() ? "Yes" : "No");
        }
        System.out.println("---------------------------------------------");
    }
    
    

}
