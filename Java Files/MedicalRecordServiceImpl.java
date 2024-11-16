package OOPProject;

import java.util.List;

public class MedicalRecordServiceImpl implements MedicalRecordService {

	@Override
	public void addMedicalRecord(String recordID, Patient patient, Diagnosis diagnosis, Treatment treatment,
			List<Prescription> prescriptions) {
		MedicalRecord newMedicalRecord = new MedicalRecord(recordID, patient, diagnosis, treatment, prescriptions);
		MedicalRecordCSV medicalRecordCSV = new MedicalRecordCSV();
		medicalRecordCSV.addMedicalRecord(recordID, patient, diagnosis, treatment, prescriptions);
		System.out.println("Medical record added for Patient ID " + patient.getHospitalId());
	}
}
