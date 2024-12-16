package server.requests;

import server.graphStructure.Network;
import server.ServerScreen;

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

    @Override
    public String doRequest(UserRequest request) {
        return null;
    }


}
