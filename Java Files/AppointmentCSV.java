package OOPProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    
    public static void scheduleAppointment( List<AppointmentSlot> slots, String patientID, String doctorID, String dateOfChoice, String startTime, String endTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        boolean appointmentScheduled = false;
        String appointmentSlotID = null;

        try {
            Date targetDate = dateFormat.parse(dateOfChoice);

            // Find the matching slot and mark it as booked
            for (AppointmentSlot slot : slots) {
                if (slot.getDoctor() != null 
                    && slot.getDoctor().getHospitalId().equals(doctorID) 
                    && dateFormat.format(slot.getDate()).equals(dateFormat.format(targetDate))
                    && !slot.isBooked()
                    && slot.getStartTime().equals(startTime)
                    && slot.getEndTime().equals(endTime)) {

                    appointmentSlotID = slot.getAppointmentSlotID();  // Save the appointmentSlotID
                    System.out.println("Appointment has been scheduled!");
                    appointmentScheduled = true;
                    break;
                }
            }

            if (!appointmentScheduled) {
                System.out.println("Appointment has not been scheduled. No available slots found.");
                return;
            }

            boolean updateSuccess = AppointmentSlotCSV.updateAppointmentSlotBookingStatus(appointmentSlotID, true);

            if (updateSuccess) {
                System.out.println("Booking status updated successfully in AppointmentSlot.csv.");

                // Generate a unique AppointmentID and create a new entry in Appointment.csv
                String appointmentID = "A" + UUID.randomUUID().toString();
                String status = "Pending";

                // Append the new appointment entry to Appointment.csv
                try (PrintWriter writer = new PrintWriter(new FileWriter(AppConfig.APPOINTMENT_FILE_PATH, true))) {  // Append mode
                    writer.printf("%s,%s,%s,%s%n", appointmentID, appointmentSlotID, status, patientID);
                    System.out.println("New appointment entry added to Appointment.csv.");
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to Appointment.csv.");
                    e.printStackTrace();
                }

            } else {
                System.out.println("Failed to update booking status in the AppointmentSlot.csv.");
            }

        } catch (ParseException e) {
            System.out.println("Invalid date format: " + dateOfChoice);
            e.printStackTrace();
        }
    }
    
    public static void cancelAppointment(String patientID, String appointmentID) {
        String appointmentSlotID = cancelAppointmentInCSV(patientID, appointmentID);
        if (appointmentSlotID != null) {
            AppointmentSlotCSV.updateAppointmentSlotBookingStatus(appointmentSlotID, false);
        }
    }

    // Method to cancel appointment in Appointment.csv and return the AppointmentSlotID if found
    private static String cancelAppointmentInCSV(String patientID, String appointmentID) {
        List<String> appointmentLines = new ArrayList<>();
        boolean appointmentFound = false;
        String appointmentSlotID = null;

        // Read and update the Appointment.csv
        try (BufferedReader reader = new BufferedReader(new FileReader(AppConfig.APPOINTMENT_FILE_PATH))) {
            String line;
            appointmentLines.add(reader.readLine()); 
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println(values[0]);
                if (values[0].equalsIgnoreCase(appointmentID) && values[3].equalsIgnoreCase(patientID)) { 
                    System.out.println("Cancelling appointment with ID: " + appointmentID);
                    values[2] = "Cancelled"; // Update status to "Cancelled"
                    appointmentSlotID = values[1]; 
                    appointmentFound = true;
                }
                appointmentLines.add(String.join(",", values));
            }
        } catch (IOException e) {
            System.out.println("Error reading the Appointment.csv file.");
            e.printStackTrace();
            return null;
        }

        if (!appointmentFound) {
            System.out.println("No matching appointment found for Patient ID " + patientID + " with Appointment ID " + appointmentID);
            return null;
        }

        // Write back the updated Appointment.csv
        try (PrintWriter writer = new PrintWriter(new FileWriter(AppConfig.APPOINTMENT_FILE_PATH))) {
            for (String updatedLine : appointmentLines) {
                writer.println(updatedLine);
            }
            System.out.println("Appointment status updated to 'Cancelled' in the Appointment.csv.");
        } catch (IOException e) {
            System.out.println("Error writing to the Appointment.csv file.");
            e.printStackTrace();
        }

        return appointmentSlotID;
    }
}
