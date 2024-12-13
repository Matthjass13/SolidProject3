package requests;

import algorithms.Dijkstra;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import graphStructure.Network;
import graphStructure.Path;
import screens.ServerScreen;

import java.io.FileReader;
import java.io.IOException;

/**
 * Handles the search of the shortest path between two nodes
 * @see Handler
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class CheckUserHandler extends Handler {

    public CheckUserHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Check user";
    }

    @Override
    public String doRequest(UserRequest request) {
        return null;
    }



    /*
    @Override
    public String doRequest(UserRequest request) {


        String username = request.getItems().get(0);
        String password = request.getItems().get(1);
        String admin = request.getItems().get(2);

        String filePath = "src/main/java/users/";
        if(admin.equals("true"))
            filePath+="adminUsers/adminUsers.json";
        else
            filePath+="endUsers/endUsers.json";

        try {
            FileReader reader = new FileReader(filePath);
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();

            JsonArray usersArray = jsonObject.getAsJsonArray("users");

            for (int i = 0; i < usersArray.size(); i++) {
                JsonObject user = usersArray.get(i).getAsJsonObject();
                if (user.get("username").getAsString().equals(username) &&
                        user.get("password").getAsString().equals(password)) {
                    return "true";
                }
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Erreur de parsing JSON : " + e.getMessage());
        }

        return "false";


    }*/



}
