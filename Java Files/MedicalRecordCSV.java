package secondpart;

import java.io.BufferedReader;
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
			if (diagnosis.getdisgnosisID().equals(diagnosisID)) {
				return diagnosis;
			}
		}
		return null;
	}

	private Treatment findTreatmentByID(String treatmentID, List<Treatment> treatments) {
		for (Treatment treatment : treatments) {
			if (treatment.gettreatmentID().equals(treatmentID)) {
				return treatment;
			}
		}
		return null;
	}

	private Prescription findPrescriptionByID(String prescriptionID, List<Prescription> prescriptions) {
		for (Prescription prescription : prescriptions) {
			if (prescription.getprescriptionID().equals(prescriptionID)) {
				return prescription;
			}
		}
		return null;
	}

	// Read file
	public List<MedicalRecord> loadMedicalRecordsFromCSV(List<Patient> patients, List<Diagnosis> diagnoses,
			List<Treatment> treatments, List<Prescription> prescriptions) {
		List<MedicalRecord> medicalrecords = new ArrayList<>();
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

					String prescriptionID = values[4];
					// Find Prescription by Prescription ID
					Prescription prescription = findPrescriptionByID(prescriptionID, prescriptions);
					if (prescription == null) {
						System.out.println("Prescription with ID " + prescriptionID + " not found.");
						continue;
					}

					MedicalRecord record = new MedicalRecord(RecordID, patient, diagnosis, treatment, prescription);
					medicalrecords.add(record);
				} else {
					System.out.println("Invalid record format: " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return medicalrecords;
	}

}
