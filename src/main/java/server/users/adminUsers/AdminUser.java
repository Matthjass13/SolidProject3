package server.users.adminUsers;

import server.users.User;

/**
 * This class represents an admin account.
 * It allows for both research and update queries.
 * @see User
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class AdminUser extends User {
    public AdminUser(String username, String password, String mail, String phone, String address) {
        super(username, password, mail, phone, address);
    }

}
