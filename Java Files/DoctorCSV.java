package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoctorCSV {

    // Read file and load doctors
    public List<Doctor> loadDoctorsFromCSV() {
        List<Doctor> doctors = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.DOCTOR_FILE_PATH))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 8) { // Assuming 8 columns in the CSV
                    String hospitalId = values[0];
                    String password = values[1];
                    Role role = Role.valueOf(values[2].toUpperCase()); // Convert string to Role enum
                    Gender gender = Gender.valueOf(values[3].toUpperCase()); // Convert string to Gender enum
                    String doctorName = values[4];
                    String department = values[5];
                    String specialisation = values[6];
                    boolean firstTimeLogin = Boolean.parseBoolean(values[7]);

                    // Create the Doctor object and add it to the list
                    Doctor doctor = new Doctor(hospitalId, password, role, gender, doctorName, department, specialisation, firstTimeLogin);
                    doctors.add(doctor);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doctors;
    }
}