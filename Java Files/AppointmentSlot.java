package OOPProject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AppointmentSlot {
	private String appointmentSlotID;
	private Doctor doctor;
	private String startTime;
	private String endTime;
	private Date date;
	private boolean isBooked;

	// Constructor
	public AppointmentSlot(String appointmentSlotID, Doctor doctor, String startTime, String endTime, Date date,
			boolean isBooked) {
		this.appointmentSlotID = appointmentSlotID;
		this.doctor = doctor;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
		this.isBooked = isBooked;
	}
	
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
    public String generateUniqueID() {
        return "AS" + UUID.randomUUID().toString();
    }
	public String getAppointmentSlotID() {
		return appointmentSlotID;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public Date getDate() {
		return date;
	}

	public boolean isBooked() {
		return isBooked;
	}

	// Setters
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public static List<AppointmentSlot> filterByDoctorID(List<AppointmentSlot> slots, String doctorID) {
		List<AppointmentSlot> filteredSlots = new ArrayList<>();
		for (AppointmentSlot slot : slots) {
			if (slot.getDoctor() != null && slot.getDoctor().getHospitalId().equals(doctorID)) {
				filteredSlots.add(slot);
			}
		}
		return filteredSlots;
	}
	
	
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
