package requests;

import graphStructure.Network;
import screens.ServerScreen;

/**
 * Handles customization options for the user
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public class SettingsUpdateHandler extends Handler{
    final String allow = "Settings Update";

    public SettingsUpdateHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
    }

    public String processRequest(UserRequest request){
        if(request.getPurpose().equals(allow)){
            System.out.println("Settings Update Handler will execute the request : "+allow);
        }
        else{
            if(successor != null){
                successor.processRequest(request);
            }
        }

        return "";
    }
}
