package OOPProject;

/**
 * The UserAuthentication interface provides methods for handling user authentication
 * tasks such as logging in, changing passwords, and logging out.
 * It is intended to be implemented by classes that handle user authentication logic.
 */
public interface UserAuthentication {
	
	/**
     * Authenticates a user with their hospital ID and password.
     * 
     * @param hospitalID The hospital ID of the user attempting to log in.
     * @param password The password of the user attempting to log in.
     * @return True if the login is successful, otherwise false.
     */
	boolean login(String hospitalID, String password);
	
	/**
     * Changes the password for a user.
     * 
     * @param hospitalID The hospital ID of the user requesting the password change.
     * @param oldPassword The user's current password.
     * @param newPassword The new password that the user wants to set.
     */
    void changePassword(String hospitalID, String oldPassword, String newPassword);
    
    /**
     * Initiates the process for a user who has forgotten their password.
     * 
     * @param hospitalID The hospital ID of the user who has forgotten their password.
     */
    void forgotPassword(String hospitalID);
    
    /**
     * Logs out a user by their hospital ID.
     * 
     * @param hospitalID The hospital ID of the user who wants to log out.
     * @return True if the logout is successful, otherwise false.
     */
    boolean logOut(String hospitalID);
}
