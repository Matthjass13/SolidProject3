package users;

/**
 * This class represents an account for using our app.
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public abstract class User {

    private String username;
    private String password;
    private String mail;
    private String phone;
    private String address;

    public User(String username, String password, String mail, String phone, String address) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.phone = phone;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getMail() {
        return mail;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }

}