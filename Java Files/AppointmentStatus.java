package OOPProject;

/**
 * Enum representing the various statuses an appointment can have.
 */
public enum AppointmentStatus {
    
    /**
     * Appointment is pending and has not yet been confirmed or cancelled.
     */
    Pending,
    
    /**
     * Appointment has been confirmed and is scheduled.
     */
    Confirmed,
    
    /**
     * Appointment has been cancelled and will not take place.
     */
    Cancelled,
    
    /**
     * Appointment has been completed successfully.
     */
    Completed
}
