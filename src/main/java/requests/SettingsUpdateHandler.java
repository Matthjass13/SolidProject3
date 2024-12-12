package requests;

import graphStructure.Network;
import screens.ServerScreen;

/**
 * Handles customization options for the user
 * @see Handler
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class SettingsUpdateHandler extends Handler{

    public SettingsUpdateHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Settings Update";
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
