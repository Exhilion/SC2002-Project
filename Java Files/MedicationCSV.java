package OOPProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code MedicationCSV} class provides functionality for managing medication data
 * stored in a CSV file. It includes methods for loading, updating, and saving medication records.
 */
public class MedicationCSV {

    /**
     * Loads all medication records from the CSV file.
     *
     * @return A list of {@link Medication} objects loaded from the CSV file.
     */
    public List<Medication> loadMedicationsFromCSV() {
        List<Medication> medications = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.MEDICATION_FILE_PATH))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) { // Expecting 6 columns: medicineID, medicineName, quantity, lowQuantity, lowStockAlert, requestRestock
                    int medicineID = Integer.parseInt(values[0]);
                    String medicineName = values[1];
                    int quantity = Integer.parseInt(values[2]);
                    int lowQuantity = Integer.parseInt(values[3]);
                    boolean lowStockAlert = Boolean.parseBoolean(values[4]);
                    String requestRestock = values[5];

                    // Create Medication object and add it to the list
                    Medication medication = new Medication(medicineID, medicineName, quantity, lowQuantity,
                            lowStockAlert, requestRestock);
                    medications.add(medication);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return medications;
    }

    /**
     * Updates the quantity of a medication identified by its medicine ID.
     *
     * @param medicineID  The ID of the medication to update.
     * @param newQuantity The new quantity to set for the medication.
     */
    public void updateMedicationQuantity(int medicineID, int newQuantity) {
        List<Medication> medications = loadMedicationsFromCSV(); // Load medications from CSV

        // Find the medication by its ID and update the quantity
        for (Medication med : medications) {
            if (med.getMedicineID() == medicineID) {
                med.setQuantity(newQuantity);
                System.out.println("Quantity updated for " + med.getMedicineName() + " to " + newQuantity);
                break;
            }
        }

        saveMedicationsToCSV(medications);
    }

    /**
     * Updates the restock request status of a medication identified by its medicine ID.
     *
     * @param medicineID              The ID of the medication to update.
     * @param newRequestRestockStatus The new restock request status to set.
     */
    public void updateRestockRequestStatus(int medicineID, String newRequestRestockStatus) {
        List<Medication> medications = loadMedicationsFromCSV(); // Load medications from CSV

        // Find the medication by its ID and update the restock request status
        for (Medication med : medications) {
            if (med.getMedicineID() == medicineID) {
                med.setRequestRestock(newRequestRestockStatus);
                System.out.println("Restock request status updated for " + med.getMedicineName() + " to "
                        + newRequestRestockStatus);
                break;
            }
        }

        // Save the updated list back to the CSV
        saveMedicationsToCSV(medications);
    }

    /**
     * Saves the updated list of medications to the CSV file.
     *
     * @param medications The list of {@link Medication} objects to save.
     */
    private void saveMedicationsToCSV(List<Medication> medications) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AppConfig.MEDICATION_FILE_PATH))) {
            // Write header
            bw.write("medicineID,medicineName,quantity,lowQuantity,lowStockAlert,requestRestock\n");
            for (Medication med : medications) {
                bw.write(med.getMedicineID() + "," + med.getMedicineName() + "," + med.getQuantity() + ","
                        + med.getLowQuantity() + "," + med.getLowStockAlert() + "," + med.getRequestRestock() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    /**
     * Updates the low stock alert status of a medication based on its current quantity
     * and low stock threshold.
     *
     * @param med The {@link Medication} object to update.
     */
    public void updateLowStockAlert(Medication med) {
        if (med.getLowQuantity() >= med.getQuantity())
            med.setlowstockAlert(true);
        else
            med.setlowstockAlert(false);
    }
}