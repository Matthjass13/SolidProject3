package server.requests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import server.graphStructure.Network;
import server.ServerScreen;

import java.io.FileReader;
import java.io.IOException;

/**
 * Checks if a user account exists in the json file
 * with a given username and password.
 * @see Handler
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class UserCheckHandler extends Handler {

    public UserCheckHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "User check";
    }

    @Override
    public String doRequest(UserRequest request) {
        String username = request.getItem(0);
        String password = request.getItem(1);
        if(checkUser(username, password, true))
            return "admin";
        if(checkUser(username, password, false))
            return "end";
        return "false";
    }

    /**
     * Concrete checking method.
     * ChatGPT generated.
     * @param username username
     * @param password password
     * @param admin admin or not
     * @return true if the user exists
     */
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
            System.err.println("Error when reading file " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("JSON parsing error : " + e.getMessage());
        }

        return false;

    }

}