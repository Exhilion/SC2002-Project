package OOPProject;
import java.util.ArrayList;

/**
 * The Inventory class manages a collection of medications, allowing for adding,
 * removing, and displaying medications in the inventory.
 */
public class Inventory {
	
	/**
     * A list to store all medications in the inventory.
     */
	private ArrayList<Medication> medications;
	
	/**
     * Constructor that initializes an empty list of medications.
     */
    public Inventory() {
        this.medications = new ArrayList<>();
    }

    /**
     * Adds a new medication to the inventory.
     *
     * @param medication The medication to be added to the inventory.
     */
    public void addMedication(Medication medication) {
        medications.add(medication);
        System.out.println("Added: " + medication.getMedicineName());
    }

    /**
     * Removes a medication from the inventory based on its ID.
     *
     * @param medicationID The ID of the medication to be removed.
     */
    public void removeMedication(int medicationID){
        for (Medication med : medications) {
            if (med.getMedicineID() == medicationID) {
                medications.remove(med);
                System.out.println("Removed: " + med.getMedicineName());
                return;
            }
        }
        System.out.println("Medication with ID " + medicationID + " not found.");
    }

    /**
     * Returns the list of all medications in the inventory.
     *
     * @return The list of medications in the inventory.
     */
    public ArrayList<Medication> getMedications() {
        return medications;
    }

    /**
     * Displays a formatted list of all medications in the inventory, including their
     * ID, name, quantity, and low stock alert status.
     */
    public void displayInventory() {
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
}
