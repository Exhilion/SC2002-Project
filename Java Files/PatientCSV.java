package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code PatientCSV} class provides functionality to manage patient data stored in a CSV file.
 * It includes methods to load patient records, update patient information, and write updated data
 * back to the CSV file.
 */
public class PatientCSV {

    /**
     * Loads patient records from the CSV file specified in {@code AppConfig.PATIENT_FILE_PATH}.
     *
     * @return A list of {@link Patient} objects parsed from the CSV file.
     */
    public List<Patient> viewPatientRecords() {
        List<Patient> patients = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/DD/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.PATIENT_FILE_PATH))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 10) {
                    try {
                        String hospitalID = values[0];
                        String password = values[1];
                        Role role = Role.valueOf(values[2].toUpperCase());
                        Gender gender = Gender.valueOf(values[3].toUpperCase());
                        String name = values[4];
                        Date dateOfBirth = dateFormat.parse(values[5]);
                        BloodType bloodType = BloodType.valueOf(values[6].toUpperCase());
                        String phoneNumber = values[7];
                        String email = values[8];
                        Boolean firstTimeLogin = Boolean.parseBoolean(values[9].trim());

                        Patient patient = new Patient(hospitalID, password, role, gender, name, dateOfBirth,
                                bloodType, phoneNumber, email, firstTimeLogin);
                        patients.add(patient);
                    } catch (Exception e) {
                        System.out.println("Error processing record: " + line + " | " + e.getMessage());
                    }
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patients;
    }

    /**
     * Updates the patient records in the CSV file based on the given patient ID, choice of column to update,
     * and the new value.
     *
     * @param patientID The ID of the patient whose record is to be updated.
     * @param choice    The column to update (1 for email, 2 for contact number).
     * @param newValue  The new value for the chosen column.
     */
    public static void updatePatientRecords(String patientID, int choice, String newValue) {
        List<String[]> records = new ArrayList<>();
        boolean recordUpdated = false;

        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.PATIENT_FILE_PATH))) {
            String line = br.readLine(); // Read header
            records.add(line.split(","));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase(patientID)) {
                    if (choice == 1) {
                        values[8] = newValue; // Update Email
                    } else if (choice == 2) {
                        values[7] = newValue; // Update Contact Number
                    }
                    recordUpdated = true;
                }
                records.add(values);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        if (recordUpdated) {
            writeRecordsToFile(records);
        } else {
            System.out.println("Patient ID not found.");
        }
    }

    /**
     * Helper method to write updated patient records back to the CSV file.
     *
     * @param records A list of string arrays representing updated patient records.
     */
    private static void writeRecordsToFile(List<String[]> records) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(AppConfig.PATIENT_FILE_PATH))) {
            for (String[] record : records) {
                pw.println(String.join(",", record));
            }
            System.out.println("Patient record updated successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}