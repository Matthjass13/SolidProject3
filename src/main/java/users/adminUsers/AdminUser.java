package users.adminUsers;

import users.User;

/**
 * This class represents an account for using our app.
 * It allows for both search and update queries.
 * @see User
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public class AdminUser extends User {
    public AdminUser(String username, String password, String mail, String phone, String address) {
        super(username, password, mail, phone, address);
    }

}
