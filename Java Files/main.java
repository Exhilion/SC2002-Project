package OOPProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class main {
	private static void displayLoginMenu() {
		System.out.println("Login");
		System.out.println("Enter your username ");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		System.out.println("Enter your password");
		String password = scanner.nextLine();
		UserCSV tempuser = new UserCSV();

		// Verify username and password
		String result = tempuser.login(username, password);
		AppointmentService appointmentService = new AppointmentServiceImpl();
		AppointmentOutcomeService appointmentOutcomeService = new AppointmentOutcomeServiceImpl();
		if (result == "DR") {
			DoctorService doctorService = new DoctorService(appointmentService, appointmentOutcomeService);
			DoctorMenu doctorMenu = new DoctorMenu(doctorService);
			doctorMenu.displayMenu(username);
		} else if (result == "PT") {
			PatientService patientService = new PatientService();
			PatientMenu patientMenu = new PatientMenu(patientService);
			patientMenu.displayPatientMenu(username);
		} else if (result == "PH") {
			PharmacistService pharmacistService = new PharmacistService();
			PharmacistMenu pharmacistMenu = new PharmacistMenu(pharmacistService);
			pharmacistMenu.displayMenu(username);
		} else if (result == "AD") {
			AdministratorService adminService = new AdministratorService();
			AdministratorMenu adminMenu = new AdministratorMenu(adminService);
			adminMenu.displayAdminMenu(username);
		} else {
			System.out.println("Invalid Login");
			displayLoginMenu();
		}
	}

	public static void main(String[] args) {
		displayLoginMenu();

	}

}
