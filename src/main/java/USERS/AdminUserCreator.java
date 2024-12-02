package USERS;

public class AdminUserCreator extends UserCreator {
    public User createUser(String mail, String number, String adress) {
    return new AdminUser("Rights admin user", mail, number, adress);
}

}
