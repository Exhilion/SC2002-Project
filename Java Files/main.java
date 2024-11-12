package OOPProject;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {
	private static loadCSVClass load = new loadCSVClass();

	private static void displayPatientMenu(String username) {
		int choice;
		Scanner scanner = new Scanner(System.in);
		do {

			System.out.println("\nPatient Menu:");
			System.out.println("(1) View Medical Record");
			System.out.println("(2) Update Personal Info");
			System.out.println("(3) View Available Appointment");
			System.out.println("(4) Schedule an Appointment");
			System.out.println("(5) Reschedule Appointment");
			System.out.println("(6) Cancel Appointment");
			System.out.println("(7) View Scheduled Appointments");
			System.out.println("(8) View Appointment Outcome Records");
			System.out.println("(9) Logout");
			System.out.print("Enter your choice: ");

			Patient patient = null;
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				// View Medical Record

				List<MedicalRecord> filteredRecords = MedicalRecord.filterByHospitalId(load.getMedicalRecords(),
						username);

				System.out.println("\nAvailable Medical Records:");
				if (filteredRecords.isEmpty()) {
					System.out.println("No medical records found for the given hospital ID.");
				} else {
					for (MedicalRecord record : filteredRecords) {
						System.out.println("Medical Record ID: " + record.getRecordID());
						System.out.println("Patient Name: " + record.getPatient().getName());
						System.out.println("Diagnosis: " + record.getDiagnosis().getdiagnosis());
						System.out.println("Treatment: " + record.getTreatment().gettreatment());
						System.out.println("Prescriptions: ");
						record.getPrescriptions().forEach(prescription -> System.out
								.println("  - " + prescription.getMedication().getMedicineName()));
						System.out.println("----------------------------------------------------");
					}
				}

				break;

			case 2:
				// Update Patient Personal Info
				patient.updatePatientRecords(username);
				break;

			case 3:
				// View Available Appointment
				break;

			case 4:
				// Schedule Appointment
				// patient.scheduleAppointment(patient, null);
				break;

			case 5:
				// Reschedule Appointment
				// patient.rescheduleAppointment(patient, null, null);
				break;

			case 6:
				// Cancel Appointment
				// patient.cancelAppointment(patient, null);
				break;

			case 7:
				// View Scheduled appointments
				// patient.viewAppointmentOutcomes(patient);
				break;
			case 8:
				// View Appointment outcome records

				break;

			case 9:
				// Quit the program
				System.out.println("Logout");
				displayLoginMenu();
				break;

			default:
				System.out.println("Invalid choice, please try again.");
				break;

			}
		} while (choice != 9);

		scanner.close();

	}

	private static void displayDoctorMenu(String doctorID) {
		System.out.println("\nDoctor Menu:");
		System.out.println("(1) View Patient Medical Records");
		System.out.println("(2) Update Patient Medical Records");
		System.out.println("(3) View Personal Schedule");
		System.out.println("(4) Set Availability for appointments");
		System.out.println("(5) Accept or Decline Appointment Requests");
		System.out.println("(6) View Upcoming Appointments");
		System.out.println("(7) Record Appointment Outcome");
		System.out.println("(8) Logout");

		// Doctor doctor = new Doctor();
		Scanner scanner = new Scanner(System.in);
		int choice;
		Patient patient = null;
		String patientID = null;
		do {
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				// View Medical Record

				// Not right fix later
			

				break;

			case 2:
				// Update Patient Personal Info
				// Retrieve the details
				break;

			case 3:
				// View Available Appointment

				List<AppointmentSlot> DoctorSchedule = AppointmentSlot.filterByDoctorID(load.getAppointmentSlots(),
						doctorID);

				System.out.println("\nSchedule:");
				if (DoctorSchedule.isEmpty()) {
					System.out.println("No schedule found");
				} else {
					for (AppointmentSlot schedule : DoctorSchedule) {
						System.out.println(schedule.toString());
					}
				}
				break;

			case 4:
				// Schedule Appointment
				// patient.scheduleAppointment(patient, null);
				break;

			case 5:
				// Reschedule Appointment
				// patient.rescheduleAppointment(patient, null, null);
				break;

			case 6:
				// Cancel Appointment
				// patient.cancelAppointment(patient, null);
				break;

			case 7:
				// View Appointment Outcome
				// patient.viewAppointmentOutcomes(patient);
				break;
			case 8:
				// Quit the program
				System.out.println("Logout");
				displayLoginMenu();
				break;

			default:
				System.out.println("Invalid choice, please try again.");
				break;
			}

		} while (choice != 8);

		scanner.close();
	}

	private static void displayAdminMenu(String adminID) {
		System.out.println("\nAdmin Menu:");
		System.out.println("(1) View Hospital Staff");
		System.out.println("(2) Add Hospital Staff");
		System.out.println("(3) Update Hospital Staff");
		System.out.println("(4) Remove Hospital Staff");
		System.out.println("(5) View Appointments details");
		System.out.println("(6) View and Manage Medication Inventory");
		System.out.println("(7) Approve Replenishment Requests");
		System.out.println("(8) Logout");

		Administrator admin = new Administrator();
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				// View Hospital Staff

				break;
			case 2:
				// Add Hospital Staff
				StaffCSV newstaff = new StaffCSV();
				System.out.println("Select Role (1-DOCTOR, 2-PHARMACIST, 3-ADMINISTRATOR):");
				int roleChoice = scanner.nextInt();
				scanner.nextLine();
				Role role = null;
				switch (roleChoice) {
				case 1 -> role = Role.DOCTOR;
				case 2 -> role = Role.PHARMACIST;
				case 3 -> role = Role.ADMINISTRATOR;
				default -> {
					System.out.println("Invalid role selected. Exiting...");
					scanner.close();
					return;
				}
				}

				// Gender selection
				System.out.println("Select Gender (1-MALE, 2-FEMALE, 3-OTHER): ");
				int genderChoice = scanner.nextInt();
				scanner.nextLine();
				Gender gender = null;
				switch (genderChoice) {
				case 1 -> gender = Gender.MALE;
				case 2 -> gender = Gender.FEMALE;
				default -> {
					System.out.println("Invalid gender selected. Exiting...");
					scanner.close();
					return;
				}
				}

				// Common details for all roles
				System.out.print("Enter Name: ");
				String name = scanner.nextLine();

				// Generate hospital ID and password
				String hospitalID = newstaff.generateStaffId(role);
				String password = "password";
				Boolean firstTimeLogin = true;

				// Additional details based on role
				switch (role) {
				case DOCTOR -> {
					System.out.print("Enter Department: ");
					String department = scanner.nextLine();
					System.out.print("Enter Specialisation: ");
					String specialisation = scanner.nextLine();
					newstaff.addDoctor(hospitalID, password, role, gender, name, department, specialisation,
							firstTimeLogin);
				}
				case PHARMACIST -> newstaff.addPharmacist(hospitalID, password, role, gender, name, firstTimeLogin);
				case ADMINISTRATOR -> newstaff.addAdmin(hospitalID, password, role, gender, name, firstTimeLogin);
				}

				break;
			case 3:
				// Update Hospital Staff

				break;
			case 4:
				// Remove Hospital Staff

				break;

			case 5:
				// View Appointments details
				break;

			case 6:
				// View and Manage Medication Inventory
				break;

			case 7:
				// Approve Replenishment Requests

				break;
			case 8:
				// Quit the program
				System.out.println("Logout");
				displayLoginMenu();
				break;

			default:
				System.out.println("Invalid choice, please try again.");
				break;
			}

		} while (choice != 5);

		scanner.close();
	}

	private static void displayPharmacistMenu(String adminID) {
		System.out.println("\nPharmacist Menu:");
		System.out.println("(1) View Appointment Outcome Record");
		System.out.println("(2) Update Prescription Status");
		System.out.println("(3) View Medication Inventory");
		System.out.println("(4) Submit Replenishment Request");
		System.out.println("(5) Logout");

		Pharmacist admin = new Pharmacist();
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				// View appointment Outcome Record

				break;

			case 2:
				// Update Prescription Status
				break;

			case 3:
				// View Medication Inventory
				break;

			case 4:
				// Submit Replenishment Request

				break;
			case 5:
				// Quit the program
				System.out.println("Logout");
				displayLoginMenu();
				break;

			default:
				System.out.println("Invalid choice, please try again.");
				break;
			}

		} while (choice != 5);

		scanner.close();
	}

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
		if (result == "DR") {
			displayDoctorMenu(username);
		} else if (result == "PH") {
			// displayPHMenu
			displayPharmacistMenu(username);
		} else if (result == "AD") {
			// displayADMenu
			displayAdminMenu(username);
		} else if (result == "PT") {
			// displayPTMenu
			displayPatientMenu(username);
		} else {
			System.out.println("Invalid Login");
			displayLoginMenu();
		}
	}

	public static void main(String[] args) {
		displayLoginMenu();

	}
}
