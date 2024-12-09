package users.adminUsers;

import users.User;
import users.UserCreator;

/**
 * @see AdminUser
 * @see UserCreator
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public class AdminUserCreator extends UserCreator {
    public User createUser(String username, String password, String mail, String phone, String address) {
        User adminUser = new AdminUser(username, password, mail, phone, address);
        String fileName = "src/main/java/users/adminUsers/adminUsers.json";
        saveToJson(adminUser, fileName);
        return adminUser;
    }

}
