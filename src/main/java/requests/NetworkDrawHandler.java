package requests;

import graphStructure.Network;
import screens.ServerScreen;

/**
 * NOT USED
 * Handles the drawing of the network
 * @author Sara Pereira
 * @since 10.12.2024
 */
public class NetworkDrawHandler extends Handler {


    public NetworkDrawHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Draw network";
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
