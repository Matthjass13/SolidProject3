package requests;

import graphStructure.Network;
import screens.ServerScreen;

/**
 * Handles the history of the paths searched by the user beforehand
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public class RouteHistoryHandler extends Handler {
    final String allow = "Route History";

    public RouteHistoryHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
    }

    public String processRequest(UserRequest request){
        if(request.getPurpose().equals(allow)){
            System.out.println("Route History Handler will execute the request : " + allow);
        }
        else{
            if(successor != null){
                successor.processRequest(request);
            }
        }

        return "";
    }
}
