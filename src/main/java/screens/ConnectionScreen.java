package screens;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

    public ConnectionScreen() {
        super();

        JTextField usernameField = new JTextField();
        usernameField.setBounds(200, 200, 100, 25);
        add(usernameField);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(200, 400, 100, 25);
        add(passwordField);

        JButton submitButton = new JButton("Validate");
        submitButton.addActionListener(e -> {
            boolean b = checkUser(usernameField.getText(), passwordField.getText(), "src/main/java/USERS/adminUsers.json");
            System.out.println(usernameField.getText() +  " " + passwordField.getText());
            System.out.println(b);
        });
        submitButton.setBounds(200, 500, 100, 25);
        add(submitButton);


        JButton signInButton = new JButton("Sign in");
        signInButton.addActionListener(e -> {
            changeScreen(new RegistrationScreen());
        });
        signInButton.setBounds(100, 500, 100, 25);
        add(signInButton);

    }

    public boolean checkUser(String username, String password, String fileName) {


        try (FileReader reader = new FileReader(fileName)) {
            // Lire le JSON comme un objet
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            // Extraire les valeurs de "username" et "password"
            String storedUsername = jsonObject.get("username").getAsString();
            String storedPassword = jsonObject.get("password").getAsString();
            // Vérifier si le nom d'utilisateur et le mot de passe correspondent
            return storedUsername.equals(username) && storedPassword.equals(password);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de lecture du fichier JSON.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;

        }

    }


}




