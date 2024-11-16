package OOPProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

/**
 * Represents a user in the hospital management system.
 * A user can be a patient, doctor, pharmacist, or administrator, each having a hospital ID, password, role, gender, 
 * and a flag indicating if it's their first login.
 */
public class User {

    private String hospitalID;
    private String password;
    private Role role;
    private Gender gender;
    private Boolean firstTimeLogin;

    /**
     * Default constructor for creating an empty user instance.
     */
    public User() {
    }

    /**
     * Constructs a user with the specified hospital ID and password.
     *
     * @param username the hospital ID of the user.
     * @param password the password of the user.
     */
    public User(String username, String password) {
        this.hospitalID = username;
        this.password = password;
    }

    /**
     * Constructs a user with the specified details.
     *
     * @param hospitalID      the hospital ID of the user.
     * @param password        the password of the user.
     * @param role            the role of the user (e.g., patient, doctor, pharmacist, administrator).
     * @param gender          the gender of the user.
     * @param firstTimeLogin  a flag indicating if it is the user's first login.
     */
    public User(String hospitalID, String password, Role role, Gender gender, Boolean firstTimeLogin) {
        this.hospitalID = hospitalID;
        this.password = password;
        this.role = role;
        this.gender = gender;
        this.firstTimeLogin = firstTimeLogin;
    }

    /**
     * Gets the hospital ID of the user.
     *
     * @return the hospital ID.
     */
    public String getHospitalId() {
        return hospitalID;
    }

    /**
     * Sets the hospital ID of the user.
     *
     * @param hospitalID the hospital ID to set.
     */
    public void setHospitalId(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role of the user.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role to set.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the gender of the user.
     *
     * @return the gender of the user.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender the gender to set.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Gets the first-time login status of the user.
     *
     * @return {@code true} if it's the user's first login, {@code false} otherwise.
     */
    public Boolean getFirstTimeLogin() {
        return firstTimeLogin;
    }

    /**
     * Sets the first-time login status of the user.
     *
     * @param firstTimeLogin the first-time login status to set.
     */
    public void setFirstTimeLogin(Boolean firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }
}