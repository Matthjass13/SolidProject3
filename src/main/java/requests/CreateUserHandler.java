package requests;

import algorithms.Dijkstra;
import graphStructure.Network;
import graphStructure.Path;
import screens.ServerScreen;

/**
 * Handles the search of the shortest path between two nodes
 * @see Handler
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class CreateUserHandler extends Handler {

    public CreateUserHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Create User";
    }

    @Override
    public String processRequest(UserRequest request) {
        return null;
    }


    /*
    @Override
    public String doRequest(UserRequest request) {
        return "";
    }*/


}
