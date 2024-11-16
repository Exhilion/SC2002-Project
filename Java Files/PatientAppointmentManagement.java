package OOPProject;

import java.util.List;

/**
 * The PatientAppointmentManagement interface provides methods to manage appointments for patients.
 * It allows for viewing appointment outcomes, canceling and rescheduling appointments, and viewing available appointment slots.
 */
public interface PatientAppointmentManagement {
	
	/**
     * Views the appointment outcomes for a given patient.
     * 
     * @param patient The patient whose appointment outcomes are to be viewed.
     * @return A list of AppointmentOutcome objects representing the patient's appointment results.
     */
	List<AppointmentOutcome> viewAppointmentOutcome(Patient patient);
	
	/**
     * Cancels an appointment for the given patient.
     * 
     * @param patient The patient who wants to cancel the appointment.
     * @param appointment The appointment to be canceled.
     */
	void cancelAppointment(Patient patient, Appointment appointment);
	
	/**
     * Schedules a new appointment for the patient at the given appointment slot.
     * 
     * @param patient The patient who is scheduling the appointment.
     * @param slot The appointment slot where the patient will be scheduled.
     */
	void scheduleAppointment(Patient patient, AppointmentSlot slot);
	
	/**
     * Reschedules an existing appointment to a new slot.
     * 
     * @param patient The patient whose appointment is being rescheduled.
     * @param appointment The current appointment that needs to be rescheduled.
     * @param newSlot The new appointment slot for the rescheduled appointment.
     * @return The rescheduled appointment.
     */
	Appointment rescheduleAppointment(Patient patient, AppointmentSlot appointment, AppointmentSlot newSlot);
	
	/**
     * Views the available appointment slots.
     * 
     * @return A list of available AppointmentSlot objects.
     */
	List<AppointmentSlot> viewAvailableAppointment();
}
