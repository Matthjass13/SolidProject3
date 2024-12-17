package server.requests;

import server.graphStructure.Network;
import server.ServerScreen;
import server.users.UserCreator;
import server.users.adminUsers.AdminUserCreator;
import server.users.endUsers.EndUserCreator;

/**
 * Handles the search of the shortest path between two nodes
 * @see Handler
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class CreateUserHandler extends Handler {

    public CreateUserHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Create user";
    }

    @Override
    public String doRequest(UserRequest request) {

        String username = request.getItem(0);

        String password = request.getItem(1);

        String mail = request.getItem(2);

        String phone = request.getItem(3);

        String adress = request.getItem(4);

        boolean admin = request.getItem(5).equals("true") ? true : false;


        createUser(username, password, mail, phone, adress, admin);


        return "";
    }


    public void createUser(String username, String password, String mail, String phone, String address, boolean admin) {
        UserCreator creator;
        if(admin)
            creator = new AdminUserCreator();
        else
            creator = new EndUserCreator();
        creator.createUser(username, password, mail, phone, address);
    }



}
