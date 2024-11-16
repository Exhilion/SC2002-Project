package OOPProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Pharmacist class represents a pharmacist user in the system. It extends the User class
 * and provides specific functionality related to the pharmacist's role, such as submitting
 * replenishment requests for medications.
 */
public class Pharmacist extends User {
	private String pharmacistName;

	/**
     * Constructs a Pharmacist object with the specified details.
     *
     * @param hospitalId     The unique ID for the hospital where the pharmacist works.
     * @param password       The password for the pharmacist.
     * @param role           The role of the user, which is 'Pharmacist' in this case.
     * @param gender         The gender of the pharmacist.
     * @param pharmacistName The name of the pharmacist.
     * @param firstTimeLogin Indicates if this is the pharmacist's first login.
     */
    public Pharmacist(String hospitalId, String password, Role role, Gender gender, 
                      String pharmacistName, Boolean firstTimeLogin) {
        super(hospitalId, password, role, gender, firstTimeLogin);
        this.pharmacistName = pharmacistName;
    }

    /**
     * Default constructor for Pharmacist, useful for instantiation without parameters.
     */
    public Pharmacist() {
    }
    
    /**
     * Returns the name of the pharmacist.
     * 
     * @return The name of the pharmacist.
     */
    public String getPharmacistName() {
        return pharmacistName;
    }

    // Method to submit a replenishment request for a medication
    /**
     * Submits a replenishment request for a specific medication. This request is written to a 
     * CSV file (`replenishment_requests.csv`) with the medication's name, requested quantity, 
     * and the status "Pending" for approval.
     * 
     * @param medication The medication for which the replenishment request is being submitted.
     */
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
