package screens;

import users.adminUsers.AdminUserCreator;
import users.endUsers.EndUserCreator;
import users.UserCreator;

import javax.swing.*;


/**
 * This class will display a registration screen,
 * for the user who has no account yet.
 * @see Screen
 * @since 09.12.2024
 * @author Sara Pereira De Pina
 */
public class RegistrationScreen extends Screen {
    public RegistrationScreen() {
        super();

        JTextField usernameField = new JTextField();
        usernameField.setBounds(200, 200, 100, 25);
        add(usernameField);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(200, 250, 100, 25);
        add(passwordField);

        JTextField mailField = new JTextField();
        mailField.setBounds(200, 300, 100, 25);
        add(mailField);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(200, 350, 100, 25);
        add(phoneField);

        JTextField addressField = new JTextField();
        addressField.setBounds(200, 400, 100, 25);
        add(addressField);

        JCheckBox adminCheckBox = new JCheckBox();
        adminCheckBox.setBounds(200, 450, 100, 25);
        add(adminCheckBox);


        JButton submitButton = new JButton("Sign in");
        submitButton.addActionListener(e -> {
            createUser(usernameField.getText(),
                       passwordField.getText(),
                       mailField.getText(),
                       phoneField.getText(),
                       addressField.getText(),
                       adminCheckBox.isSelected());
            changeScreen(new AppScreen());
        });
        submitButton.setBounds(200, 500, 100, 25);
        add(submitButton);

    }

    public void createUser(String username, String password, String mail, String phone, String address, boolean admin) {
        UserCreator creator;
        if(admin)
            creator = new AdminUserCreator();
        else
            creator = new EndUserCreator();
        creator.createUser(username, password, mail, phone, address);
    }


    public void createTextField(String name, int y) {
        JTextField usernameField = new JTextField();
        usernameField.setBounds(200, y, 100, 25);
        add(usernameField);
    }


}
