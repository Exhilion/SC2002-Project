package OOPProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

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
		System.out.println("(5) Accept/Decline Appointment Requests");
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
				// Set Availability for Appointments
				AppointmentSlotCSV newslot = new AppointmentSlotCSV();
				System.out.println("\nSet Availability for Appointments:");

				System.out.print("Enter Start Time (HH:mm): ");
				String startTime = scanner.nextLine();

				System.out.print("Enter End Time (HH:mm): ");
				String endTime = scanner.nextLine();

				System.out.print("Enter Date (dd/MM/yyyy): ");
				String dateStr = scanner.nextLine();

				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date;
				try {
					date = dateFormat.parse(dateStr); // Parse the input date string into a Date object
				} catch (ParseException e) {
					System.out.println("Invalid date format. Please use 'dd/MM/yyyy'.");
					break; // Exit the case if date is invalid
				}

				String AppointmentSlotID = "AS" + UUID.randomUUID().toString();
				AppointmentSlot newSlot = new AppointmentSlot(AppointmentSlotID, startTime, endTime, date, false);

				// Save the new slot to CSV
				if (newslot.addAppointmentSlotToCSV(newSlot, doctorID)) {
					System.out.println("Appointment slot added successfully!");
				} else {
					System.out.println("Failed to add appointment slot.");
				}
				break;

			case 5:
				// Accept/Decline Appointment Request
				System.out.println("\nAccept/Decline Appointment Requests:");

				List<Appointment> pendingAppointments = new ArrayList<>();
				for (Appointment appointment : load.getAppointments()) {
					if (appointment.getAppointmentSlot().getDoctor().getHospitalId().equals(doctorID)
							&& "pending".equalsIgnoreCase(appointment.getStatus())) {
						pendingAppointments.add(appointment);
					}
				}

				if (pendingAppointments.isEmpty()) {
					System.out.println("No pending appointments found.");
				} else {
					for (Appointment appointment : pendingAppointments) {
						System.out.println("Patient: " + appointment.getPatient().getName());
						System.out.println("Appointment Slot:");
						System.out
								.println("   Doctor: " + appointment.getAppointmentSlot().getDoctor().getDoctorName());
						System.out.println("   Start Time: " + appointment.getAppointmentSlot().getStartTime());
						System.out.println("   End Time: " + appointment.getAppointmentSlot().getEndTime());
						SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
						String formattedDate = dateFormat1.format(appointment.getAppointmentSlot().getDate());
						System.out.println("   Date: " + formattedDate);
						System.out.println("   Is Booked: " + appointment.getAppointmentSlot().isBooked());

						// Ask user if they want to accept or decline the appointment
						System.out
								.println("Do you want to accept or decline this appointment? (1: Accept, 2: Decline)");
						int response = scanner.nextInt();
						scanner.nextLine();

						String newStatus = (response == 1) ? "Confirmed" : "Cancelled";
						if (response == 1) {

							new AppointmentCSV().updateAppointmentStatus(appointment.getAppointmentID(), newStatus);
							new AppointmentSlotCSV().updateAppointmentSlotBookingStatus(
									appointment.getAppointmentSlot().getAppointmentSlotID(), true);
							System.out.println("Appointment " + newStatus + " successfully!");
						}

						else {
							new AppointmentCSV().updateAppointmentStatus(appointment.getAppointmentID(), newStatus);
							System.out.println("Appointment Cancelled");
						}
					}
				}
				break;

			case 6:
				// View Upcoming Appointment

				String status = "confirmed";
				

				List<Appointment> confirmedAppointments = AppointmentFilter.filterAppointmentsByDoctorAndStatus(load.getAppointments(), doctorID,
						status);

				// Output the filtered appointments
				for (Appointment appointment : confirmedAppointments) {
				    AppointmentSlot slot = appointment.getAppointmentSlot();
				    Doctor doctor = slot.getDoctor();
				    Date dates = slot.getDate();
				    
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				    String formattedDate = sdf.format(dates);
				    
				    System.out.println("Doctor: " + (doctor != null ? doctor.getDoctorName() : "N/A"));
				    System.out.println("Start Time: " + slot.getStartTime());
				    System.out.println("End Time: " + slot.getEndTime());
				    System.out.println("Date: " + formattedDate);
				    System.out.println("Status: " + appointment.getStatus());
				    System.out.println("Patient: " + (appointment.getPatient() != null ? appointment.getPatient().getName() : "N/A"));
				    System.out.println("-------------------------------");
				}

				break;
			case 7:
				// Record Appointment Outcome
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
