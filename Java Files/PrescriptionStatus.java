package OOPProject;

/**
 * Represents the status of a prescription in the system.
 * 
 * <p>This enumeration is used to track whether a prescription is yet to be fulfilled or has already been dispensed.</p>
 */
public enum PrescriptionStatus {
    /**
     * Indicates that the prescription is pending and has not yet been dispensed.
     */
    Pending,

    /**
     * Indicates that the prescription has been dispensed to the patient.
     */
    Dispensed
}