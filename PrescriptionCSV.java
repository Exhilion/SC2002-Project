package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionCSV {
	private Medication findMedicationByName(String medicationName, List<Medication> medications) {
        for (Medication med : medications) {
            if (med.getMedicineName().equals(medicationName)) {
                return med;
            }
        }
        return null;
    }
    
	//Read file
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