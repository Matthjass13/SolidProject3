package requests;

import graphStructure.Network;
import screens.ServerScreen;

/**
 * Handles the history of the paths searched by the user beforehand
 * @see Handler
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class RouteHistoryHandler extends Handler {

    public RouteHistoryHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Route History";
    }

    public String processRequest(UserRequest request){
        if(request.isAbout(type)){


        }
        else{
            if(successor != null){
                successor.processRequest(request);
            }
        }

        return "";
    }
}
