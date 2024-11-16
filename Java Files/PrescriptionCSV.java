package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The PrescriptionCSV class handles the loading of prescription data from a CSV file. 
 * It parses the file to create a list of Prescription objects, associating each prescription 
 * with the correct Medication based on the medication name.
 */
public class PrescriptionCSV {
	
	/**
     * Finds a Medication object by its name from the list of medications.
     * 
     * @param medicationName The name of the medication to search for.
     * @param medications A list of Medication objects.
     * @return The Medication object if found, otherwise null.
     */
	private Medication findMedicationByName(String medicationName, List<Medication> medications) {
        for (Medication med : medications) {
            if (med.getMedicineName().equals(medicationName)) {
                return med;
            }
        }
        return null;
    }
    
	//Read file
	/**
     * Loads a list of prescriptions from a CSV file, associating each prescription 
     * with a medication from the provided list of medications.
     * 
     * @param medications The list of medications available for matching with the prescription.
     * @return A list of Prescription objects loaded from the CSV file.
     */
    public List<Prescription> loadPrescriptionsFromCSV(List<Medication> medications) {
        List<Prescription> prescriptions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.PRESCRIPTION_FILE_PATH))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) { // Adjusted for 5 expected columns
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
