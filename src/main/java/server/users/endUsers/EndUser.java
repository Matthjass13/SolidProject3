package server.users.endUsers;

import server.users.User;

/**
 * This class represents an end user account.
 * It only allows search queries.
 * @see User
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class EndUser extends User {
    public EndUser(String username, String password, String mail, String phone, String address) {
        super(username, password, mail, phone, address);
    }

}
