package OOPProject;

import java.util.List;

/**
 * This class is responsible for loading data from CSV files into the
 * corresponding lists. The data includes patients, treatments, medications,
 * diagnoses, prescriptions, medical records, doctors, appointment slots,
 * appointments, and appointment outcomes.
 */
public class loadCSVClass {
	private List<Patient> patients;
	private List<Treatment> treatments;
	private List<Medication> medications;
	private List<Diagnosis> diagnoses;
	private List<Prescription> prescriptions;
	private List<MedicalRecord> medicalRecords;
	private List<AppointmentSlot> appointmentSlots;
	private List<Doctor> doctors;
	private List<Appointment> appointments;
	private List<AppointmentOutcome> appointmentOutcomes;

	/**
	 * Constructor to load all CSV data by invoking respective CSV loader classes.
	 * It loads the data for all entities including patients, treatments,
	 * medications, diagnoses, prescriptions, medical records, doctors, appointment
	 * slots, appointments, and appointment outcomes.
	 */
	public loadCSVClass() {

		PatientCSV patientCSV = new PatientCSV();
		this.patients = patientCSV.viewPatientRecords();

		TreatmentCSV treatmentCSV = new TreatmentCSV();
		this.treatments = treatmentCSV.loadTreatmentsFromCSV();

		MedicationCSV medicationCSV = new MedicationCSV();
		this.medications = medicationCSV.loadMedicationsFromCSV();

		DiagnosisCSV diagnosisCSV = new DiagnosisCSV();
		this.diagnoses = diagnosisCSV.loadDiagnosisFromCSV();

		PrescriptionCSV prescriptionCSV = new PrescriptionCSV();
		this.prescriptions = prescriptionCSV.loadPrescriptionsFromCSV(this.medications);

		MedicalRecordCSV medicalRecordCSV = new MedicalRecordCSV();
		this.medicalRecords = medicalRecordCSV.loadMedicalRecordsFromCSV(this.patients, this.diagnoses, this.treatments,
				this.prescriptions);

		DoctorCSV doctorCSV = new DoctorCSV();
		this.doctors = doctorCSV.loadDoctorsFromCSV();

		AppointmentSlotCSV appointmentSlotCSV = new AppointmentSlotCSV();
		this.appointmentSlots = appointmentSlotCSV.loadAppointmentSlotsFromCSV(doctors);

		AppointmentCSV appointmentCSV = new AppointmentCSV();
		this.appointments = appointmentCSV.loadAppointmentsFromCSV(appointmentSlots, patients);

		AppointmentOutcomeCSV appointmentOutcomeCSV = new AppointmentOutcomeCSV();
		this.appointmentOutcomes = appointmentOutcomeCSV.loadAppointmentOutcomesFromCSV(appointments, medicalRecords);
	}

	/**
	 * Gets the list of patients loaded from the CSV file.
	 * 
	 * @return List of Patient objects.
	 */
	public List<Patient> getPatients() {
		return patients;
	}

	/**
	 * Gets the list of treatments loaded from the CSV file.
	 * 
	 * @return List of Treatment objects.
	 */
	public List<Treatment> getTreatments() {
		return treatments;
	}

	/**
	 * Gets the list of medications loaded from the CSV file.
	 * 
	 * @return List of Medication objects.
	 */
	public List<Medication> getMedications() {
		return medications;
	}

	/**
	 * Gets the list of diagnoses loaded from the CSV file.
	 * 
	 * @return List of Diagnosis objects.
	 */
	public List<Diagnosis> getDiagnoses() {
		return diagnoses;
	}

	/**
	 * Gets the list of prescriptions loaded from the CSV file.
	 * 
	 * @return List of Prescription objects.
	 */
	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	/**
	 * Gets the list of medical records loaded from the CSV file.
	 * 
	 * @return List of MedicalRecord objects.
	 */
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}

	/**
	 * Gets the list of doctors loaded from the CSV file.
	 * 
	 * @return List of Doctor objects.
	 */
	public List<Doctor> getDoctors() {
		return doctors;
	}

	/**
	 * Gets the list of appointment slots loaded from the CSV file.
	 * 
	 * @return List of AppointmentSlot objects.
	 */
	public List<AppointmentSlot> getAppointmentSlots() {
		return appointmentSlots;
	}

	/**
	 * Gets the list of appointments loaded from the CSV file.
	 * 
	 * @return List of Appointment objects.
	 */
	public List<Appointment> getAppointments() {
		return appointments;
	}

	/**
	 * Gets the list of appointment outcomes loaded from the CSV file.
	 * 
	 * @return List of AppointmentOutcome objects.
	 */
	public List<AppointmentOutcome> getAppointmentOutcomes() {
		return appointmentOutcomes;
	}
}
