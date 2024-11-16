package OOPProject;

import java.util.List;

/**
 * Interface to manage doctor appointments.
 * Provides methods for setting availability, accepting or declining appointments,
 * and viewing the appointment schedule of a doctor.
 */
public interface DoctorAppointmentManagement {

    /**
     * Sets the available dates and times for a doctor's appointments.
     *
     * @param doctorID The unique identifier of the doctor.
     * @param schedule A list of available appointment slots for the doctor.
     */
    void setAvailableDates(String doctorID, List<AppointmentSlot> schedule);

    /**
     * Accepts an appointment request by its unique appointment ID.
     *
     * @param appointmentID The unique identifier of the appointment to be accepted.
     */
    void acceptAppointmentRequest(String appointmentID);

    /**
     * Declines an appointment request by its unique appointment ID.
     *
     * @param appointmentID The unique identifier of the appointment to be declined.
     */
    void declineAppointmentRequest(String appointmentID);

    /**
     * Views the current appointment schedule for a doctor.
     *
     * @param doctorID The unique identifier of the doctor.
     * @return A list of appointments scheduled for the doctor.
     */
    List<Appointment> viewAppointmentSchedule(String doctorID);
}
