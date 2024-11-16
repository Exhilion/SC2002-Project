package OOPProject;

import java.util.Date;

/**
 * The {@code Patient} class represents a patient in the hospital system. 
 * It extends the {@link User} class and includes attributes specific to a patient,
 * such as name, date of birth, gender, blood type, and contact details.
 */
public class Patient extends User {
    private String name;
    private Date dateOfBirth;
    private Gender gender;
    private BloodType bloodType;
    private String hospitalID;
    private String password;
    private Role role;
    private String phoneNumber;
    private String email;

    /**
     * Constructs a new {@code Patient} object with the specified details.
     *
     * @param hospitalID     The unique hospital ID for the patient.
     * @param password       The password for the patient's account.
     * @param role           The role of the user (e.g., Patient).
     * @param gender         The gender of the patient.
     * @param name           The full name of the patient.
     * @param dateOfBirth    The date of birth of the patient.
     * @param bloodType      The blood type of the patient.
     * @param phoneNumber    The contact number of the patient.
     * @param email          The email address of the patient.
     * @param firstTimeLogin A flag indicating if this is the patient's first login to the system.
     */
    public Patient(String hospitalID, String password, Role role, Gender gender, String name, Date dateOfBirth,
                   BloodType bloodType, String phoneNumber, String email, Boolean firstTimeLogin) {
        super(hospitalID, password, role, gender, firstTimeLogin);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.hospitalID = hospitalID;
        this.role = role;
    }

    /**
     * Gets the hospital ID of the patient.
     *
     * @return The hospital ID as a {@code String}.
     */
    public String getUsername() {
        return hospitalID;
    }

    /**
     * Gets the email address of the patient.
     *
     * @return The email address as a {@code String}.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the role of the patient.
     *
     * @return The {@link Role} of the patient.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Gets the name of the patient.
     *
     * @return The name as a {@code String}.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the patient.
     *
     * @param name The new name for the patient.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the date of birth of the patient.
     *
     * @return The date of birth as a {@link Date}.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the patient.
     *
     * @param dateOfBirth The new date of birth for the patient.
     */
    public void setDate(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the gender of the patient.
     *
     * @return The {@link Gender} of the patient.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender of the patient.
     *
     * @param gender The new gender for the patient.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Gets the blood type of the patient.
     *
     * @return The {@link BloodType} of the patient.
     */
    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     * Sets the blood type of the patient.
     *
     * @param bloodType The new blood type for the patient.
     */
    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * Gets the phone number of the patient.
     *
     * @return The phone number as a {@code String}.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}