package screens;

import screens.ui.*;

/**
 * This class will display a connection screen
 * for the user who already has an account.
 * @see Screen
 * @author Sara Pereira
 * @since 09.12.2024
 */
public class ConnectionScreen extends Screen {



    private TextField username;
    private PasswordField password;
    private Label message;
    private Button login;
    private Button signUp;

    public ConnectionScreen(Client client) {
        super(client);

        super.drawTitle("Connection");


        Rectangle connectionForm = new Rectangle(30, 110, 250, 200, this);

        username = new TextField("Enter username", 0, 0, connectionForm);
        password = new PasswordField("Enter password", 0, 50, connectionForm);
        message = new Label("", 0, 75, 500, connectionForm);

        login = new Button("Login", 0, 100, connectionForm);
        login.addActionListener(e -> {
            boolean isAdmin = checkUser(username.getText(), password.getText(), true);
            if(isAdmin)
                logIn(true);
            else {
                boolean isEndUser = checkUser(username.getText(), password.getText(), false);
                if(isEndUser) {
                    logIn(false);
                }
                else {
                    message.setText("Wrong username or password, please try again !");
                }
            }
        });

        signUp = new Button("Sign up", 300, 220, this);
        signUp.addActionListener(e -> {
            goToRegistration();
        });

        revalidate();
        repaint();

    }

}




