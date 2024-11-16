package OOPProject;

import java.io.BufferedReader;
import java.util.UUID;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code MedicalRecordCSV} class handles operations related to medical records
 * stored in a CSV file. It provides functionality for loading, adding, updating,
 * and storing medical records.
 */
public class MedicalRecordCSV {

    /**
     * Finds a patient by their hospital ID from the list of patients.
     *
     * @param hospitalID The hospital ID of the patient to find.
     * @param patients   The list of {@link Patient} objects to search.
     * @return The {@link Patient} object if found; otherwise, {@code null}.
     */
    private Patient findPatientByID(String hospitalID, List<Patient> patients) {
        for (Patient patient : patients) {
            if (patient.getHospitalId().equals(hospitalID)) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Finds a diagnosis by its ID from the list of diagnoses.
     *
     * @param diagnosisID The ID of the diagnosis to find.
     * @param diagnoses   The list of {@link Diagnosis} objects to search.
     * @return The {@link Diagnosis} object if found; otherwise, {@code null}.
     */
    private Diagnosis findDiagnosisByID(String diagnosisID, List<Diagnosis> diagnoses) {
        for (Diagnosis diagnosis : diagnoses) {
            if (diagnosis.getDiagnosisID().equals(diagnosisID)) {
                return diagnosis;
            }
        }
        return null;
    }

    /**
     * Finds a treatment by its ID from the list of treatments.
     *
     * @param treatmentID The ID of the treatment to find.
     * @param treatments  The list of {@link Treatment} objects to search.
     * @return The {@link Treatment} object if found; otherwise, {@code null}.
     */
    private Treatment findTreatmentByID(String treatmentID, List<Treatment> treatments) {
        for (Treatment treatment : treatments) {
            if (treatment.getTreatmentID().equals(treatmentID)) {
                return treatment;
            }
        }
        return null;
    }

    /**
     * Finds a prescription by its ID from the list of prescriptions.
     *
     * @param prescriptionID The ID of the prescription to find.
     * @param prescriptions  The list of {@link Prescription} objects to search.
     * @return The {@link Prescription} object if found; otherwise, {@code null}.
     */
    private Prescription findPrescriptionByID(String prescriptionID, List<Prescription> prescriptions) {
        for (Prescription prescription : prescriptions) {
            if (prescription.getPrescriptionID().equals(prescriptionID)) {
                return prescription;
            }
        }
        return null;
    }

    /**
     * Loads medical records from the CSV file. Links the medical records with their associated
     * patients, diagnoses, treatments, and prescriptions.
     *
     * @param patients      The list of {@link Patient} objects.
     * @param diagnoses     The list of {@link Diagnosis} objects.
     * @param treatments    The list of {@link Treatment} objects.
     * @param prescriptions The list of {@link Prescription} objects.
     * @return A list of {@link MedicalRecord} objects loaded from the CSV file.
     */
    public List<MedicalRecord> loadMedicalRecordsFromCSV(List<Patient> patients, List<Diagnosis> diagnoses,
                                                         List<Treatment> treatments, List<Prescription> prescriptions) {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.MEDICALRECORD_FILE_PATH))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) {
                    String recordID = values[0];
                    String hospitalId = values[1];

                    Patient patient = findPatientByID(hospitalId, patients);
                    if (patient == null) {
                        System.out.println("Patient with Hospital ID " + hospitalId + " not found.");
                        continue;
                    }

                    Diagnosis diagnosis = findDiagnosisByID(values[2], diagnoses);
                    if (diagnosis == null) {
                        System.out.println("Diagnosis with ID " + values[2] + " not found.");
                        continue;
                    }

                    Treatment treatment = findTreatmentByID(values[3], treatments);
                    if (treatment == null) {
                        System.out.println("Treatment with ID " + values[3] + " not found.");
                        continue;
                    }

                    String[] prescriptionIDs = values[4].split(";");
                    List<Prescription> prescriptionList = new ArrayList<>();
                    for (String prescriptionID : prescriptionIDs) {
                        Prescription prescription = findPrescriptionByID(prescriptionID, prescriptions);
                        if (prescription != null) {
                            prescriptionList.add(prescription);
                        } else {
                            System.out.println("Prescription with ID " + prescriptionID + " not found.");
                        }
                    }

                    MedicalRecord record = new MedicalRecord(recordID, patient, diagnosis, treatment, prescriptionList);
                    medicalRecords.add(record);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return medicalRecords;
    }

