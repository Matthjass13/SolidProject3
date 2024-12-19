package client.panels;

import client.Client;
import client.ui.*;

/**
 * This class will display a connection screen
 * for the user who already has an account.
 * @see ClientPanel
 * @author Sara Pereira
 * @since 09.12.2024
 */
public class ConnectionPanel extends ClientPanel {

    private final TextField username;
    private final PasswordField password;
    private final Label message;

    public ConnectionPanel(Client client) {
        super(client);
        super.drawTitle("Connection");

        Rectangle connectionForm = new Rectangle(30, 100, 250, 220, this);

        username = new TextField("Enter username", 0, 0, connectionForm);
        password = new PasswordField("Enter password", 0, 50, connectionForm);
        message = new Label("", 0, 160, 500, connectionForm);

        Button login = new Button("Login", 0, 100, connectionForm);
        login.addActionListener(e -> sendRequest("User check : " + username.getText() + " : " + password.getText()));

        Button signUp = new Button("Sign up", 300, 200, this);
        signUp.addActionListener(e -> goToRegistration());

        revalidate();
        repaint();
    }

    @Override
    public void handleRequestBack(String response) {
        if(response.equals("admin"))
            logIn(true);
        else {
            if(response.equals("end")) {
                logIn(false);
            }
            else {
                message.setText("Wrong identifiers !");
            }
        }
    }

}