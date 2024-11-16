package OOPProject;

import java.io.*;
import java.util.ArrayList;

public class Administrator extends User {
    private String adminName;
    private int adminID;

    public Administrator(String hospitalId, String password, Role role, Gender gender, 
                         String adminName, Boolean firstTimeLogin) {
        super(hospitalId, password, role, gender, firstTimeLogin);
        this.adminName = adminName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    
    

    // Method to approve replenishment requests submitted by pharmacists
    public void approveReplenishmentRequests(Inventory inventory) {
        String filePath = "replenishment_requests.csv";
        ArrayList<String> updatedLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 3) continue;

                String medicationName = data[0].trim();
                int requestedQuantity;
                try {
                    requestedQuantity = Integer.parseInt(data[1].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity in request: " + data[1]);
                    updatedLines.add(line);
                    continue;
                }
                String status = data[2].trim();

                if ("Pending".equalsIgnoreCase(status)) {
                    Medication medication = findMedicationByName(inventory, medicationName);
                    if (medication != null) {
                        // Update the inventory with the requested quantity
                        int currentQuantity = medication.getQuantity();
                        medication.setQuantity(currentQuantity + requestedQuantity);
                        medication.updateLowStockAlert(medication);

                        System.out.println("Approved replenishment for " + medicationName + 
                                           ": " + currentQuantity + " --> " + medication.getQuantity());
                        updatedLines.add(medicationName + "," + requestedQuantity + ",Approved");
                    } else {
                        System.out.println("Medication " + medicationName + " not found in inventory.");
                        updatedLines.add(medicationName + "," + requestedQuantity + ",Rejected");
                    }
                } else {
                    updatedLines.add(line); // Keep non-pending requests as is
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading replenishment requests file: " + e.getMessage());
        }

        // Write back the updated status to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String updatedLine : updatedLines) {
                bw.write(updatedLine);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to replenishment requests file: " + e.getMessage());
        }
    }

    // Helper method to find a medication by name in the inventory
    private Medication findMedicationByName(Inventory inventory, String medicationName) {
        for (Medication med : inventory.getMedications()) {
            if (med.getMedicineName().equalsIgnoreCase(medicationName)) {
                return med;
            }
        }
        return null;
    }
}
