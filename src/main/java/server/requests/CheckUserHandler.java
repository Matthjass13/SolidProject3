package server.requests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import server.graphStructure.Network;
import server.ServerScreen;

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

        String username = request.getItem(0);
        String password = request.getItem(1);

        boolean isAdmin = checkUser(username, password, true);
        boolean isEndUser = checkUser(username, password, false);

        System.out.println(isAdmin);
        System.out.println(isEndUser);


        if(isAdmin) {
            System.out.println("admin");
            return "admin";
        }
        if(isEndUser)
            return "end";
        return "false";

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
