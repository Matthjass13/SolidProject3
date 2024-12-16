package client.panels;

import client.Client;
import client.ui.*;
import server.users.adminUsers.AdminUserCreator;
import server.users.endUsers.EndUserCreator;
import server.users.UserCreator;

import javax.swing.*;


/**
 * This class will display a registration screen,
 * for the user who has no account yet.
 * @see ClientPanel
 * @author Sara Pereira
 * @since 09.12.2024
 */
public class RegistrationClientPanel extends ClientPanel implements ClientState {


    private TextField username;
    private PasswordField password;
    private TextField mail;
    private TextField phone;
    private TextField address;
    private JCheckBox admin;
    private Button signUp;
    private Button login;

    private Label message;


    public RegistrationClientPanel(Client client) {
        super(client);

        super.drawTitle("Registration");


        Rectangle connectionForm = new Rectangle(30, 110, 500, 300, this);

        username = new TextField("Username", 0, 0, connectionForm);
        password = new PasswordField("Password", 0, 50, connectionForm);
        mail = new TextField("Mail", 0, 100, connectionForm);
        phone = new TextField("Phone", 0, 150, connectionForm);
        address = new TextField("Address", 0, 200, connectionForm);

        new Label("Admin", 220, 0, 100, connectionForm);
        admin = new JCheckBox();
        admin.setBounds(310, 20, 25, 25);
        connectionForm.add(admin);

        signUp = new Button("Sign up", 240, 180, connectionForm);
        signUp.addActionListener(e -> {

            if(checkUser(username.getText(), password.getText(), admin.isSelected())) {
                message.setText("You already have an account. Please log in.");
            } else {
                createUser(username.getText(),
                        password.getText(),
                        mail.getText(),
                        phone.getText(),
                        address.getText(),
                        admin.isSelected());
                if(admin.isSelected())
                    logIn(true);
                else
                    logIn(false);
            }

        });

        login = new Button("Login",
                connectionForm.getX() + connectionForm.getWidth() + 20,
                connectionForm.getY() + signUp.getY()-20, this);
        login.addActionListener(e -> {
            goToConnection();
        });

        message = new Label("", 30, 350, 500, connectionForm);

        revalidate();
        repaint();

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