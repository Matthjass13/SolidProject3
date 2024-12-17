package server.users.adminUsers;

import server.users.User;
import server.users.UserCreator;

/**
 * @see AdminUser
 * @see UserCreator
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class AdminUserCreator extends UserCreator {
    public User createUser(String username, String password, String mail, String phone, String address) {
        User adminUser = new AdminUser(username, password, mail, phone, address);
        String fileName = "src/main/java/server/users/adminUsers/adminUsers.json";
        saveToJson(adminUser, fileName);
        return adminUser;
    }

}
