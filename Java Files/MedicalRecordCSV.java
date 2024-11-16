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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The MedicalRecordCSV class handles reading and writing medical records to a CSV file.
 * It also contains helper methods to find associated patients, diagnoses, treatments, and prescriptions by their IDs.
 */
public class MedicalRecordCSV {

	/**
     * Finds a patient by their hospital ID from a list of patients.
     * 
     * @param hospitalID The hospital ID to search for.
     * @param patients   The list of patients to search in.
     * @return The patient associated with the given hospital ID, or null if not found.
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
     * Finds a diagnosis by its ID from a list of diagnoses.
     * 
     * @param diagnosisID The diagnosis ID to search for.
     * @param diagnoses   The list of diagnoses to search in.
     * @return The diagnosis associated with the given ID, or null if not found.
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
     * Finds a treatment by its ID from a list of treatments.
     * 
     * @param treatmentID The treatment ID to search for.
     * @param treatments The list of treatments to search in.
     * @return The treatment associated with the given ID, or null if not found.
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
     * Finds a prescription by its ID from a list of prescriptions.
     * 
     * @param prescriptionID The prescription ID to search for.
     * @param prescriptions  The list of prescriptions to search in.
     * @return The prescription associated with the given ID, or null if not found.
     */
	private Prescription findPrescriptionByID(String prescriptionID, List<Prescription> prescriptions) {
		for (Prescription prescription : prescriptions) {
			if (prescription.getPrescriptionID().equals(prescriptionID)) {
				return prescription;
			}
		}
		return null;
	}

	// Read Medical Record
	/**
     * Reads medical records from a CSV file and returns a list of MedicalRecord objects.
     * 
     * @param patients    The list of patients to associate with the medical records.
     * @param diagnoses   The list of diagnoses to associate with the medical records.
     * @param treatments  The list of treatments to associate with the medical records.
     * @param prescriptions The list of prescriptions to associate with the medical records.
     * @return A list of MedicalRecord objects read from the CSV file.
     */
	public List<MedicalRecord> loadMedicalRecordsFromCSV(List<Patient> patients, List<Diagnosis> diagnoses,
			List<Treatment> treatments, List<Prescription> prescriptions) {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.MEDICALRECORD_FILE_PATH))) {
			String line;
			br.readLine(); // Skip the header line
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length == 5) { // Adjusted for 5 expected columns

					String RecordID = values[0];
					String hospitalId = values[1];

					// Find Patient by Hospital ID
					Patient patient = findPatientByID(hospitalId, patients);
					if (patient == null) {
						System.out.println("Patient with Hospital ID " + hospitalId + " not found.");
						continue;
					}

					String diagnosisID = values[2];
					// Find Diagnosis by Diagnosis ID
					Diagnosis diagnosis = findDiagnosisByID(diagnosisID, diagnoses);
					if (diagnosis == null) {
						System.out.println("Diagnosis with ID " + diagnosisID + " not found.");
						continue;
					}

					String treatmentID = values[3];
					// Find Treatment by Treatment ID
					Treatment treatment = findTreatmentByID(treatmentID, treatments);
					if (treatment == null) {
						System.out.println("Treatment with ID " + treatmentID + " not found.");
						continue;
					}

					String[] prescriptionIDs = values[4].split(";");
					List<Prescription> prescriptionList = new ArrayList<>();
					for (String prescriptionID : prescriptionIDs) {
						// Find each Prescription by ID
						Prescription prescription = findPrescriptionByID(prescriptionID, prescriptions);
						if (prescription != null) {
							prescriptionList.add(prescription);
						} else {
							System.out.println("Prescription with ID " + prescriptionID + " not found.");
						}
					}

					// Assuming here each record can only have one diagnosis, treatment, and
					// possibly multiple prescriptions
					MedicalRecord record = new MedicalRecord(RecordID, patient, diagnosis, treatment, prescriptionList);
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


	// Add Medical Record
	/**
     * Adds a new medical record and stores it in the CSV file.
     * 
     * @param recordID   The ID of the new medical record.
     * @param patient    The patient associated with the medical record.
     * @param diagnosis  The diagnosis associated with the medical record.
     * @param treatment  The treatment associated with the medical record.
     * @param prescriptions The list of prescriptions associated with the medical record.
     */
	public void addMedicalRecord(String recordID, Patient patient, Diagnosis diagnosis, Treatment treatment,
			List<Prescription> prescriptions) {
		MedicalRecord newMedicalRecord = new MedicalRecord(recordID, patient, diagnosis, treatment, prescriptions);
		writeMedicalRecordToCSV(newMedicalRecord);
	}

	// Store MedicalRecord in CSV
	/**
     * Writes a medical record to the CSV file.
     * 
     * @param record The medical record to write to the CSV file.
     * @return The written MedicalRecord object.
     */
	private MedicalRecord writeMedicalRecordToCSV(MedicalRecord record) {
		try (FileWriter writer = new FileWriter(AppConfig.MEDICALRECORD_FILE_PATH, true)) {


			String csvLine = record.getRecordID()
					+ "," + record.getPatient().getHospitalId() + "," + record.getDiagnosis().getDiagnosisID() + ","
					+ record.getTreatment().getTreatmentID() + "," + record.getPrescriptions().stream()
							.map(prescription -> prescription.getPrescriptionID()).collect(Collectors.joining(";"))
					+ "\n";

		
			writer.write(csvLine);
			System.out.println("New Medical Record added successfully.");

		} catch (IOException e) {
			System.err.println("Error writing to CSV: " + e.getMessage());
		}


		return record;
	}

}
