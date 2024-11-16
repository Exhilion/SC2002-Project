package OOPProject;

import java.util.List;

/**
 * Interface for managing doctor appointments. This interface defines methods for setting 
 * availability, handling appointment requests, and viewing the appointment schedule for doctors.
 */
public interface DoctorAppointmentManagement {
	
	/**
     * Sets the available appointment slots for a doctor.
     *
     * @param doctorID The unique identifier for the doctor.
     * @param schedule A list of {@link AppointmentSlot} objects representing the doctor's availability.
     */
    void setAvailableDates(String doctorID, List<AppointmentSlot> schedule);
    
    /**
     * Accepts an appointment request, changing its status to confirmed.
     *
     * @param appointmentID The unique identifier for the appointment to be accepted.
     */
    void acceptAppointmentRequest(String appointmentID);
    
    /**
     * Declines an appointment request, changing its status to cancelled or rejected.
     *
     * @param appointmentID The unique identifier for the appointment to be declined.
     */
    void declineAppointmentRequest(String appointmentID);
    
    /**
     * Retrieves the appointment schedule for a specific doctor.
     *
     * @param doctorID The unique identifier for the doctor.
     * @return A list of {@link Appointment} objects representing the doctor's confirmed schedule.
     */
    List<Appointment> viewAppointmentSchedule(String doctorID);
}
