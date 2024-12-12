package screens;

import screens.ui.Button;
import screens.ui.Label;
import screens.ui.TextField;
import users.adminUsers.AdminUserCreator;
import users.endUsers.EndUserCreator;
import users.UserCreator;

import javax.swing.*;


/**
 * This class will display a registration screen,
 * for the user who has no account yet.
 * @see Screen
 * @author Sara Pereira
 * @since 09.12.2024
 */
public class RegistrationScreen extends Screen {

    private TextField username;
    private TextField password;
    private TextField mail;
    private TextField phone;
    private TextField address;
    private JCheckBox admin;
    private Button signUp;
    private Button login;


    public RegistrationScreen() {
        super();

        super.drawTitle("Registration");

        username = new TextField("Username", 30, 100, this);
        password = new TextField("Password", 30, 150, this);
        mail = new TextField("Mail", 30, 200, this);
        phone = new TextField("Phone", 30, 250, this);
        address = new TextField("Address", 30, 300, this);

        new Label("Admin", 250, 100, 100, this);
        admin = new JCheckBox();
        admin.setBounds(320, 100, 25, 25);
        add(admin);

        signUp = new Button("Sign up", 260, 280, this);
        signUp.addActionListener(e -> {
            createUser(username.getText(),
                       password.getText(),
                       mail.getText(),
                       phone.getText(),
                       address.getText(),
                       admin.isSelected());
            if(admin.isSelected())
                changeScreen(new AdminAppScreen());
            else
                changeScreen(new AppScreen());
        });

        login = new Button("Login", 450, 280, this);
        login.addActionListener(e -> {
            changeScreen(new ConnectionScreen());
        });

    }

    public void createUser(String username, String password, String mail, String phone, String address, boolean admin) {
        UserCreator creator;
        if(admin)
            creator = new AdminUserCreator();
        else
            creator = new EndUserCreator();
        creator.createUser(username, password, mail, phone, address);

    }

}
