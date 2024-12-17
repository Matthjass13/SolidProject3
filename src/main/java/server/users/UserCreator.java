package server.users;

import com.google.gson.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class allows to create users.
 * It implements the abstract factory pattern.
 * @see User
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public abstract class UserCreator {
    public abstract User createUser(String username, String password, String mail, String phone, String address);

    /**
     * Save a new user into a json file
     * Chat GPT generated
     * @param user
     * @param filePath
     */
    public void saveToJson(User user, String filePath) {

        try {

            FileReader reader = new FileReader(filePath);
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();

            if (!jsonObject.has("users") || !jsonObject.get("users").isJsonArray())
                jsonObject.add("users", new JsonArray());

            JsonArray usersArray = jsonObject.getAsJsonArray("users");

            JsonObject newUser = new JsonObject();
            newUser.addProperty("username", user.getUsername());
            newUser.addProperty("password", user.getPassword());
            newUser.addProperty("mail", user.getMail());
            newUser.addProperty("phone", user.getPhone());
            newUser.addProperty("address", user.getAddress());

            usersArray.add(newUser);

            FileWriter writer = new FileWriter(filePath);
            writer.write(jsonObject.toString());
            writer.close();

            System.out.println("Utilisateur ajouté avec succès !");

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture ou de l'écriture du fichier : " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Erreur de parsing JSON : " + e.getMessage());
        }

    }


}