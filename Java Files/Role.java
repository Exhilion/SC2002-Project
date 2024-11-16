package OOPProject;

/**
 * Represents the roles available in the hospital management system.
 * 
 * <p>This enumeration is used to define and differentiate the roles of various users in the system.</p>
 */
public enum Role {
    /**
     * Represents a patient role, who interacts with the system to schedule appointments,
     * view medical records, and manage personal information.
     */
    PATIENT,

    /**
     * Represents a doctor role, who manages patient medical records, diagnoses,
     * treatments, and appointments.
     */
    DOCTOR,

    /**
     * Represents a pharmacist role, who manages medication inventory, processes
     * prescriptions, and submits replenishment requests.
     */
    PHARMACIST,

    /**
     * Represents an administrator role, who manages hospital staff, oversees
     * operations, and handles user management.
     */
    ADMINISTRATOR
}