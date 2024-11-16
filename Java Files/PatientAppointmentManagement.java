package OOPProject;

import java.util.List;

/**
 * The {@code PatientAppointmentManagement} interface defines methods to manage
 * patient appointments, including viewing appointment outcomes, scheduling,
 * canceling, rescheduling, and viewing available appointments.
 */
public interface PatientAppointmentManagement {

    /**
     * Retrieves the list of appointment outcomes for a specific patient.
     *
     * @param patient The {@link Patient} whose appointment outcomes are to be retrieved.
     * @return A {@link List} of {@link AppointmentOutcome} objects associated with the patient.
     */
    List<AppointmentOutcome> viewAppointmentOutcome(Patient patient);

    /**
     * Cancels an existing appointment for a specific patient.
     *
     * @param patient     The {@link Patient} whose appointment is to be canceled.
     * @param appointment The {@link Appointment} to be canceled.
     */
    void cancelAppointment(Patient patient, Appointment appointment);

    /**
     * Schedules a new appointment for a specific patient in the specified appointment slot.
     *
     * @param patient The {@link Patient} for whom the appointment is to be scheduled.
     * @param slot    The {@link AppointmentSlot} where the appointment is to be scheduled.
     */
    void scheduleAppointment(Patient patient, AppointmentSlot slot);

    /**
     * Reschedules an existing appointment for a patient to a new appointment slot.
     *
     * @param patient      The {@link Patient} whose appointment is to be rescheduled.
     * @param appointment  The {@link AppointmentSlot} to be rescheduled.
     * @param newSlot      The new {@link AppointmentSlot} to which the appointment is to be rescheduled.
     * @return The updated {@link Appointment} object with the new schedule.
     */
    Appointment rescheduleAppointment(Patient patient, AppointmentSlot appointment, AppointmentSlot newSlot);

    /**
     * Retrieves a list of available appointment slots.
     *
     * @return A {@link List} of {@link AppointmentSlot} objects that are available for booking.
     */
    List<AppointmentSlot> viewAvailableAppointment();
}