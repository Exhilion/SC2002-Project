package OOPProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AppointmentCSV {

	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	/**
	 * Reads appointments from a CSV file and creates Appointment objects.
	 * 
	 * @param slots    List of AppointmentSlot objects to match against.
	 * @param patients List of Patient objects to match against.
	 * @return A list of Appointment objects read from the CSV.
	 */
	public List<Appointment> loadAppointmentsFromCSV(List<AppointmentSlot> slots, List<Patient> patients) {
		List<Appointment> appointments = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.APPOINTMENT_FILE_PATH))) {
			String line;
			br.readLine();

			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length == 4) {
					String appointmentID = values[0];
					String slotID = values[1];
					String status = values[2];
					String patientID = values[3];

					AppointmentSlot slot = findSlotByID(slotID, slots);
					if (slot == null) {
						System.out.println("Appointment Slot ID " + slotID + " not found.");
						continue;
					}

					Patient patient = findPatientByID(patientID, patients);
					if (patient == null) {
						System.out.println("Patient with ID " + patientID + " not found.");
						continue;
					}

					Appointment appointment = new Appointment(appointmentID, slot, status, patient);
					appointments.add(appointment);
				} else {
					System.out.println("Invalid record format: " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appointments;
	}

	/**
	 * Updates the status of an appointment in the CSV file.
	 * 
	 * @param appointmentID The ID of the appointment to update.
	 * @param newStatus     The new status to set for the appointment.
	 * @return True if the status was updated successfully, false otherwise.
	 */
	public boolean updateAppointmentStatus(String appointmentID, String newStatus) {
		File inputFile = new File(AppConfig.APPOINTMENT_FILE_PATH);
		List<String> lines = new ArrayList<>();
		boolean isUpdated = false;

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				if (values[0].equals(appointmentID)) {

					System.out.println("Updating appointment with ID: " + appointmentID);
					values[2] = newStatus;
					isUpdated = true;
				}
				lines.add(String.join(",", values));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		if (!isUpdated) {
			System.out.println("Appointment" + appointmentID + "ID not found or no change in status.");
			return false;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
			for (String updatedLine : lines) {
				writer.write(updatedLine);
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Finds an AppointmentSlot object by its ID.
	 * 
	 * @param slotID The ID of the appointment slot to search for.
	 * @param slots  List of AppointmentSlot objects to search through.
	 * @return The AppointmentSlot object if found, null otherwise.
	 */
	private AppointmentSlot findSlotByID(String slotID, List<AppointmentSlot> slots) {
		for (AppointmentSlot slot : slots) {
			if (slot.getAppointmentSlotID().equalsIgnoreCase(slotID)) {
				return slot;
			}
		}
		return null;
	}

	/**
	 * Finds a Patient object by its hospital ID.
	 * 
	 * @param patientID The ID of the patient to search for.
	 * @param patients  List of Patient objects to search through.
	 * @return The Patient object if found, null otherwise.
	 */
	private Patient findPatientByID(String patientID, List<Patient> patients) {
		for (Patient patient : patients) {
			if (patient.getHospitalId().equalsIgnoreCase(patientID)) {
				return patient;
			}
		}
		return null;
	}

	/**
	 * Schedules a new appointment by finding an available slot, updating the
	 * booking status, and writing the appointment details to the CSV file.
	 * 
	 * @param slots        List of available appointment slots.
	 * @param patientID    The ID of the patient scheduling the appointment.
	 * @param doctorID     The ID of the doctor for the appointment.
	 * @param dateOfChoice The desired date of the appointment.
	 * @param startTime    The start time for the appointment.
	 * @param endTime      The end time for the appointment.
	 */
	public static void scheduleAppointment(List<AppointmentSlot> slots, String patientID, String doctorID,
			String dateOfChoice, String startTime, String endTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		boolean appointmentScheduled = false;
		String appointmentSlotID = null;

		try {
			Date targetDate = dateFormat.parse(dateOfChoice);

			for (AppointmentSlot slot : slots) {
				if (slot.getDoctor() != null && slot.getDoctor().getHospitalId().equals(doctorID)
						&& dateFormat.format(slot.getDate()).equals(dateFormat.format(targetDate)) && !slot.isBooked()
						&& slot.getStartTime().equals(startTime) && slot.getEndTime().equals(endTime)) {

					appointmentSlotID = slot.getAppointmentSlotID();
					System.out.println("Appointment has been scheduled!");
					appointmentScheduled = true;
					break;
				}
			}

			if (!appointmentScheduled) {
				System.out.println("Appointment has not been scheduled. No available slots found.");
				return;
			}

			boolean updateSuccess = AppointmentSlotCSV.updateAppointmentSlotBookingStatus(appointmentSlotID, true);

			if (updateSuccess) {
				System.out.println("Booking status updated successfully in AppointmentSlot.csv.");

				String appointmentID = "A" + UUID.randomUUID().toString();
				String status = "Pending";

				try (PrintWriter writer = new PrintWriter(new FileWriter(AppConfig.APPOINTMENT_FILE_PATH, true))) {
					writer.printf("%s,%s,%s,%s%n", appointmentID, appointmentSlotID, status, patientID);
					System.out.println("New appointment entry added to Appointment.csv.");
				} catch (IOException e) {
					System.out.println("An error occurred while writing to Appointment.csv.");
					e.printStackTrace();
				}

			} else {
				System.out.println("Failed to update booking status in the AppointmentSlot.csv.");
			}

		} catch (ParseException e) {
			System.out.println("Invalid date format: " + dateOfChoice);
			e.printStackTrace();
		}
	}

	/**
	 * Cancels an appointment by updating its status and updating the corresponding
	 * appointment slot.
	 * 
	 * @param patientID     The ID of the patient who wants to cancel the
	 *                      appointment.
	 * @param appointmentID The ID of the appointment to cancel.
	 */
	public static void cancelAppointment(String patientID, String appointmentID) {
		String appointmentSlotID = cancelAppointmentInCSV(patientID, appointmentID);
		if (appointmentSlotID != null) {
			AppointmentSlotCSV.updateAppointmentSlotBookingStatus(appointmentSlotID, false);
		}
	}

	private static String cancelAppointmentInCSV(String patientID, String appointmentID) {
		List<String> appointmentLines = new ArrayList<>();
		boolean appointmentFound = false;
		String appointmentSlotID = null;

		try (BufferedReader reader = new BufferedReader(new FileReader(AppConfig.APPOINTMENT_FILE_PATH))) {
			String line;
			appointmentLines.add(reader.readLine());
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				if (values[0].equalsIgnoreCase(appointmentID) && values[3].equalsIgnoreCase(patientID)) {
					System.out.println("Cancelling appointment with ID: " + appointmentID);
					values[2] = "Cancelled";
					appointmentSlotID = values[1];
					appointmentFound = true;
				}
				appointmentLines.add(String.join(",", values));
			}
		} catch (IOException e) {
			System.out.println("Error reading the Appointment.csv file.");
			e.printStackTrace();
			return null;
		}

		if (!appointmentFound) {
			System.out.println("No matching appointment found for Patient ID " + patientID + " with Appointment ID "
					+ appointmentID);
			return null;
		}

		try (PrintWriter writer = new PrintWriter(new FileWriter(AppConfig.APPOINTMENT_FILE_PATH))) {
			for (String updatedLine : appointmentLines) {
				writer.println(updatedLine);
			}
			System.out.println("Appointment status updated to 'Cancelled' in the Appointment.csv.");
		} catch (IOException e) {
			System.out.println("Error writing to the Appointment.csv file.");
			e.printStackTrace();
		}

		return appointmentSlotID;
	}
}
