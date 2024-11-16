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

/**
 * The AppointmentCSV class handles the loading, updating, and manipulation of appointment data stored in a CSV file.
 * <p>
 * It provides methods to load appointments from a CSV file, update appointment statuses, and find specific appointment slots and patients
 * based on their IDs. This class interacts with AppointmentSlot and Patient objects to associate appointments with the correct slot and patient.
 * </p>
 */
public class AppointmentCSV {

    // Define the allowed date format
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    // Read appointments from CSV file
    /**
     * Loads appointments from a CSV file and associates them with available appointment slots and patients.
     *
     * @param slots the list of available appointment slots
     * @param patients the list of registered patients
     * @return a list of appointments loaded from the CSV file
     */
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
    /**
     * Updates the status of an appointment in the CSV file.
     *
     * @param appointmentID the ID of the appointment to update
     * @param newStatus the new status for the appointment
     * @return true if the status was successfully updated, false otherwise
     */
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
    /**
     * Finds an AppointmentSlot by its slot ID.
     *
     * @param slotID the slot ID to search for
     * @param slots the list of available appointment slots
     * @return the AppointmentSlot object with the matching ID, or null if not found
     */
    private AppointmentSlot findSlotByID(String slotID, List<AppointmentSlot> slots) {
        for (AppointmentSlot slot : slots) {
            if (slot.getAppointmentSlotID().equalsIgnoreCase(slotID)) {
                return slot;
            }
        }
        return null;
    }

    // Helper method to find a Patient by ID
    /**
     * Finds a Patient by their hospital ID.
     *
     * @param patientID the hospital ID to search for
     * @param patients the list of registered patients
     * @return the Patient object with the matching hospital ID, or null if not found
     */
    private Patient findPatientByID(String patientID, List<Patient> patients) {
        for (Patient patient : patients) {
            if (patient.getHospitalId().equalsIgnoreCase(patientID)) {
                return patient;
            }
        }
        return null;
    }
}
