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

public class MedicalRecordCSV {
	
	private Patient findPatientByID(String hospitalID, List<Patient> patients) {
		for (Patient patient : patients) {
			if (patient.getHospitalId().equals(hospitalID)) {
				return patient;
			}
		}
		return null;
	}

	private Diagnosis findDiagnosisByID(String diagnosisID, List<Diagnosis> diagnoses) {
		for (Diagnosis diagnosis : diagnoses) {
			if (diagnosis.getDiagnosisID().equals(diagnosisID)) {
				return diagnosis;
			}
		}
		return null;
	}

	private Treatment findTreatmentByID(String treatmentID, List<Treatment> treatments) {
		for (Treatment treatment : treatments) {
			if (treatment.getTreatmentID().equals(treatmentID)) {
				return treatment;
			}
		}
		return null;
	}

	private Prescription findPrescriptionByID(String prescriptionID, List<Prescription> prescriptions) {
		for (Prescription prescription : prescriptions) {
			if (prescription.getPrescriptionID().equals(prescriptionID)) {
				return prescription;
			}
		}
		return null;
	}
	


	// Read Medical Record
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

	                // Assuming here each record can only have one diagnosis, treatment, and possibly multiple prescriptions
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


	// Randomly generated Medical Record ID
	public String generateMedicalRecordID() {
		return "MR" + UUID.randomUUID().toString().substring(0, 8);
	}

	// Add Medical Record
	public void addMedicalRecord(String recordID, Patient patient, Diagnosis diagnosis, Treatment treatment,
			List<Prescription> prescriptions) {
		MedicalRecord newMedicalRecord = new MedicalRecord(recordID, patient, diagnosis, treatment, prescriptions);
		writeMedicalRecordToCSV(newMedicalRecord);
	}

	// Store MedicalRecord in CSV
	private void writeMedicalRecordToCSV(MedicalRecord record) {
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
	}


}
