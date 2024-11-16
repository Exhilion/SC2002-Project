package OOPProject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

/**
 * The StaffCSV class implements the AdminStaffManagement interface and manages staff-related operations. 
 * It provides methods to add different staff roles (Administrator, Doctor, Pharmacist) and writes their 
 * information to respective CSV files based on their role.
 */
public class StaffCSV implements AdminStaffManagement{
	private Map<Role, String> roleFilePathMap;

	/**
     * Constructor initializes the role-to-file mapping for various staff roles. 
     * This ensures that each staff type's data is written to the correct CSV file.
     */
	public StaffCSV() {
		roleFilePathMap = new HashMap<>();
		roleFilePathMap.put(Role.ADMINISTRATOR, AppConfig.ADMIN_FILE_PATH);
		roleFilePathMap.put(Role.DOCTOR, AppConfig.DOCTOR_FILE_PATH);
		roleFilePathMap.put(Role.PHARMACIST, AppConfig.PHARMACIST_FILE_PATH);
	}

	// Randomly generated ID based on role
	/**
     * Generates a unique staff ID based on the staff's role. The ID is prefixed with the role abbreviation.
     *
     * @param role The role of the staff (e.g., DOCTOR, PHARMACIST, ADMINISTRATOR).
     * @return A randomly generated staff ID with a prefix based on the role.
     */
	public String generateStaffId(Role role) {
		String prefix = switch (role) {
		case DOCTOR -> "DR";
		case PHARMACIST -> "PH";
		case ADMINISTRATOR -> "AD";
		default -> throw new IllegalArgumentException("Unexpected value: " + role);
		};
		return prefix + UUID.randomUUID().toString().substring(0, 8);
	}

	/**
     * Adds a new Administrator staff member and writes their information to the corresponding CSV file.
     * 
     * @param hospitalId The hospital ID of the administrator.
     * @param password The administrator's password.
     * @param role The role of the staff, which is ADMINISTRATOR.
     * @param gender The gender of the administrator.
     * @param administratorName The name of the administrator.
     * @param firstTimeLogin Flag indicating if it's the administrator's first login.
     */
	@Override
	public void addAdmin(String hospitalId, String password, Role role, Gender gender, String administratorName, Boolean firstTimeLogin ) {
		Administrator newAdmin = new Administrator(hospitalId, password, role, gender, administratorName , firstTimeLogin);
		writeToCSV(newAdmin);
	}

	/**
     * Adds a new Doctor staff member and writes their information to the corresponding CSV file.
     * 
     * @param hospitalId The hospital ID of the doctor.
     * @param password The doctor's password.
     * @param role The role of the staff, which is DOCTOR.
     * @param gender The gender of the doctor.
     * @param doctorName The name of the doctor.
     * @param department The department the doctor works in.
     * @param specialisation The specialisation of the doctor.
     * @param firstTimeLogin Flag indicating if it's the doctor's first login.
     */
	@Override
	public void addDoctor(String hospitalId, String password, Role role, Gender gender, String doctorName,
			String department, String specialisation, Boolean firstTimeLogin) {
		Doctor newDoctor = new Doctor(hospitalId, password, role, gender, doctorName, department, specialisation, firstTimeLogin);
		writeToCSV(newDoctor);
	}

	/**
     * Adds a new Pharmacist staff member and writes their information to the corresponding CSV file.
     * 
     * @param hospitalId The hospital ID of the pharmacist.
     * @param password The pharmacist's password.
     * @param role The role of the staff, which is PHARMACIST.
     * @param gender The gender of the pharmacist.
     * @param pharmacistName The name of the pharmacist.
     * @param firstTimeLogin Flag indicating if it's the pharmacist's first login.
     */
	@Override
	public void addPharmacist(String hospitalId, String password, Role role, Gender gender, String pharmacistName, Boolean firstTimeLogin) {
		Pharmacist newPharmacist = new Pharmacist(hospitalId, password, role, gender, pharmacistName, firstTimeLogin);
		writeToCSV(newPharmacist);
	}

	// Method to write any staff member to CSV
	/**
     * Writes the details of any staff member to the appropriate CSV file based on their role. 
     * The method supports different types of staff (Administrator, Doctor, Pharmacist) 
     * and writes their details in the correct CSV format for each role.
     *
     * @param staffMember The staff member object (Administrator, Doctor, or Pharmacist).
     */
	@Override
	public void writeToCSV(Object staffMember) {
		String csvLine;
		String filePath = "";

		if (staffMember instanceof Administrator admin) {
			csvLine = admin.getHospitalId() + "," + admin.getPassword() + "," + admin.getRole() + ","
					+ admin.getGender() + "," + admin.getAdminName() + "," + admin.getFirstTimeLogin() +"\n";
			filePath = roleFilePathMap.get(admin.getRole());
		} else if (staffMember instanceof Doctor doctor) {
			csvLine = doctor.getHospitalId() + "," + doctor.getPassword() + "," + doctor.getRole() + ","
					+ doctor.getGender() + "," + doctor.getDoctorName() + "," + doctor.getDepartment() + ","
					+ doctor.getSpecialisation() + "," + doctor.getFirstTimeLogin() +"\n";
			filePath = roleFilePathMap.get(doctor.getRole());
		} else if (staffMember instanceof Pharmacist pharmacist) {
			csvLine = pharmacist.getHospitalId() + "," + pharmacist.getPassword() + "," + pharmacist.getRole() + ","
					+ pharmacist.getGender() + "," + pharmacist.getPharmacistName() + "," + pharmacist.getFirstTimeLogin() +"\n";
			filePath = roleFilePathMap.get(pharmacist.getRole());
		} else {
			throw new IllegalArgumentException("Unsupported staff member type");
		}

		// Write to CSV
		try (FileWriter writer = new FileWriter(filePath, true)) {
			writer.write(csvLine);
			System.out.println("New " + staffMember.getClass().getSimpleName() + " added successfully.");
		} catch (IOException e) {
			System.err.println("Error writing to CSV: " + e.getMessage());
		}
	}

}