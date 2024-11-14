package OOPProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AppointmentCSV {

    // Define the allowed date format
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    // Read appointments from CSV file
    public List<Appointment> loadAppointmentsFromCSV(List<AppointmentSlot> slots, List<Patient> patients) {
        List<Appointment> appointments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.APPOINTMENT_FILE_PATH))) {
            String line;
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) { // Assuming 4 columns in the CSV
                    String appointmentID = values[0];
                    String slotID = values[1];
                    String status = values[2];
                    String patientID = values[3];

                    // Find the matching AppointmentSlot object
                    AppointmentSlot slot = findSlotByID(slotID, slots);
                    if (slot == null) {
                        System.out.println("Appointment Slot ID " + slotID + " not found.");
                        continue;
                    }

                    // Find matching patient
                    Patient patient = findPatientByID(patientID, patients);
                    if (patient == null) {
                        System.out.println("Patient with ID " + patientID + " not found.");
                        continue;
                    }

                    // Create Appointment object and add to the list
                    Appointment appointment = new Appointment(appointmentID, slot, status, patient);
                    appointments.add(appointment);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appointments;
    }
    
    // Update appointment status in the CSV
    public boolean updateAppointmentStatus(String appointmentID, String newStatus) {
        File inputFile = new File(AppConfig.APPOINTMENT_FILE_PATH);
        File tempFile = new File("temp.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 8 && values[0].equals(appointmentID)) {
                    // Update status
                    values[2] = newStatus;
                }
                writer.write(String.join(",", values));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Replace old file with updated content
        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.out.println("Error updating appointment status.");
            return false;
        }

        return true;
    }

    // Helper method to find an AppointmentSlot by slotID
    private AppointmentSlot findSlotByID(String slotID, List<AppointmentSlot> slots) {
        for (AppointmentSlot slot : slots) {
            if (slot.getAppointmentSlotID().equalsIgnoreCase(slotID)) {
                return slot;
            }
        }
        return null;
    }

    // Helper method to find a Patient by ID
    private Patient findPatientByID(String patientID, List<Patient> patients) {
        for (Patient patient : patients) {
            if (patient.getHospitalId().equalsIgnoreCase(patientID)) {
                return patient;
            }
        }
        return null;
    }
}
