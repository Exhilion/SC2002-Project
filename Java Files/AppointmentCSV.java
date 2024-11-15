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
        //System.out.println("Attempting to read lines from Appointment.csv...");

        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.APPOINTMENT_FILE_PATH))) {
            String line = br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                //System.out.println("Read line: " + line);  // Debug line

                String[] values = line.split(",");
                if (values.length == 4) {
                    String appointmentID = values[0];
                    String slotID = values[1];
                    String status = values[2];
                    String patientID = values[3];

                    // Find matching AppointmentSlot and Patient
                    AppointmentSlot slot = findSlotByID(slotID, slots);
                    if (slot == null) {
                        System.out.println("Appointment Slot ID " + slotID + " not found.");
                        continue;
                    }

                    Patient patient = findPatientByID(patientID, patients);
                    if (patient == null) {
                        System.out.println("Patient with ID " + patientID + " not found.");
                        continue;
                    }

                    // Create and add the appointment
                    Appointment appointment = new Appointment(appointmentID, slot, status, patient);
                    appointments.add(appointment);
                    System.out.println("Loaded Appointment: " + appointment);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Appointment.csv: " + e.getMessage());
            e.printStackTrace();
        }

        //System.out.println("Total appointments loaded: " + appointments.size());
        return appointments;
    }
    
    // Update appointment status in the CSV
    public boolean updateAppointmentStatus(String appointmentID, String newStatus) {
        File inputFile = new File(AppConfig.APPOINTMENT_FILE_PATH);
        List<String> lines = new ArrayList<>();
        boolean isUpdated = false;  // Flag to track if an update happened

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(appointmentID)) {
                    // Update status
                    System.out.println("Updating appointment with ID: " + appointmentID);  // Debugging line
                    values[2] = newStatus;
                    isUpdated = true;  // Mark that we updated a line
                }
                lines.add(String.join(",", values));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // If no update was performed, return false
        if (!isUpdated) {
            System.out.println("Appointment" +appointmentID+ "ID not found or no change in status.");
            return false;
        }

        // Write all lines back to the original file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
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