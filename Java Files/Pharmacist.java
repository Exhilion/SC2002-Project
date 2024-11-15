package OOPProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Pharmacist extends User {
	private String pharmacistName;

    public Pharmacist(String hospitalId, String password, Role role, Gender gender, 
                      String pharmacistName, Boolean firstTimeLogin) {
        super(hospitalId, password, role, gender, firstTimeLogin);
        this.pharmacistName = pharmacistName;
    }

    public String getPharmacistName() {
        return pharmacistName;
    }
    public Pharmacist() {
    	
    }
    
    // Method to submit a replenishment request for a medication
    public void submitReplenishmentRequest(Medication medication) {
        System.out.println("Submitting replenishment request for: " + medication.getMedicineName());

        // Add the replenishment request to a CSV file
        String filePath = "replenishment_requests.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = medication.getMedicineName() + "," + medication.getQuantity() + ",Pending";
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to replenishment requests file: " + e.getMessage());
        }

        System.out.println("Replenishment request submitted successfully. Pending approval from the administrator.");
    }

}