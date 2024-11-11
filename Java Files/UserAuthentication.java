public interface UserAuthentication {
    boolean login(String hospitalID, String password);
    void changePassword(String hospitalID, String oldPassword, String newPassword);
    void forgotPassword(String hospitalID);
    boolean logOut(String hospitalID);
}
