package OOPProject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The AppointmentOutcome class represents the outcome of a patient's appointment.
 * It holds the appointment details, medical record, consultation notes, and prescription status.
 * This class provides methods to print the details of the appointment outcome, group outcomes by prescription status,
 * and filter outcomes by their status.
 */
public class AppointmentOutcome {
    private String appointmentOutcomeID;
    private Appointment appointment;
    private MedicalRecord medicalRecord;
    private String consultationNotes;
    private PrescriptionStatus status;

    /**
     * Constructs an AppointmentOutcome object with the specified details.
     *
     * @param appointmentOutcomeID the unique ID of the appointment outcome
     * @param appointment the appointment associated with this outcome
     * @param medicalRecord the medical record associated with this outcome
     * @param consultationNotes the notes from the consultation
     * @param status the status of the prescription
     */
    public AppointmentOutcome(String appointmentOutcomeID, Appointment appointment, MedicalRecord medicalRecord, String consultationNotes, PrescriptionStatus status) {
        this.appointmentOutcomeID = appointmentOutcomeID;
    	this.appointment = appointment;
        this.medicalRecord = medicalRecord;
        this.consultationNotes = consultationNotes;
        this.status = status;
    }

    // Getter and Setter for appointmentOutcomeID
    /**
     * Returns the unique ID of the appointment outcome.
     *
     * @return the appointment outcome ID
     */
    public String getAppointmentOutcomeID() {
        return appointmentOutcomeID;
    }

    /**
     * Sets the unique ID for the appointment outcome.
     *
     * @param appointmentOutcomeID the new appointment outcome ID
     */
    public void setAppointmentOutcomeID(String appointmentOutcomeID) {
        this.appointmentOutcomeID = appointmentOutcomeID;
    }

