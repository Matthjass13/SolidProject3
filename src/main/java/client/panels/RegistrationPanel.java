package client.panels;

import client.Client;
import client.ui.*;

import javax.swing.*;

/**
 * This class will display a registration screen,
 * for the user who has no account yet.
 * @see ClientPanel
 * @author Sara Pereira
 * @since 09.12.2024
 */
public class RegistrationPanel extends ClientPanel implements ClientState {

    private final TextField username;
    private final PasswordField password;
    private final TextField mail;
    private final TextField phone;
    private final TextField address;
    private final Label message;
    private final JCheckBox admin;

    public RegistrationPanel(Client client) {
        super(client);
        super.drawTitle("Registration");

        Rectangle connectionForm = new Rectangle(30, 110, 500, 300, this);

        username = new TextField("Username", 0, 0, connectionForm);
        password = new PasswordField("Password", 0, 50, connectionForm);
        mail = new TextField("Mail", 0, 100, connectionForm);
        phone = new TextField("Phone", 0, 150, connectionForm);
        address = new TextField("Address", 0, 200, connectionForm);
        message = new Label("Hello", 0, 240, 500, connectionForm);

        new Label("Admin", 220, 0, 100, connectionForm);
        admin = new JCheckBox();
        admin.setBounds(310, 20, 25, 25);
        connectionForm.add(admin);

        Button signUp = new Button("Sign up", 240, 180, connectionForm);
        signUp.addActionListener(e ->
                sendRequest("User check : " + username.getText() + " : " + password.getText()));

        Button login = new Button("Login",
                connectionForm.getX() + connectionForm.getWidth() + 20,
                connectionForm.getY() + signUp.getY() - 20, this);
        login.addActionListener(e -> goToConnection());

        revalidate();
        repaint();

    }

    @Override
    public void handleRequestBack(String response) {
        switch(response) {
            case "false" :
                sendRequest("User creation : "
                    + username.getText() + " : "
                    + password.getText() + " : "
                    + mail.getText() + " : "
                    + phone.getText() + " : "
                    + address.getText() + " : "
                    + admin.isSelected());
                goToConnection();
                break;
            case "admin" :
            case "end" :
                message.setText("You already have an account. Please log in.");
        }
    }

}