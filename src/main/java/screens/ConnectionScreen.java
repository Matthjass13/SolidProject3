package screens;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import screens.ui.Button;
import screens.ui.Label;
import screens.ui.TextField;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class will display a connection screen
 * for the user who already has an account.
 * @see Screen
 * @since 09.12.2024
 * @author Sara Pereira De Pina
 */
public class ConnectionScreen extends Screen {

    private TextField username;
    private TextField password;
    private Label message;
    private Button submit;
    private Button signIn;

    public ConnectionScreen() {
        super();

        super.drawTitle("Connection");

        username = new TextField("Enter username", 200, 100, 100, 25, this);
        password = new TextField("Enter password", 200, 150, 100, 25, this);
        message = new Label("", 350, 500, 100, 25, 16, this);

        submit = new Button("Validate", 300, 200, this);
        submit.addActionListener(e -> {
            boolean isAdmin = checkUser(username.getText(), password.getText(), true);
            if(isAdmin)
                changeScreen(new AdminAppScreen());
            else {
                boolean isEndUser = checkUser(username.getText(), password.getText(), false);
                if(isEndUser)
                    changeScreen(new AppScreen());
                else {
                    message.setText("Wrong username or password, please try again !");
                }
            }
        });

        signIn = new Button("Sign in", 100, 200, this);
        signIn.addActionListener(e -> {
            changeScreen(new RegistrationScreen());
        });

    }

    public boolean checkUser(String username, String password, boolean admin) {

        String filePath = "src/main/java/users/";
        if(admin)
            filePath+="adminUsers/adminUsers.json";
        else
            filePath+="endUsers/endUsers.json";

        try {

            FileReader reader = new FileReader(filePath);
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();

            if (!jsonObject.has("users") || !jsonObject.get("users").isJsonArray()) {
                System.err.println("JSON file does not contain 'users' array.");
                return false;
            }

            JsonArray usersArray = jsonObject.getAsJsonArray("users");

            for (int i = 0; i < usersArray.size(); i++) {
                JsonObject user = usersArray.get(i).getAsJsonObject();
                if (user.get("username").getAsString().equals(username) &&
                        user.get("password").getAsString().equals(password)) {
                    return true;
                }
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Erreur de parsing JSON : " + e.getMessage());
        }

        return false;

    }


}




