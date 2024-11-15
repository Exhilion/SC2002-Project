package OOPProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

    // Parameterized constructor
    public Pharmacist(String hospitalID, String password, Role role, Gender gender, String pharmacistName, Boolean firstTimeLogin) {
        super(hospitalID, password, role, gender, firstTimeLogin);
        this.pharmacistName = pharmacistName;
        this.hospitalID = hospitalID;
        this.role = role;
        this.gender = gender;
    }

    // Getters for pharmacist details
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
    
    // Method to check inventory of medications
    public void checkInventory(List<Medication> medications) {
        if (medications == null || medications.isEmpty()) {
            System.out.println("No medications available in inventory.");
        } else {
            for (Medication medication : medications) {
                medication.printDetails();
            }
        }
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
    
    // Method to update the prescription status of an appointment outcome
    public void updatePrescriptionStatus(AppointmentOutcome outcome, PrescriptionStatus status) {
        outcome.setStatus(status);
        System.out.println("Prescription status for AppointmentOutcome ID: " + outcome.getAppointmentOutcomeID() + " updated to " + status);
    }
    
    // Method to prescribe medications for an appointment outcome
    public void prescribeMedications(AppointmentOutcome outcome, List<Medication> medications) {
        if (outcome == null || medications == null || medications.isEmpty()) {
            System.out.println("Invalid appointment outcome or no medications available.");
            return;
        }

        List<Medication> prescribedMedications = new ArrayList<>();
        List<Integer> prescribedQuantities = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        boolean prescribing = true;
        while (prescribing) {
            // Display available medications
            System.out.println("Select a medication to prescribe:");
            for (int i = 0; i < medications.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + medications.get(i).getMedicineName() + " - Available: " + medications.get(i).getQuantity());
            }

            System.out.print("Enter the number of the medication (or 0 to finish): ");
            int medicationChoice = scanner.nextInt();
            scanner.nextLine();
            
            if (medicationChoice == 0) {
                break;
            }

            if (medicationChoice > 0 && medicationChoice <= medications.size()) {
                Medication selectedMedication = medications.get(medicationChoice - 1);

                // Check if the medication has already been prescribed
                if (prescribedMedications.contains(selectedMedication)) {
                    System.out.println("This medication has already been prescribed. Choose another.");
                    continue;
                }

                System.out.print("Enter the quantity to prescribe: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                
                if (quantity > 0 && quantity <= selectedMedication.getQuantity()) {
                    selectedMedication.reduceQuantity(quantity);
                    prescribedMedications.add(selectedMedication);
                    prescribedQuantities.add(quantity);
                    System.out.println("Added " + quantity + " units of " + selectedMedication.getMedicineName() + " to the prescription.");
                } else {
                    System.out.println("Invalid quantity.");
                }
            } else {
                System.out.println("Invalid medication selection.");
            }

            System.out.print("Do you want to prescribe another medication? (y/n): ");
            String response = scanner.nextLine();
            prescribing = response.equalsIgnoreCase("y");
        }

        if (!prescribedMedications.isEmpty()) {
            // Update the status of the AppointmentOutcome to Dispensed
            updatePrescriptionStatus(outcome, PrescriptionStatus.Dispensed);

            // Display all prescribed medications
            System.out.println("Prescription successful. Medications prescribed:");
            for (int i = 0; i < prescribedMedications.size(); i++) {
                System.out.println("- " + prescribedQuantities.get(i) + " units of " + prescribedMedications.get(i).getMedicineName());
            }
        } else {
            System.out.println("No medications were prescribed.");
        }
    }
    
}
