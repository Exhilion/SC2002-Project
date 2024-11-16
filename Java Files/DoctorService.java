package OOPProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * This class represents the service layer that provides business logic for doctor-related operations.
 * It includes functionalities such as viewing and updating patient medical records, managing personal schedules,
 * handling appointment requests, and recording the outcomes of appointments.
 */
public class DoctorService {

	Scanner scanner = new Scanner(System.in);
	
	/**
	 * Service for managing appointments
	 */
	private AppointmentService appointmentService;
	
	/**
	 * Service for manaing medical records
	 */
	private MedicalRecordService medicalRecordService;
	
	/**
	 * Service for loading data from CSV files
	 */
	private loadCSVClass load;

	// Constructor to inject the services
	/**
     * Constructor to initialize the {@link DoctorService} with necessary services.
     *
     * @param medicalRecordService The {@link MedicalRecordService} used for managing medical records.
     * @param appointmentService The {@link AppointmentService} used for managing appointments.
     * @param load The {@link loadCSVClass} used to load CSV data.
     */
	public DoctorService(MedicalRecordService medicalRecordService, AppointmentService appointmentService,
			loadCSVClass load) {
		this.appointmentService = appointmentService;
		this.medicalRecordService = medicalRecordService;
		this.load = load;
	}

	/**
     * View the medical records of a patient. 
     * This method is to be implemented for viewing the patient's medical history.
     * 
     * @param doctorID The ID of the doctor accessing the medical records.
     */
	public static void viewPatientMedicalRecords(String doctorID) {
		// Implement the logic for viewing patient medical records here
	}

	/**
     * Update the medical records of a patient. 
     * This method is to be implemented for updating patient information after consultations.
     * 
     * @param doctorID The ID of the doctor making the updates to the medical records.
     */
	public static void updatePatientMedicalRecords(String doctorID) {
		// Implement the logic for updating patient medical records here
	}

	// View personal schedule for the doctor
	/**
     * View the personal schedule of the doctor.
     * Displays the doctor's available time slots for appointments.
     * 
     * @param doctorID The ID of the doctor whose schedule is being viewed.
     */
	public void viewPersonalSchedule(String doctorID) {
		List<AppointmentSlot> doctorSchedule = AppointmentSlot.filterByDoctorID(load.getAppointmentSlots(), doctorID);
		System.out.println("\nSchedule:");
		if (doctorSchedule.isEmpty()) {
			System.out.println("No schedule found");
		} else {
			for (AppointmentSlot schedule : doctorSchedule) {
				System.out.println(schedule.toString());
			}
		}
	}

	/**
     * Set the doctor's availability for appointments.
     * Allows the doctor to specify available time slots for appointments.
     * 
     * @param doctorID The ID of the doctor setting availability.
     */
	public void setAvailabilityForAppointments(String doctorID) {
		System.out.println("\nSet Availability for Appointments:");

		String startTime = null;
		String endTime = null;
		String dateStr = null;

		while (startTime == null) {
			System.out.print("Enter Start Time (HH:mm): ");
			startTime = scanner.nextLine();
			if (!startTime.matches("\\d{2}:\\d{2}")) {
				System.out.println("Invalid time format. Please use 'HH:mm'.");
				startTime = null;
			}
		}

		while (endTime == null) {
			System.out.print("Enter End Time (HH:mm): ");
			endTime = scanner.nextLine();
			if (!endTime.matches("\\d{2}:\\d{2}")) {
				System.out.println("Invalid time format. Please use 'HH:mm'.");
				endTime = null;
			}
		}

		Date date = null;
		while (date == null) {
			System.out.print("Enter Date (dd/MM/yyyy): ");
			dateStr = scanner.nextLine();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
			try {
				date = dateFormat.parse(dateStr);
			} catch (ParseException e) {
				System.out.println("Invalid date format. Please use 'dd/MM/yyyy'.");
			}
		}

		String[] startTimeParts = startTime.split(":");
		String[] endTimeParts = endTime.split(":");
		int startHour = Integer.parseInt(startTimeParts[0]);
		int startMinute = Integer.parseInt(startTimeParts[1]);
		int endHour = Integer.parseInt(endTimeParts[0]);
		int endMinute = Integer.parseInt(endTimeParts[1]);

		if ((startHour > endHour) || (startHour == endHour && startMinute >= endMinute)) {
			System.out.println("Start time must be earlier than end time.");
			return;
		}

		if (appointmentService.addAppointmentSlot(doctorID, startTime, endTime, date)) {
			System.out.println("Appointment slot added successfully!");
		} else {
			System.out.println("Failed to add appointment slot.");
		}
	}

