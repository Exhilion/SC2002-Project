package OOPProject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * The class represents a time slot available for appointments with a doctor.
 * Each slot includes information about the doctor, time range, date, and booking status.
 */
public class AppointmentSlot {
	private String appointmentSlotID;
	private Doctor doctor;
	private String startTime;
	private String endTime;
	private Date date;
	private boolean isBooked;

	// Constructor
	/**
    * Constructs an {@code AppointmentSlot} with the specified details.
    *
    * @param appointmentSlotID the unique ID of the appointment slot
    * @param doctor the {@link Doctor} associated with the appointment slot
    * @param startTime the start time of the slot (format: HH:mm)
    * @param endTime the end time of the slot (format: HH:mm)
    * @param date the date of the appointment slot
    * @param isBooked {@code true} if the slot is booked, {@code false} otherwise
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
     * Constructs an {@code AppointmentSlot} without specifying a doctor.
     *
     * @param appointmentSlotID the unique ID of the appointment slot
     * @param startTime the start time of the slot (format: HH:mm)
     * @param endTime the end time of the slot (format: HH:mm)
     * @param date the date of the appointment slot
     * @param isBooked {@code true} if the slot is booked, {@code false} otherwise
     */
	public AppointmentSlot(String appointmentSlotID, String startTime, String endTime, Date date,
			boolean isBooked) {
		this.appointmentSlotID = appointmentSlotID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
		this.isBooked = isBooked;
	}

	// Getters
	
	 // Generate a unique AppointmentID using UUID
	/**
     * Generates a unique ID for the appointment slot.
     *
     * @return a unique appointment slot ID in the format "AS" followed by a UUID
     */
    public String generateUniqueID() {
        return "AS" + UUID.randomUUID().toString();
    }
    
    /**
     * Retrieves the appointment slot ID.
     *
     * @return the appointment slot ID
     */
	public String getAppointmentSlotID() {
		return appointmentSlotID;
	}

	/**
     * Retrieves the doctor associated with the slot.
     *
     * @return the {@link Doctor} object
     */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
     * Retrieves the start time of the slot.
     *
     * @return the start time in HH:mm format
     */
	public String getStartTime() {
		return startTime;
	}

	/**
     * Retrieves the end time of the slot.
     *
     * @return the end time in HH:mm format
     */
	public String getEndTime() {
		return endTime;
	}

	/**
     * Retrieves the date of the slot.
     *
     * @return the {@link Date} object
     */
	public Date getDate() {
		return date;
	}

	/**
     * Checks if the slot is booked.
     *
     * @return {@code true} if the slot is booked, {@code false} otherwise
     */
	public boolean isBooked() {
		return isBooked;
	}

	// Setters
	/**
     * Updates the doctor associated with the slot.
     *
     * @param doctor the new {@link Doctor} object
     */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
     * Updates the start time of the slot.
     *
     * @param startTime the new start time in HH:mm format
     */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
     * Updates the end time of the slot.
     *
     * @param endTime the new end time in HH:mm format
     */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
     * Updates the date of the slot.
     *
     * @param date the new {@link Date} object
     */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
     * Updates the booking status of the slot.
     *
     * @param isBooked {@code true} if the slot is booked, {@code false} otherwise
     */
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	/**
     * Filters appointment slots by a specific doctor's ID.
     *
     * @param slots the list of {@code AppointmentSlot} objects to filter
     * @param doctorID the ID of the doctor to filter by
     * @return a list of {@code AppointmentSlot} objects associated with the specified doctor
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
     * Filters appointment slots to retrieve only available (unbooked) slots.
     *
     * @param slots the list of {@code AppointmentSlot} objects to filter
     * @return a list of available {@code AppointmentSlot} objects
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
     * Returns a string representation of the appointment slot, including all its details.
     *
     * @return a string describing the appointment slot
     */
	@Override
	public String toString() {
	    return "Appointment Slot ID: " + appointmentSlotID +
	           ", Doctor: " + doctor.getDoctorName() +  // Assuming Doctor class has a getName() method
	           ", Start Time: " + startTime +
	           ", End Time: " + endTime +
	           ", Date: " + date +
	           ", Is Booked: " + (isBooked ? "Yes" : "No");
	}



}
