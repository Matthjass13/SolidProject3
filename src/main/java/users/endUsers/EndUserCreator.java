package users.endUsers;

import users.User;
import users.UserCreator;

/**
 * @see EndUser
 * @see UserCreator
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class EndUserCreator extends UserCreator {
    public User createUser(String username, String password, String mail, String phone, String address) {
        User endUser = new EndUser(username, password, mail, phone, address);
        String fileName = "src/main/java/users/endUsers/endUsers.json";
        saveToJson(endUser, fileName);
        return endUser;
    }

}
