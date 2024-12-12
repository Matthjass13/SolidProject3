package screens;

import javax.swing.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import screens.ui.Title;
import screens.ui.Button;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class will display a generic JFrame to the client.
 * Subclasses will define the specific of each screen.
 * @see RegistrationScreen
 * @see ConnectionScreen
 * @see AppScreen
 * @author Sara Pereira
 * @since 09.12.2024
 */

public class Screen extends JFrame {

    protected Title titleLabel;
    private static Color BACKGROUND_COLOR = Color.decode("#BBD2EC");

    public Screen() {
        setBounds(0, 0, 800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        getContentPane().setBackground(BACKGROUND_COLOR);

        revalidate();
        repaint();

        setVisible(true);
    }

    public void changeScreen(Screen s) {
        s.setVisible(true);
        int x = getX();
        int y = getY();
        int width = getWidth();
        int height = getHeight();
        s.setBounds(x, y, width, height);
        dispose();
    }

    public void drawTitle(String title) {
        titleLabel = new Title(title, this);
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
