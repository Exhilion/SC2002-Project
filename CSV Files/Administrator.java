package OOPProject;

import java.io.*;
import java.util.*;

import javax.management.relation.Role;

public class Administrator extends User {
    private String adminName;
    private int adminID;

    public Administrator(String hospitalId, String password, Role role, Gender gender, 
                         String adminName, Boolean firstTimeLogin) {
        super(hospitalId, password, role, gender, firstTimeLogin);
        this.adminName = adminName;
    }

    public Administrator() {}

    // Method to approve replenishment requests
    public void approveReplenishmentRequests(Inventory inventory) {
        String filePath = "replenishment_requests.csv";
        ArrayList<String> updatedLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 3) continue;

                String medicationName = data[0].trim();
                int requestedQuantity = Integer.parseInt(data[1].trim());
                String status = data[2].trim();

                if ("Pending".equalsIgnoreCase(status)) {
                    Medication medication = findMedicationByName(inventory, medicationName);
                    if (medication != null) {
                        int currentQuantity = medication.getQuantity();
                        medication.setQuantity(currentQuantity + requestedQuantity);
                        medication.updateLowStockAlert();
                        updatedLines.add(medicationName + "," + requestedQuantity + ",Approved");
                        System.out.println("Approved replenishment for " + medicationName + 
                                           ": " + currentQuantity + " --> " + medication.getQuantity());
                    } else {
                        updatedLines.add(medicationName + "," + requestedQuantity + ",Rejected");
                        System.out.println("Medication " + medicationName + " not found in inventory.");
                    }
                } else {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading replenishment requests file: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String updatedLine : updatedLines) {
                bw.write(updatedLine);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to replenishment requests file: " + e.getMessage());
        }
    }

    private Medication findMedicationByName(Inventory inventory, String medicationName) {
        for (Medication med : inventory.getMedications()) {
            if (med.getMedicineName().equalsIgnoreCase(medicationName)) {
                return med;
            }
        }
        return null;
    }

    // Method to update hospital staff details
    public void updateStaff(String staffID, String newName, Role newRole, Gender newGender) {
    StaffCSV staffCSV = new StaffCSV();
    staffCSV.updateStaffDetails(staffID, newName, newRole, newGender);
}

public void removeStaff(String staffID) {
    StaffCSV staffCSV = new StaffCSV();
    boolean removed = staffCSV.removeStaffById(staffID);
    if (removed) {
        System.out.println("Staff removed successfully.");
    } else {
        System.out.println("Staff with ID " + staffID + " not found.");
    }
}

}
