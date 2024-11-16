package OOPProject;

import java.util.List;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

/**
 * The {@code PatientService} class provides functionality for patients to manage their appointments, personal information,
 * and view medical records. It acts as a service layer to facilitate interactions with other modules and data sources.
 */
public class PatientService {
    private Scanner scanner = new Scanner(System.in);

    private AppointmentService appointmentService;
    private loadCSVClass load;

    /**
     * Constructs a new {@code PatientService} instance with the specified appointment service and data loader.
     *
     * @param appointmentService The service to manage patient appointments.
     * @param load               The loader for retrieving data from CSV files.
     */
    public PatientService(AppointmentService appointmentService, loadCSVClass load) {
        this.appointmentService = appointmentService;
        this.load = load;
    }

    /**
     * Updates the personal information of the patient (email or contact number).
     * Prompts the user to select the type of information to update and validates input.
     */
    public void updatePersonalInfo() {
        System.out.println("Enter Patient ID:");
        String patientID = scanner.nextLine();

        if (patientID.isEmpty()) {
            System.out.println("Patient ID cannot be empty. Exiting.");
            return;
        }

        System.out.println("Would you like to update (1)Email or (2)Contact Number?");
        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 for Email or 2 for Contact Number:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1 for Email or 2 for Contact Number:");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        System.out.println("Enter new value:");
        String newValue = scanner.nextLine();

        if (choice == 1) {
            if (!newValue.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                System.out.println("Invalid email format. Exiting.");
                return;
            }
        } else if (choice == 2) {
            if (!newValue.matches("^\\+?[0-9]{7,15}$")) {
                System.out.println("Invalid contact number format. Exiting.");
                return;
            }
        }

        PatientCSV.updatePatientRecords(patientID, choice, newValue);
        System.out.println("Personal information updated successfully.");
    }

    /**
     * Displays available appointment slots for the patient.
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
     * Allows the patient to schedule an appointment.
     *
     * @param username The hospital ID of the patient scheduling the appointment.
     */
    public void scheduleAppointment(String username) {
        System.out.println("Enter Doctor ID: ");
        String doctorID = scanner.nextLine().trim();

        if (doctorID.isEmpty()) {
            System.out.println("Doctor ID cannot be empty. Exiting.");
            return;
        }

        System.out.println("Enter the date for your appointment: (DD/MM/YYYY)");
        String dateOfChoice = scanner.nextLine().trim();

        if (!dateOfChoice.matches("\\d{2}/\\d{2}/\\d{4}")) {
            System.out.println("Invalid date format. Please enter in DD/MM/YYYY format. Exiting.");
            return;
        }

        System.out.println("Enter the start time (HH:mm): ");
        String startTime = scanner.nextLine().trim();
        if (!startTime.matches("\\d{2}:\\d{2}")) {
            System.out.println("Invalid time format. Please enter in HH:mm format. Exiting.");
            return;
        }

        System.out.println("Enter the end time (HH:mm): ");
        String endTime = scanner.nextLine().trim();

        if (!endTime.matches("\\d{2}:\\d{2}")) {
            System.out.println("Invalid time format. Please enter in HH:mm format. Exiting.");
            return;
        }

        if (!isTimeBefore(startTime, endTime)) {
            System.out.println("Start time must be before end time. Exiting.");
            return;
        }

        appointmentService.scheduleAppointment(username, doctorID, dateOfChoice, startTime, endTime);
    }

    /**
     * Allows the patient to reschedule an existing appointment by first canceling it.
     *
     * @param username The hospital ID of the patient rescheduling the appointment.
     */
    public void rescheduleAppointment(String username) {
        System.out.println("Enter the Appointment ID to reschedule: ");
        String appointmentID = scanner.nextLine().trim();

        if (appointmentID.isEmpty()) {
            System.out.println("Appointment ID cannot be empty. Exiting.");
            return;
        }

        appointmentService.cancelAppointment(username, appointmentID);

        scheduleAppointment(username); // Reuse scheduling logic
    }

    /**
     * Cancels an appointment for the patient.
     *
     * @param username The hospital ID of the patient canceling the appointment.
     */
    public void cancelAppointment(String username) {
        System.out.println("Enter the Appointment ID to cancel: ");
        String appointmentID = scanner.nextLine().trim();
        if (appointmentID.isEmpty()) {
            System.out.println("Appointment ID cannot be empty. Exiting.");
            return;
        }
        appointmentService.cancelAppointment(username, appointmentID);
    }

    /**
     * Displays the patient's scheduled appointments.
     *
     * @param username The hospital ID of the patient.
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
     * Displays the outcomes of past appointments for the patient.
     *
     * @param username The hospital ID of the patient.
     */
    public void viewAppointmentOutcomeRecords(String username) {
        AppointmentOutcome.viewAppointmentOutcomeRecords(load.getAppointmentOutcomes(), username);
    }

    /**
     * Displays the medical records associated with the patient.
     *
     * @param username The hospital ID of the patient.
     */
    public void viewMedicalRecord(String username) {
        List<MedicalRecord> filteredRecords = MedicalRecord.filterByHospitalId(load.getMedicalRecords(), username);

        for (MedicalRecord record : filteredRecords) {
            record.displayMedicalRecords(filteredRecords);
        }
    }

    /**
     * Helper method to validate if the start time is earlier than the end time.
     *
     * @param startTime The start time in HH:mm format.
     * @param endTime   The end time in HH:mm format.
     * @return {@code true} if the start time is earlier than the end time; otherwise, {@code false}.
     */
    private static boolean isTimeBefore(String startTime, String endTime) {
        try {
            LocalTime start = LocalTime.parse(startTime); // Parse startTime into a LocalTime object
            LocalTime end = LocalTime.parse(endTime);     // Parse endTime into a LocalTime object
            return start.isBefore(end); // Compare if start is before end
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing time: " + e.getMessage());
            return false; // If parsing fails, consider invalid input
        }
    }
}