package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading and parsing of prescription data from a CSV file.
 * Provides functionality to link prescriptions with their respective medications.
 */
public class PrescriptionCSV {

    /**
     * Finds a medication by its name from the list of available medications.
     *
     * @param medicationName the name of the medication to find.
     * @param medications the list of medications to search.
     * @return the {@link Medication} object if found, otherwise {@code null}.
     */
    private Medication findMedicationByName(String medicationName, List<Medication> medications) {
        for (Medication med : medications) {
            if (med.getMedicineName().equals(medicationName)) {
                return med;
            }
        }
        return null;
    }

    /**
     * Loads a list of prescriptions from a CSV file and links them to their respective medications.
     *
     * <p>The CSV file is expected to have the following format:</p>
     * <ul>
     *     <li>Column 1: Prescription ID</li>
     *     <li>Column 2: Dosage (in mg)</li>
     *     <li>Column 3: Duration (e.g., "7 days")</li>
     *     <li>Column 4: Medication Name</li>
     *     <li>Column 5: Frequency (times per day)</li>
     * </ul>
     *
     * <p>If a medication cannot be found by its name, the prescription is skipped, and an error message is logged.</p>
     *
     * @param medications the list of available medications.
     * @return a list of {@link Prescription} objects loaded from the CSV file.
     */
    public List<Prescription> loadPrescriptionsFromCSV(List<Medication> medications) {
        List<Prescription> prescriptions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.PRESCRIPTION_FILE_PATH))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) { // Ensure correct format
                    String prescriptionID = values[0];
                    String medicationName = values[3];
                    Medication medication = findMedicationByName(medicationName, medications);

                    if (medication == null) {
                        System.out.println("Medication with Name " + medicationName + " not found.");
                        continue;
                    }
                    int dosage = Integer.parseInt(values[1]);
                    int frequency = Integer.parseInt(values[4]);
                    String duration = values[2];

                    Prescription prescription = new Prescription(prescriptionID, medication, dosage, frequency, duration);
                    prescriptions.add(prescription);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prescriptions;
    }
}