package HospitalProject;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Medication> medications;
    public Inventory() {
        this.medications = new ArrayList<>();
    }

    public void addMedication(Medication medication) {
        medications.add(medication);
        System.out.println("Added: " + medication.getMedicineName());
    }

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

    public ArrayList<Medication> getMedications() {
        return medications;
    }

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
