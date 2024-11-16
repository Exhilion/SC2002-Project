package OOPProject;

import java.util.List;
import java.util.Scanner;

/**
 * The PatientService class handles the business logic related to patient operations
 * such as viewing medical records, updating personal information, scheduling and
 * rescheduling appointments, and more. It interacts with other services like 
 * AppointmentService and loadCSVClass for data manipulation.
 */
public class PatientService {
	private Scanner scanner = new Scanner(System.in);

	private AppointmentService appointmentService;
	private loadCSVClass load;

	/**
     * Constructs a PatientService object with the specified AppointmentService and loadCSVClass.
     * 
     * @param appointmentService The appointment service used to handle appointment operations.
     * @param load The loadCSVClass instance used to load data from CSV files.
     */
	public PatientService(AppointmentService appointmentService, loadCSVClass load) {
		this.appointmentService = appointmentService;
		this.load = load;
	}

	/**
     * Views the medical records for the patient identified by the given username.
     * The records are filtered by hospital ID from the loaded data.
     * 
     * @param username The username of the patient whose medical records are to be viewed.
     */
	public void viewMedicalRecord(String username) {
		List<MedicalRecord> filteredRecords = MedicalRecord.filterByHospitalId(load.getMedicalRecords(),
				username);
	}

	/**
     * Allows the patient to update their personal information such as email or contact number.
     * It prompts the user to choose what information to update and then asks for the new value.
     * 
     * @param patientID The ID of the patient whose information is to be updated.
     */
	public void updatePersonalInfo() {
		System.out.println("Enter Patient ID:");
		String patientID = scanner.nextLine();

		System.out.println("Would you like to update (1)Email or (2)Contact Number?");
		int choice = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter new value:");
		String newValue = scanner.nextLine();

		if (choice == 1) {
			PatientCSV.updatePatientRecords(patientID, choice, newValue); // Update Email
		} else if (choice == 2) {
			PatientCSV.updatePatientRecords(patientID, choice, newValue); // Update Contact Number
		} else {
			System.out.println("Invalid choice. Exiting.");
		}
	}

	/**
     * Displays available appointment slots to the patient. If no slots are available, 
     * it informs the user.
     */
	public void viewAvailableAppointments() {
		List<AppointmentSlot> availableSlots = AppointmentSlot.filterAvailableSlots(load.getAppointmentSlots());
		if (availableSlots.isEmpty()) {
			System.out.println("No schedule found");
		} else {
			for (AppointmentSlot schedule : availableSlots) {
				System.out.println(schedule.toString());
			}
		}
	}

	/**
     * Allows the patient to schedule an appointment by entering details like doctor ID,
     * appointment date, start time, and end time.
     * 
     * @param username The username of the patient scheduling the appointment.
     */
	public void scheduleAppointment(String username) {
		System.out.println("Enter Doctor ID: ");
		String doctorID = scanner.nextLine();

		System.out.println("Enter the date for your appointment: (DD/MM/YYYY)");
		String dateOfChoice = scanner.nextLine();

		System.out.println("Enter the start time (HH:mm): ");
		String startTime = scanner.nextLine();

		System.out.println("Enter the end time (HH:mm): ");
		String endTime = scanner.nextLine();

		appointmentService.scheduleAppointment(username, doctorID, dateOfChoice, startTime, endTime);
	}

	/**
     * Allows the patient to reschedule an existing appointment by entering the appointment ID
     * and providing new appointment details.
     * 
     * @param username The username of the patient rescheduling the appointment.
     */
	public void rescheduleAppointment(String username) {
		System.out.println("Enter the Appointment ID to reschedule: ");
		String appointmentID = scanner.nextLine();
		
		appointmentService.cancelAppointment(username, appointmentID);

		System.out.println("Enter Doctor ID: ");
		String doctorID = scanner.nextLine();

		System.out.println("Enter the date for your appointment: (DD/MM/YYYY)");
		String dateOfChoice = scanner.nextLine();

		System.out.println("Enter the start time (HH:mm): ");
		String startTime = scanner.nextLine();

		System.out.println("Enter the end time (HH:mm): ");
		String endTime = scanner.nextLine();

		appointmentService.scheduleAppointment(username, doctorID, dateOfChoice, startTime, endTime);
	}

	/**
     * Allows the patient to cancel an existing appointment by providing the appointment ID.
     * 
     * @param username The username of the patient canceling the appointment.
     */
	public void cancelAppointment(String username) {
		System.out.println("Enter the Appointment ID to cancel: ");
		String appointmentID = scanner.nextLine();
		appointmentService.cancelAppointment(username, appointmentID);
	}

	/**
     * Views the scheduled appointments for the patient identified by the username.
     * It filters the scheduled appointments by the patient's username.
     * 
     * @param username The username of the patient whose appointments are to be viewed.
     */
	public void viewScheduledAppointments(String username) {
		List<Appointment> scheduledAppointments = Appointment.filterScheduledAppointment(load.getAppointments(),
				username);
		if (scheduledAppointments.isEmpty()) {
			System.out.println("No schedule found");
		} else {
			for (Appointment schedule : scheduledAppointments) {
				System.out.println(schedule.toString());
			}
		}
	}

	/**
     * Views the appointment outcome records for the patient identified by the username.
     * 
     * @param username The username of the patient whose appointment outcomes are to be viewed.
     */
	public void viewAppointmentOutcomeRecords(String username) {
		AppointmentOutcome.viewAppointmentOutcomeRecords(load.getAppointmentOutcomes(), username);
	}

}
