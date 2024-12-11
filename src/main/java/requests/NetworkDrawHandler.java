package requests;


import graphStructure.Network;
import graphStructure.Path;
import graphStructure.Road;
import graphStructure.Star;
import graphStructure.nodes.Node;
import screens.ServerScreen;

import javax.swing.*;
import java.awt.*;

/**
 * NOT USED
 * Handles the drawing of the network
 * @author Sara Pereira De Pina
 * @since 10.12.2024
 */
public class NetworkDrawHandler extends Handler {


    final String allow = "Draw network"; /* for the exemple i put some purpose
    but instead we can implement for exemple action/event*/


    public NetworkDrawHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
    }

    public String processRequest(UserRequest request){
        if(request.getPurpose().equals(allow)){

        }
        else{
            if(successor != null){
                successor.processRequest(request);
            }
        }

        return "";
    }










}
