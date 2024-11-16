package OOPProject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Represents an appointment slot for a doctor in the system. The slot includes
 * information about the doctor, the time, date, and booking status.
 */
public class AppointmentSlot {
	private String appointmentSlotID;
	private Doctor doctor;
	private String startTime;
	private String endTime;
	private Date date;
	private boolean isBooked;

	/**
	 * Constructor to initialize an AppointmentSlot with all properties.
	 * 
	 * @param appointmentSlotID the unique ID for the appointment slot
	 * @param doctor            the doctor associated with the appointment slot
	 * @param startTime         the start time of the appointment slot
	 * @param endTime           the end time of the appointment slot
	 * @param date              the date of the appointment slot
	 * @param isBooked          the booking status of the appointment slot
	 */
	public AppointmentSlot(String appointmentSlotID, Doctor doctor, String startTime, String endTime, Date date,
			boolean isBooked) {
		this.appointmentSlotID = appointmentSlotID;
		this.doctor = doctor;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
		this.isBooked = isBooked;
	}

	/**
	 * Constructor to initialize an AppointmentSlot with all properties excluding
	 * the doctor.
	 * 
	 * @param appointmentSlotID the unique ID for the appointment slot
	 * @param startTime         the start time of the appointment slot
	 * @param endTime           the end time of the appointment slot
	 * @param date              the date of the appointment slot
	 * @param isBooked          the booking status of the appointment slot
	 */
	public AppointmentSlot(String appointmentSlotID, String startTime, String endTime, Date date, boolean isBooked) {
		this.appointmentSlotID = appointmentSlotID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
		this.isBooked = isBooked;
	}

	/**
	 * Generates a unique appointment slot ID using UUID.
	 * 
	 * @return a unique appointment slot ID
	 */
	public String generateUniqueID() {
		return "AS" + UUID.randomUUID().toString();
	}

	/**
	 * Gets the appointment slot ID.
	 * 
	 * @return the appointment slot ID
	 */
	public String getAppointmentSlotID() {
		return appointmentSlotID;
	}

	/**
	 * Gets the doctor associated with the appointment slot.
	 * 
	 * @return the doctor associated with the appointment slot
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Gets the start time of the appointment slot.
	 * 
	 * @return the start time of the appointment slot
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * Gets the end time of the appointment slot.
	 * 
	 * @return the end time of the appointment slot
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * Gets the date of the appointment slot.
	 * 
	 * @return the date of the appointment slot
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Gets the booking status of the appointment slot.
	 * 
	 * @return true if the appointment slot is booked, false otherwise
	 */
	public boolean isBooked() {
		return isBooked;
	}

	/**
	 * Sets the doctor associated with the appointment slot.
	 * 
	 * @param doctor the doctor to associate with the appointment slot
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Sets the start time of the appointment slot.
	 * 
	 * @param startTime the start time to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * Sets the end time of the appointment slot.
	 * 
	 * @param endTime the end time to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * Sets the date of the appointment slot.
	 * 
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Sets the booking status of the appointment slot.
	 * 
	 * @param isBooked true if the slot is booked, false otherwise
	 */
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	/**
	 * Filters a list of appointment slots by a given doctor ID.
	 * 
	 * @param slots    the list of appointment slots to filter
	 * @param doctorID the doctor ID to filter by
	 * @return a list of appointment slots associated with the specified doctor ID
	 */
	public static List<AppointmentSlot> filterByDoctorID(List<AppointmentSlot> slots, String doctorID) {
		List<AppointmentSlot> filteredSlots = new ArrayList<>();
		for (AppointmentSlot slot : slots) {
			if (slot.getDoctor() != null && slot.getDoctor().getHospitalId().equals(doctorID)) {
				filteredSlots.add(slot);
			}
		}
		return filteredSlots;
	}

	/**
	 * Filters a list of appointment slots to only include available (unbooked)
	 * slots.
	 * 
	 * @param slots the list of appointment slots to filter
	 * @return a list of available (unbooked) appointment slots
	 */
	public static List<AppointmentSlot> filterAvailableSlots(List<AppointmentSlot> slots) {
		List<AppointmentSlot> availableSlots = new ArrayList<>();

		for (AppointmentSlot slot : slots) {
			// Check if the slot is unbooked
			if (!slot.isBooked()) {
				availableSlots.add(slot);
			}
		}

		return availableSlots;
	}

	/**
	 * Returns a string representation of the appointment slot.
	 * 
	 * @return a string representation of the appointment slot
	 */
	@Override
	public String toString() {
		return "Appointment Slot ID: " + appointmentSlotID + ", Doctor: " + doctor.getDoctorName() + // Assuming Doctor
																										// class has a
																										// getName()
																										// method
				", Start Time: " + startTime + ", End Time: " + endTime + ", Date: " + date + ", Is Booked: "
				+ (isBooked ? "Yes" : "No");
	}

}
