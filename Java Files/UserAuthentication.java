package OOPProject;

/**
 * Interface defining user authentication operations for the hospital management system.
 * Provides methods for login, password management, and logout functionalities.
 */
public interface UserAuthentication {

    /**
     * Logs in a user with the specified hospital ID and password.
     *
     * @param hospitalID the hospital ID of the user.
     * @param password   the password of the user.
     * @return {@code true} if login is successful, {@code false} otherwise.
     */
    boolean login(String hospitalID, String password);

    /**
     * Changes the password for the user with the specified hospital ID.
     *
     * @param hospitalID  the hospital ID of the user.
     * @param oldPassword the current password of the user.
     * @param newPassword the new password to be set.
     */
    void changePassword(String hospitalID, String oldPassword, String newPassword);

    /**
     * Handles the "forgot password" process for the user with the specified hospital ID.
     * Typically involves sending a password reset link or providing a mechanism for recovery.
     *
     * @param hospitalID the hospital ID of the user.
     */
    void forgotPassword(String hospitalID);

    /**
     * Logs out the user with the specified hospital ID.
     *
     * @param hospitalID the hospital ID of the user.
     * @return {@code true} if logout is successful, {@code false} otherwise.
     */
    boolean logOut(String hospitalID);
}