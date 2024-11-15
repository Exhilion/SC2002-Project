package OOPProject;

import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Pharmacist extends User {
    private String pharmacistName;
    private String hospitalID;
    private Role role;
    private Gender gender;

    // Default constructor
    public Pharmacist() {
        super(null, null, null, null, false);
    }

    public Pharmacist(String hospitalID, String password, Role role, Gender gender, String pharmacistName, Boolean firstTimeLogin) {
        super(hospitalID, password, role, gender, firstTimeLogin);
        this.pharmacistName = pharmacistName;
        this.hospitalID = hospitalID;
        this.role = role;
        this.gender = gender;
    }

    public String getPharmacistName() {
        return pharmacistName;
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public Role getRole() {
        return role;
    }

    public Gender getGender() {
        return gender;
    }

    // Method to view appointment outcome records
    public void viewAppointmentOutcomeRecords(List<AppointmentOutcome> outcomesToView) {
        for (AppointmentOutcome outcome : outcomesToView) {
            outcome.printDetails();
        }
    }
    
    public void checkInventory(List<Medication> medications) {
        if (medications == null || medications.isEmpty()) {
            System.out.println("No medications available in inventory.");
        } else {
            for (Medication medication : medications) {
                medication.printDetails();
            }
        }
    }
    
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