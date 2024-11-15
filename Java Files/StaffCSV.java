package OOPProject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class StaffCSV implements AdminStaffManagement{
	private Map<Role, String> roleFilePathMap;

	public StaffCSV() {
		roleFilePathMap = new HashMap<>();
		roleFilePathMap.put(Role.ADMINISTRATOR, AppConfig.ADMIN_FILE_PATH);
		roleFilePathMap.put(Role.DOCTOR, AppConfig.DOCTOR_FILE_PATH);
		roleFilePathMap.put(Role.PHARMACIST, AppConfig.PHARMACIST_FILE_PATH);
	}

	// Randomly generated ID based on role
	public String generateStaffId(Role role) {
		String prefix = switch (role) {
		case DOCTOR -> "DR";
		case PHARMACIST -> "PH";
		case ADMINISTRATOR -> "AD";
		default -> throw new IllegalArgumentException("Unexpected value: " + role);
		};
		return prefix + UUID.randomUUID().toString().substring(0, 8);
	}


	@Override
	public void addAdmin(String hospitalId, String password, Role role, Gender gender, String administratorName, Boolean firstTimeLogin ) {
		Administrator newAdmin = new Administrator(hospitalId, password, role, gender, administratorName , firstTimeLogin);
		writeToCSV(newAdmin);
	}

	@Override
	public void addDoctor(String hospitalId, String password, Role role, Gender gender, String doctorName,
			String department, String specialisation, Boolean firstTimeLogin) {
		Doctor newDoctor = new Doctor(hospitalId, password, role, gender, doctorName, department, specialisation, firstTimeLogin);
		writeToCSV(newDoctor);
	}

	@Override
	public void addPharmacist(String hospitalId, String password, Role role, Gender gender, String pharmacistName, Boolean firstTimeLogin) {
		Pharmacist newPharmacist = new Pharmacist(hospitalId, password, role, gender, pharmacistName, firstTimeLogin);
		writeToCSV(newPharmacist);
	}

	// Method to write any staff member to CSV
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