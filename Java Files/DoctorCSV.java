package OOPProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The DoctorCSV class provides functionality for reading a CSV file that
 * contains information about doctors. It loads doctor data and creates a list
 * of Doctor objects.
 */
public class DoctorCSV {
	/**
	 * Reads the CSV file containing doctor details and loads the data into a list
	 * of Doctor objects.
	 * 
	 * @return A list of Doctor objects loaded from the CSV file.
	 */
	public List<Doctor> loadDoctorsFromCSV() {
		List<Doctor> doctors = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.DOCTOR_FILE_PATH))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length == 8) {
					String hospitalId = values[0];
					String password = values[1];
					Role role = Role.valueOf(values[2].toUpperCase());
					Gender gender = Gender.valueOf(values[3].toUpperCase());
					String doctorName = values[4];
					String department = values[5];
					String specialisation = values[6];
					boolean firstTimeLogin = Boolean.parseBoolean(values[7]);

					Doctor doctor = new Doctor(hospitalId, password, role, gender, doctorName, department,
							specialisation, firstTimeLogin);
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
