package OOPProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * The main class serves as the entry point for the Hospital Management System.
 * It facilitates user login and role-specific navigation to various menus based on the user's credentials.
 */

public class main {

	/**
     * Displays the login menu and facilitates user authentication.
     * Based on the authenticated user's role, the appropriate menu is displayed.
     * 
     * Roles:
     * <ul>
     *   <li>Doctor (DR): Accesses the {@link DoctorMenu} to manage appointments and medical records.</li>
     *   <li>Patient (PT): Accesses the {@link PatientMenu} to view and manage appointments.</li>
     *   <li>Pharmacist (PH): Accesses the {@link PharmacistMenu} to manage prescriptions and inventory.</li>
     *   <li>Administrator (AD): Accesses the {@link AdministratorMenu} to manage hospital staff and system settings.</li>
     * </ul>
     * If the login is invalid, the user is prompted to retry.
     */
	static void displayLoginMenu() {
		System.out.println("Login");
		System.out.println("Enter your username ");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		System.out.println("Enter your password");
		String password = scanner.nextLine();
		UserCSV tempuser = new UserCSV();

		// Verify username and password
		String result = tempuser.login(username, password);
		loadCSVClass load = new loadCSVClass();
		AppointmentService appointmentService = new AppointmentServiceImpl();
		MedicalRecordService medicalRecordService = new MedicalRecordServiceImpl();
		if (result == "DR") {
			// Doctor Role
			DoctorService doctorService = new DoctorService(medicalRecordService, appointmentService, load);
			DoctorMenu doctorMenu = new DoctorMenu(doctorService);
			doctorMenu.displayMenu(username);
		} else if (result == "PT") {
			// Patient Role
			PatientService patientService = new PatientService(appointmentService, load);
			PatientMenu patientMenu = new PatientMenu(patientService);
			patientMenu.displayPatientMenu(username);
		} else if (result == "PH") {
			// Pharmacist Role
			PharmacistService pharmacistService = new PharmacistService(load);
			PharmacistMenu pharmacistMenu = new PharmacistMenu(pharmacistService);
			pharmacistMenu.displayMenu(username);
		} else if (result == "AD") {
			// Administrator Role
			AdministratorService adminService = new AdministratorService();
			AdministratorMenu adminMenu = new AdministratorMenu(adminService);
			adminMenu.displayAdminMenu(username);
		} else {
			// Invalid Login
			System.out.println("Invalid Login");
			displayLoginMenu();
		}
	}

	/**
     * The main method serves as the program's entry point.
     * It invokes the {@link #displayLoginMenu()} method to start the login and navigation process.
     * 
     * @param args Command-line arguments (not used).
     */

	public static void main(String[] args) {
		displayLoginMenu();

	}

}
