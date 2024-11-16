package OOPProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentSlotCSV {

	private static final String TIME_FORMAT = "H:mm";
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);

	static {
		timeFormat.setLenient(false);
	}

	/**
	 * Loads appointment slots from the CSV file and returns a list of
	 * AppointmentSlot objects.
	 * 
	 * @param doctors A list of Doctor objects to find the corresponding Doctor for
	 *                each appointment slot.
	 * @return A list of AppointmentSlot objects.
	 */

	public List<AppointmentSlot> loadAppointmentSlotsFromCSV(List<Doctor> doctors) {
		List<AppointmentSlot> appointmentSlots = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(AppConfig.APPOINTMENT_SLOT_FILE_PATH))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length == 6) {
					String appointmentSlotID = values[0];
					String doctorID = values[1];
					Doctor doctor = findDoctorByID(doctorID, doctors);

					if (doctor == null) {
						System.out.println("Doctor with Name " + doctorID + " not found.");
						continue;
					}

					String startTime = values[2];
					String endTime = values[3];
					String dateStr = values[4];
					boolean isBooked = Boolean.parseBoolean(values[5]);

					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = null;
					try {
						date = dateFormat.parse(dateStr);
					} catch (Exception e) {
						System.out.println("Invalid date format: " + dateStr);
						continue;
					}

					AppointmentSlot appointmentSlot = new AppointmentSlot(appointmentSlotID, doctor, startTime, endTime,
							date, isBooked);
					appointmentSlots.add(appointmentSlot);
				} else {
					System.out.println("Invalid record format: " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appointmentSlots;
	}

	/**
	 * Adds a new appointment slot to the CSV file.
	 * 
	 * @param slot     The AppointmentSlot object to be added.
	 * @param doctorID The ID of the doctor to which the appointment slot belongs.
	 * @return True if the appointment slot was successfully added, false otherwise.
	 */
	public boolean addAppointmentSlotToCSV(AppointmentSlot slot, String doctorID) {
		if (!isValidTimeFormat(slot.getStartTime()) || !isValidTimeFormat(slot.getEndTime())) {
			System.out.println("Invalid time format. Please use 'H:mm' format (e.g., 9:00, 13:30).");
			return false;
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(AppConfig.APPOINTMENT_SLOT_FILE_PATH, true))) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String dateStr = dateFormat.format(slot.getDate());

			String newLine = String.format("%s,%s,%s,%s,%s,%s", slot.getAppointmentSlotID(), doctorID,
					slot.getStartTime(), slot.getEndTime(), dateStr, slot.isBooked());

			bw.write(newLine);
			bw.newLine();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Updates the booking status of an appointment slot in the CSV file.
	 * 
	 * @param appointmentSlotID The ID of the appointment slot to update.
	 * @param newStatus         The new booking status (true for booked, false for
	 *                          available).
	 * @return True if the update was successful, false otherwise.
	 */
	public static boolean updateAppointmentSlotBookingStatus(String appointmentSlotID, boolean newStatus) {
		File inputFile = new File(AppConfig.APPOINTMENT_SLOT_FILE_PATH);
		List<String> lines = new ArrayList<>();
		boolean isUpdated = false;

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				if (values[0].equals(appointmentSlotID)) {

					System.out.println("Updating appointment slot with ID: " + appointmentSlotID);
					values[5] = String.valueOf(newStatus);
					isUpdated = true;
				}
				lines.add(String.join(",", values));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		if (!isUpdated) {
			System.out.println("Appointment Slot " + appointmentSlotID + " not found or no change in booking status.");
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
	 * Validates the format of a time string.
	 * 
	 * @param time The time string to be validated.
	 * @return True if the time format is valid, false otherwise.
	 */
	public boolean isValidTimeFormat(String time) {
		try {
			timeFormat.parse(time);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Helper method to find a Doctor by their ID.
	 * 
	 * @param doctorID The ID of the doctor to be searched for.
	 * @param doctors  The list of Doctor objects to search through.
	 * @return The Doctor object if found, null otherwise.
	 */
	private Doctor findDoctorByID(String doctorID, List<Doctor> doctors) {
		for (Doctor doctor : doctors) {
			if (doctor.getHospitalId().equalsIgnoreCase(doctorID)) {
				return doctor;
			}
		}
		return null;
	}
}
