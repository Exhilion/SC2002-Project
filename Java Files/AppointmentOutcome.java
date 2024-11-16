package OOPProject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentOutcome {
    private String appointmentOutcomeID;
    private Appointment appointment;
    private MedicalRecord medicalRecord;
    private String consultationNotes;
    private PrescriptionStatus status;

    public AppointmentOutcome(String appointmentOutcomeID, Appointment appointment, MedicalRecord medicalRecord, String consultationNotes, PrescriptionStatus status) {
        this.appointmentOutcomeID = appointmentOutcomeID;
    	this.appointment = appointment;
        this.medicalRecord = medicalRecord;
        this.consultationNotes = consultationNotes;
        this.status = status;
    }

    // Getter and Setter for appointmentOutcomeID
    public String getAppointmentOutcomeID() {
        return appointmentOutcomeID;
    }

    public void setAppointmentOutcomeID(String appointmentOutcomeID) {
        this.appointmentOutcomeID = appointmentOutcomeID;
    }

    // Getter and Setter for appointment
    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }


    // Getter and Setter for medicalRecord
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    // Getter and Setter for consultationNotes
    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    // Getter and Setter for status
    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }
    

    public void printDetails() {
        System.out.println("Appointment Outcome Details:");
        System.out.println("Patient Name: " + (appointment != null && appointment.getPatient() != null ? appointment.getPatient().getName() : "N/A"));
        medicalRecord.printPrescriptionDetails();
        System.out.println("Consultation Notes: " + consultationNotes);
        System.out.println("Prescription Status: " + (status != null ? status : "N/A"));
        System.out.println("--------------------------------");
    }
   

    // Method to print appointment details, with status check for "completed"
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
    
    public static List<AppointmentOutcome> filterByPendingStatus(List<AppointmentOutcome> allOutcomes) {
        List<AppointmentOutcome>  pendingOutcomes = new ArrayList<>();
        for (AppointmentOutcome outcome : allOutcomes) {
            if (outcome.getStatus() == PrescriptionStatus.Pending) {
                pendingOutcomes.add(outcome);
            }
        }
        return pendingOutcomes;
    }
    
    
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
