package client.panels;

import javax.swing.*;

import client.Client;
import client.ui.Title;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class will display a generic JPanel to the client.
 * Subclasses will define the specific of each screen.
 * @see RegistrationClientPanel
 * @see ConnectionClientPanel
 * @see AppClientPanel
 * @author Sara Pereira
 * @since 09.12.2024
 */

public class ClientPanel extends JPanel implements ClientState {

    protected Client client;

    protected Title titleLabel;
    private static Color BACKGROUND_COLOR = Color.decode("#009DCF");


    public ClientPanel(Client client) {

        this.client = client;

        setBounds(0, 0, 800, 400);
        setLayout(null);
        setBackground(BACKGROUND_COLOR);

        revalidate();
        repaint();

        setVisible(true);
    }

    public ClientPanel() {

        setBounds(0, 0, 800, 400);
        setLayout(null);
        setBackground(BACKGROUND_COLOR);

        revalidate();
        repaint();

        setVisible(true);
    }


    public String getTitle() {
        return titleLabel.getText();
    }

    @Override
    public void logOut() {
        client.setCurrentState(client.getConnectionScreen());
    }

    @Override
    public void logIn(boolean admin) {
        if(admin)
            client.setCurrentState(client.getAdminAppScreen());
        else
            client.setCurrentState(client.getAppScreen());
    }

    @Override
    public void goToConnection() {
        client.setCurrentState(client.getConnectionScreen());
    }

    @Override
    public void goToRegistration() {
        client.setCurrentState(client.getRegistrationScreen());
    }


    public void drawTitle(String title) {
        titleLabel = new Title(title, this);
    }


    public boolean checkUser(String username, String password, boolean admin) {

        String filePath = "src/main/java/server/users/";
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
