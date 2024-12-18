package server.users.adminUsers;

import server.users.User;
import server.users.UserCreator;

/**
 * @see UserCreator
 * @see AdminUser
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class AdminUserCreator extends UserCreator {
    public void createUser(String username, String password, String mail, String phone, String address) {
        User adminUser = new AdminUser(username, password, mail, phone, address);
        String fileName = "src/main/java/server/users/adminUsers/adminUsers.json";
        save(adminUser, fileName);
    }

}