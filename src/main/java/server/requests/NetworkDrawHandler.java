package server.requests;

import server.graphStructure.Network;
import server.ServerScreen;

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

    @Override
    public String doRequest(UserRequest request) {
        return null;
    }


}
