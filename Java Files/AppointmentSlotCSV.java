package OOPProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentSlotCSV {
	
    // Define the allowed time format
    private static final String TIME_FORMAT = "H:mm"; // Format for 9:00, 10:00, etc.
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
    
    // Ensures that invalid times are rejected
    static {
        timeFormat.setLenient(false); 
    }


    // Read file and load appointment slots
    public List<AppointmentSlot> loadAppointmentSlotsFromCSV(List<Doctor> doctors) {
        List<AppointmentSlot> appointmentSlots = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.APPOINTMENT_SLOT_FILE_PATH))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) { // Assuming 6 columns in the CSV
                    String appointmentSlotID = values[0];
                    String doctorID = values[1];
                    Doctor doctor = findDoctorByID(doctorID, doctors);

                    if (doctor == null) {
                        System.out.println("Doctor with Name " + doctorID + " not found.");
                        continue;
                    }

                    String startTime = values[2];
                    String endTime = values[3];
                    String dateStr = values[4];
                    boolean isBooked = Boolean.parseBoolean(values[5]);

                    // Convert date string to Date object
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = null;
                    try {
                        date = dateFormat.parse(dateStr);
                    } catch (Exception e) {
                        System.out.println("Invalid date format: " + dateStr);
                        continue;
                    }

                    // Create the AppointmentSlot object and add it to the list
                    AppointmentSlot appointmentSlot = new AppointmentSlot(appointmentSlotID, doctor, startTime, endTime, date, isBooked);
                    appointmentSlots.add(appointmentSlot);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appointmentSlots;
    }
    
    // Allow doctor to add a new appointment slot with time format validation
    public boolean addAppointmentSlotToCSV(AppointmentSlot slot, String doctorID) {
        if (!isValidTimeFormat(slot.getStartTime()) || !isValidTimeFormat(slot.getEndTime())) {
            System.out.println("Invalid time format. Please use 'H:mm' format (e.g., 9:00, 13:30).");
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AppConfig.APPOINTMENT_SLOT_FILE_PATH, true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateStr = dateFormat.format(slot.getDate());

            String newLine = String.format("%s,%s,%s,%s,%s,%s",
                slot.getAppointmentSlotID(),
                doctorID,
                slot.getStartTime(),
                slot.getEndTime(),
                dateStr,
                slot.isBooked());

            bw.write(newLine);
            bw.newLine();
            return true; // Successfully added
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Failed to add
        }
    }
    
    //Update Booking Status
    public boolean updateAppointmentSlotBookingStatus(String appointmentSlotID, boolean newStatus) {
        File inputFile = new File(AppConfig.APPOINTMENT_SLOT_FILE_PATH);
        List<String> lines = new ArrayList<>();
        boolean isUpdated = false;  // Flag to track if an update happened

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(appointmentSlotID)) {
                    // Update booking status
                    System.out.println("Updating appointment slot with ID: " + appointmentSlotID);  // Debugging line
                    values[5] = String.valueOf(newStatus);  // Assuming isBooked is at index 4 in the CSV
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
            System.out.println("Appointment Slot " + appointmentSlotID + " not found or no change in booking status.");
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


    // Validate the format of the time string
    public boolean isValidTimeFormat(String time) {
        try {
            timeFormat.parse(time);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
 

    // Helper method to find a Doctor by name
    private Doctor findDoctorByID(String doctorID, List<Doctor> doctors) {
        for (Doctor doctor : doctors) {
            if (doctor.getHospitalId().equalsIgnoreCase(doctorID)) {
                return doctor;
            }
        }
        return null;
    }
}