	// Accept or decline appointments
	/**
     * Accept or decline appointment requests.
     * Allows the doctor to review pending appointment requests and accept or decline them.
     * 
     * @param doctorID The ID of the doctor reviewing the appointment requests.
     */
	public void acceptDeclineAppointments(String doctorID) {
		System.out.println("\nAccept/Decline Appointment Requests:");
		List<Appointment> pendingAppointments = appointmentService.getPendingAppointments(doctorID);

		if (pendingAppointments.isEmpty()) {
			System.out.println("No pending appointments found.");
		} else {
			for (Appointment appointment : pendingAppointments) {
				appointment.printAppointmentDetails();
				int response = 0;
				boolean validInput = false;

				while (!validInput) {
					System.out.println("Do you want to accept or decline this appointment? (1: Accept, 2: Decline)");

					if (scanner.hasNextInt()) {
						response = scanner.nextInt();
						scanner.nextLine();
						if (response == 1 || response == 2) {
							validInput = true;
						} else {
							System.out.println("Invalid choice! Please enter 1 to accept or 2 to decline.");
						}
					} else {
						System.out.println("Invalid input! Please enter a valid number (1 or 2).");
						scanner.nextLine(); // Consume the invalid input
					}
				}

				String newStatus = (response == 1) ? "Confirmed" : "Cancelled";
				appointmentService.updateAppointmentStatus(appointment.getAppointmentID(), newStatus);
				if (response == 1) {
					appointmentService.updateAppointmentSlotBookingStatus(
							appointment.getAppointmentSlot().getAppointmentSlotID(), true);
					System.out.println("Appointment Confirmed successfully!");
				} else {
					System.out.println("Appointment Cancelled");
				}
			}
		}
	}

	// View upcoming appointments
	/**
     * View all upcoming confirmed appointments for the doctor.
     * Displays a list of confirmed appointments.
     * 
     * @param doctorID The ID of the doctor whose upcoming appointments are being viewed.
     */
	public void viewUpcomingAppointments(String doctorID) {
		List<Appointment> confirmedAppointments = appointmentService.getConfirmedAppointments(doctorID);
		for (Appointment appointment : confirmedAppointments) {
			appointment.printAppointmentDetails();
		}
	}

