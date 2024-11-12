package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentSlotCSV {

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
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
