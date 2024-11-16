package OOPProject;

import java.io.*;
import java.util.ArrayList;

/**
 * The Administrator class represents an administrator user in the system.
 * It extends the User class and includes properties specific to an administrator, 
 * such as the admin's name and ID. The class also contains a helper method 
 * for finding a medication by name in the inventory.
 */
public class Administrator extends User {
    private String adminName;
    private int adminID;

    /**
     * Constructs an Administrator object with the specified details.
     * 
     * @param hospitalId The hospital ID associated with the administrator.
     * @param password The password of the administrator.
     * @param role The role of the administrator.
     * @param gender The gender of the administrator.
     * @param adminName The name of the administrator.
     * @param firstTimeLogin Flag indicating whether this is the first time the administrator is logging in.
     */
    public Administrator(String hospitalId, String password, Role role, Gender gender, 
                         String adminName, Boolean firstTimeLogin) {
        super(hospitalId, password, role, gender, firstTimeLogin);
        this.adminName = adminName;
    }

    /**
     * Gets the name of the administrator.
     * 
     * @return The admin's name.
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * Sets the name of the administrator.
     * 
     * @param adminName The new name of the administrator.
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

 
 
}