    /**
     * Adds a new medical record to the CSV file.
     *
     * @param recordID      The unique ID of the medical record.
     * @param patient       The {@link Patient} associated with the medical record.
     * @param diagnosis     The {@link Diagnosis} associated with the medical record.
     * @param treatment     The {@link Treatment} associated with the medical record.
     * @param prescriptions The list of {@link Prescription} objects associated with the medical record.
     */
    public void addMedicalRecord(String recordID, Patient patient, Diagnosis diagnosis, Treatment treatment,
                                 List<Prescription> prescriptions) {
        MedicalRecord newMedicalRecord = new MedicalRecord(recordID, patient, diagnosis, treatment, prescriptions);
        writeMedicalRecordToCSV(newMedicalRecord);
    }

    /**
     * Writes a single medical record to the CSV file.
     *
     * @param record The {@link MedicalRecord} object to write.
     * @return The written {@link MedicalRecord} object.
     */
    private MedicalRecord writeMedicalRecordToCSV(MedicalRecord record) {
        try (FileWriter writer = new FileWriter(AppConfig.MEDICALRECORD_FILE_PATH, true)) {
            String csvLine = record.getRecordID() + "," +
                             record.getPatient().getHospitalId() + "," +
                             record.getDiagnosis().getDiagnosisID() + "," +
                             record.getTreatment().getTreatmentID() + "," +
                             record.getPrescriptions().stream()
                                   .map(Prescription::getPrescriptionID)
                                   .collect(Collectors.joining(";")) + "\n";
            writer.write(csvLine);
            System.out.println("New Medical Record added successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
        return record;
    }

    /**
     * Updates an existing medical record in the CSV file.
     *
     * @param recordID      The ID of the medical record to update.
     * @param patientID     The ID of the patient associated with the medical record.
     * @param diagnosis     The updated {@link Diagnosis}.
     * @param treatment     The updated {@link Treatment}.
     * @param prescriptions The updated list of {@link Prescription} objects.
     */
    public void updateMedicalRecord(String recordID, String patientID, Diagnosis diagnosis, Treatment treatment,
                                    List<Prescription> prescriptions) {
        List<String[]> medicalRecords = new ArrayList<>();
        boolean recordUpdated = false;

        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.MEDICALRECORD_FILE_PATH))) {
            String line = br.readLine();
            medicalRecords.add(line.split(",")); // Add header

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase(recordID) && values[1].trim().equalsIgnoreCase(patientID)) {
                    values[2] = diagnosis.getDiagnosisID();
                    values[3] = treatment.getTreatmentID();
                    values[4] = prescriptions.stream()
                                             .map(Prescription::getPrescriptionID)
                                             .collect(Collectors.joining(";"));
                    recordUpdated = true;
                }
                medicalRecords.add(values);
            }
        } catch (IOException e) {
            System.out.println("Error reading the medical records file: " + e.getMessage());
        }

        if (recordUpdated) {
            writeMedicalRecordsToFile(medicalRecords);
        } else {
            System.out.println("Medical Record ID not found for the given patient.");
        }
    }

    /**
     * Writes the updated list of medical records to the CSV file.
     *
     * @param medicalRecords The list of updated medical records.
     */
    private void writeMedicalRecordsToFile(List<String[]> medicalRecords) {
        try (FileWriter writer = new FileWriter(AppConfig.MEDICALRECORD_FILE_PATH)) {
            for (String[] record : medicalRecords) {
                writer.write(String.join(",", record) + "\n");
            }
            System.out.println("Medical Record updated successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to medical records file: " + e.getMessage());
        }
    }
}