package OOPProject;

import java.util.List;

/**
 * The MedicalRecordServiceImpl class provides an implementation of the MedicalRecordService interface.
 * It handles operations related to adding new medical records to the system.
 */
public class MedicalRecordServiceImpl implements MedicalRecordService {

	/**
     * Adds a new medical record to the system.
     *
     * @param recordID The unique identifier for the new medical record.
     * @param patient The patient associated with the new medical record.
     * @param diagnosis The diagnosis associated with the new medical record.
     * @param treatment The treatment associated with the new medical record.
     * @param prescriptions The list of prescriptions associated with the new medical record.
     */
	@Override
	public void addMedicalRecord(String recordID, Patient patient, Diagnosis diagnosis, Treatment treatment,
			List<Prescription> prescriptions) {
		MedicalRecord newMedicalRecord = new MedicalRecord(recordID, patient, diagnosis, treatment, prescriptions);
		MedicalRecordCSV medicalRecordCSV = new MedicalRecordCSV();
		medicalRecordCSV.addMedicalRecord(recordID, patient, diagnosis, treatment, prescriptions);
		System.out.println("Medical record added for Patient ID " + patient.getHospitalId());
	}
}
