package OOPProject;

/**
 * The class contains constant file paths for various CSV files used in the Hospital Management System.
 * These file paths are used throughout the application to read and write data for different entities such as diagnoses,
 * prescriptions, treatments, medical records, medications, patients, doctors, pharmacists, administrators, and appointments.
 * <p>
 * The class provides a centralized place to manage the paths to CSV files, making it easier to update file locations in 
 * the future without having to modify code across multiple classes.
 * </p>
 */
public class AppConfig {
	/**
	 * The file path to the Diagnosis CSV file.
     * This file stores patient diagnosis data.
	 */
    public static final String DIAGNOSIS_FILE_PATH = "src\\\\OOPProject\\\\Diagnosis.csv";
    /**
     * The file path to the Prescription CSV file.
     * This file contains information about prescribed medications and treatments for patients.
     */
     */
    public static final String PRESCRIPTION_FILE_PATH = "src\\\\OOPProject\\\\Prescription.csv";
    /**
     * The file path to the Treatment CSV file.
     * This file stores data related to treatments administered to patients.
     */
    public static final String TREATMENT_FILE_PATH = "src\\\\OOPProject\\\\Treatment.csv";
    /**
     * The file path to the MedicalRecord CSV file.
     * This file contains a record of patient medical history and treatment details.
     */
    public static final String MEDICALRECORD_FILE_PATH = "src\\\\OOPProject\\\\MedicalRecord.csv";
    /**
     * The file path to the Medication CSV file.
     * This file stores information on the available medications in the hospital.
     */
    public static final String MEDICATION_FILE_PATH = "src\\\\OOPProject\\\\Medication.csv";
    /**
     *
     * The file path to the Patient CSV file.
     * This file contains details about the patients, including their personal and medical information.
     */
    public static final String PATIENT_FILE_PATH = "src\\\\OOPProject\\\\Patient.csv";
    /**
     * The file path to the Doctor CSV file.
     * This file stores information about doctors working in the hospital.
     */
    public static final String DOCTOR_FILE_PATH = "src\\\\OOPProject\\\\Doctor.csv";
    /**
     * The file path to the Pharmacist CSV file.
     * This file contains data about pharmacists working in the hospital.
     */
    public static final String PHARMACIST_FILE_PATH = "src\\\\OOPProject\\\\Pharmacist.csv";
    /**
     * The file path to the Admin CSV file.
     * This file stores information on administrators in the hospital system.
     */
    public static final String ADMIN_FILE_PATH = "src\\\\OOPProject\\\\Admin.csv";
    /**
     * The file path to the AppointmentSlot CSV file.
     * This file contains data regarding available time slots for appointments.
     */
    public static final String APPOINTMENT_SLOT_FILE_PATH = "src\\\\OOPProject\\\\AppointmentSlot.csv";
    /**
     * The file path to the Appointment CSV file.
     * This file stores details of patient appointments, including date and time.
     */
    public static final String APPOINTMENT_FILE_PATH = "src\\\\OOPProject\\\\Appointment.csv";
    /**
     * The file path to the AppointmentOutcome CSV file.
     * This file contains the outcome of appointments, including diagnosis and treatment recommendations.
     */
    public static final String APPOINTMENT_OUTCOME_FILE_PATH = "src\\\\OOPProject\\\\AppointmentOutcome.csv";
  
}