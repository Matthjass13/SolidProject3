package USERS;

public class EndUserCreator extends UserCreator {
    public User createUser(String mail, String number, String adress) {
        return new EndUser("Rights end user", mail, number, adress);
    }

}