    // Getter and Setter for appointment
    /**
     * Returns the appointment associated with this outcome.
     *
     * @return the appointment object
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Sets the appointment for this outcome.
     *
     * @param appointment the new appointment object
     */
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }


    // Getter and Setter for medicalRecord
    /**
     * Returns the medical record associated with this outcome.
     *
     * @return the medical record object
     */
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    /**
     * Sets the medical record for this outcome.
     *
     * @param medicalRecord the new medical record object
     */
    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    // Getter and Setter for consultationNotes
    /**
     * Returns the consultation notes for this appointment outcome.
     *
     * @return the consultation notes
     */
    public String getConsultationNotes() {
        return consultationNotes;
    }

    /**
     * Sets the consultation notes for this outcome.
     *
     * @param consultationNotes the new consultation notes
     */
    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    // Getter and Setter for status
    /**
     * Returns the prescription status for this appointment outcome.
     *
     * @return the prescription status
     */
    public PrescriptionStatus getStatus() {
        return status;
    }

    /**
     * Sets the prescription status for this appointment outcome.
    *
    * @param status the new prescription status
    */
    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }
    
    /**
     * Prints the details of the appointment outcome, including patient name, medical record details,
     * consultation notes, and prescription status.
     */
    public void printDetails() {
        System.out.println("Appointment Outcome Details:");
        System.out.println("Patient Name: " + (appointment != null && appointment.getPatient() != null ? appointment.getPatient().getName() : "N/A"));
        medicalRecord.printPrescriptionDetails();
        System.out.println("Consultation Notes: " + consultationNotes);
        System.out.println("Prescription Status: " + (status != null ? status : "N/A"));
        System.out.println("--------------------------------");
    }
   

    // Method to print appointment details, with status check for "completed"
    /**
     * Prints detailed appointment outcome information if the appointment status is "completed".
     */
    public void printAppointmentDetails() {
        if (appointment != null && appointment.getStatus().equalsIgnoreCase("completed")) {
            System.out.println("Appointment Outcome Details:");
            System.out.println("Appointment ID: " + appointment.getAppointmentID());
            System.out.println("Patient ID: " + appointment.getPatient().getHospitalId());
            System.out.println("Patient Name: " + appointment.getPatient().getName());
            System.out.println("Appointment Status: " + appointment.getStatus());
            System.out.println("Appointment Slot:");
            System.out.println("   Doctor ID: " + appointment.getAppointmentSlot().getDoctor().getHospitalId());
            System.out.println("   Doctor Name: " + appointment.getAppointmentSlot().getDoctor().getDoctorName());
            System.out.println("   Start Time: " + appointment.getAppointmentSlot().getStartTime());
            System.out.println("   End Time: " + appointment.getAppointmentSlot().getEndTime());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(appointment.getAppointmentSlot().getDate());
            System.out.println("   Date: " + formattedDate);
            System.out.println("   Is Booked: " + appointment.getAppointmentSlot().isBooked());

            // Printing outcome details
            medicalRecord.printPrescriptionDetails();
            System.out.println("Consultation Notes: " + consultationNotes);
            System.out.println("Prescription Status: " + (status != null ? status : "N/A"));
            System.out.println("--------------------------------");
        } else {
            System.out.println("Appointment is not completed, no details to display.");
        }
    }
    
    /**
     * Views and prints grouped appointment outcome records based on prescription status for a specific patient.
     *
     * @param slots the list of AppointmentOutcome records
     * @param patientID the ID of the patient whose records should be retrieved
     */
    public static void viewAppointmentOutcomeRecords(List<AppointmentOutcome> slots, String patientID) {
    	String normalizedPatientID = patientID.trim().toLowerCase();
        Map<PrescriptionStatus, List<AppointmentOutcome>> groupedOutcomes = new HashMap<>();

        // Filter and group AppointmentOutcome records by status
        for (AppointmentOutcome slot : slots) {
            if (slot.getAppointment().getPatient() != null) {
                String slotPatientID = slot.getAppointment().getPatient().getHospitalId().trim().toLowerCase();
                if (slotPatientID.equals(normalizedPatientID)) {
                    // Group by PrescriptionStatus
                    groupedOutcomes
                        .computeIfAbsent(slot.getStatus(), k -> new ArrayList<>())
                        .add(slot);
                }
            } else {
                System.out.println("Appointment with ID: " + slot.getAppointment().getAppointmentID() + " has no linked Patient.");
            }
        }

        // Print grouped AppointmentOutcomes by PrescriptionStatus
        for (Map.Entry<PrescriptionStatus, List<AppointmentOutcome>> entry : groupedOutcomes.entrySet()) {
            System.out.println("\nStatus: " + entry.getKey());
            for (AppointmentOutcome outcome : entry.getValue()) {
                System.out.println(outcome);
            }
        }
    }
    
    /**
     * Filters and returns a list of appointment outcomes that are pending.
     *
     * @param allOutcomes the list of all AppointmentOutcome records
     * @return a list of AppointmentOutcome records that have a pending status
     */
    public static List<AppointmentOutcome> filterByPendingStatus(List<AppointmentOutcome> allOutcomes) {
        List<AppointmentOutcome>  pendingOutcomes = new ArrayList<>();
        for (AppointmentOutcome outcome : allOutcomes) {
            if (outcome.getStatus() == PrescriptionStatus.Pending) {
                pendingOutcomes.add(outcome);
            }
        }
        return pendingOutcomes;
    }
    
    /**
     * Provides a string representation of the AppointmentOutcome object.
     *
     * @return a string containing the AppointmentOutcome's ID, appointment details, consultation notes, and prescription status
     */
    @Override
    public String toString() {
        // Retrieve the AppointmentID and PatientID if they exist
        String appointmentID = (appointment != null) ? appointment.getAppointmentID() : "N/A";
        String patientID = (appointment != null && appointment.getPatient() != null) ? 
                            appointment.getPatient().getHospitalId() : "N/A";

        // Retrieve the MedicalRecordID if it exists
        String medicalRecordID = (medicalRecord != null) ? medicalRecord.getRecordID() : "N/A";

        return "AppointmentOutcomeID: " + appointmentOutcomeID +
               "\nAppointmentID: " + appointmentID +
               "\nMedicalRecordID: " + medicalRecordID +
               "\nConsultationNotes: " + consultationNotes +
               "\nPrescriptionStatus: " + status;
    }
    
  
}