	// Record the outcome of an appointment
	/**
     * Record the outcome of an appointment after it is completed.
     * This includes updating the patient's medical record, selecting diagnosis, treatment, and prescriptions,
     * and adding the appointment outcome.
     * 
     * @param doctorID The ID of the doctor recording the appointment outcome.
     */
	public void recordAppointmentOutcome(String doctorID) {
		// Filter confirmed appointments for the given doctor
		List<Appointment> filteredAppointments = appointmentService.getConfirmedAppointments(doctorID);

		if (filteredAppointments.isEmpty()) {
			System.out.println("No confirmed appointments found.");
		} else {
			System.out.println("Patient's appointments:");
			for (Appointment appointment : filteredAppointments) {
				appointment.printAppointmentDetails();
			}

			// Select the appointment to update
			int selectedAppointmentIndex = -1;

			// Ensure valid input for appointment selection
			while (selectedAppointmentIndex < 0 || selectedAppointmentIndex >= filteredAppointments.size()) {
				System.out.print(
						"Select the patient to update their medical record (1-" + filteredAppointments.size() + "): ");
				while (!scanner.hasNextInt()) {
					System.out.println("Invalid input. Please enter a valid number.");
					scanner.next(); // Consume the invalid input
				}
				selectedAppointmentIndex = scanner.nextInt() - 1; // Input is 1-indexed, so subtract 1
				scanner.nextLine(); // Consume newline

				if (selectedAppointmentIndex < 0 || selectedAppointmentIndex >= filteredAppointments.size()) {
					System.out.println("Invalid selection. Please select a valid appointment.");
				}
			}

			Appointment selectedAppointment = filteredAppointments.get(selectedAppointmentIndex);
			Patient selectedPatient = selectedAppointment.getPatient();

			if (selectedPatient != null) {
				System.out.println("Patient ID: " + selectedPatient.getHospitalId());
				List<Diagnosis> diagnoses = load.getDiagnoses();
				List<Treatment> treatments = load.getTreatments();
				List<Prescription> prescriptions = load.getPrescriptions();

				// Select diagnosis
				int diagnosisChoice = -1;
				while (diagnosisChoice < 0 || diagnosisChoice >= diagnoses.size()) {
					System.out.println("Available Diagnoses:");
					for (int i = 0; i < diagnoses.size(); i++) {
						System.out.println((i + 1) + ". " + diagnoses.get(i).getdiagnosis());
					}
					System.out.print("Select a diagnosis (1-" + diagnoses.size() + "): ");
					while (!scanner.hasNextInt()) {
						System.out.println("Invalid input. Please enter a valid number.");
						scanner.next();
					}
					diagnosisChoice = scanner.nextInt() - 1;
					scanner.nextLine();

					if (diagnosisChoice < 0 || diagnosisChoice >= diagnoses.size()) {
						System.out.println("Invalid selection. Please select a valid diagnosis.");
					}
				}

				// Select treatment
				int treatmentChoice = -1;
				while (treatmentChoice < 0 || treatmentChoice >= treatments.size()) {
					System.out.println("Available Treatments:");
					for (int i = 0; i < treatments.size(); i++) {
						System.out.println((i + 1) + ". " + treatments.get(i).gettreatment());
					}
					System.out.print("Select a treatment (1-" + treatments.size() + "): ");
					while (!scanner.hasNextInt()) {
						System.out.println("Invalid input. Please enter a valid number.");
						scanner.next();
					}
					treatmentChoice = scanner.nextInt() - 1;
					scanner.nextLine();

					if (treatmentChoice < 0 || treatmentChoice >= treatments.size()) {
						System.out.println("Invalid selection. Please select a valid treatment.");
					}
				}

				// Select prescriptions
				List<Prescription> selectedPrescriptions = new ArrayList<>();
				String addMore = "y";
				while ("y".equalsIgnoreCase(addMore)) {
					int prescriptionChoice = -1;
					while (prescriptionChoice < 0 || prescriptionChoice >= prescriptions.size()) {
						System.out.println("Available Prescriptions:");
						for (int i = 0; i < prescriptions.size(); i++) {
							Prescription p = prescriptions.get(i);
							System.out.println((i + 1) + ". " + p.getMedication().getMedicineName() + " - "
									+ p.getDosage() + " mg");
						}
						System.out.print("Select a prescription by number (1-" + prescriptions.size() + "): ");
						while (!scanner.hasNextInt()) {
							System.out.println("Invalid input. Please enter a valid number.");
							scanner.next();
						}
						prescriptionChoice = scanner.nextInt() - 1;
						scanner.nextLine();

						if (prescriptionChoice >= 0 && prescriptionChoice < prescriptions.size()) {
							selectedPrescriptions.add(prescriptions.get(prescriptionChoice));
							System.out.println("Prescription added: "
									+ prescriptions.get(prescriptionChoice).getMedication().getMedicineName());
						} else {
							System.out.println("Invalid choice.");
						}
					}

					System.out.print("Add another prescription? (y/n): ");
					addMore = scanner.nextLine();
				}

				// Enter consultation notes
				System.out.print("Enter consultation notes: \n");
				String consultationNotes = scanner.nextLine();

				// Create a new medical record for the patient
				String recordID = "MR" + UUID.randomUUID().toString().substring(0, 8);
				MedicalRecord newMedicalRecord = new MedicalRecord(recordID, selectedPatient,
						diagnoses.get(diagnosisChoice), treatments.get(treatmentChoice), selectedPrescriptions);

				// Add the medical record to the CSV
				medicalRecordService.addMedicalRecord(recordID, selectedPatient, diagnoses.get(diagnosisChoice),
						treatments.get(treatmentChoice), selectedPrescriptions);
				System.out.println("Medical record updated for Patient ID " + selectedPatient.getHospitalId());

				// Create the appointment outcome
				String appointmentID = selectedAppointment.getAppointmentID();
				String appointmentOutcomeID = "A" + UUID.randomUUID().toString();
				appointmentService.addAppointmentOutcome(appointmentOutcomeID, selectedAppointment, newMedicalRecord,
						consultationNotes, PrescriptionStatus.Pending);

				// Update the appointment status
				appointmentService.updateAppointmentStatus(appointmentID, "Completed");

			} else {
				System.out.println("Invalid selection.");
			}
		}
	}

}
