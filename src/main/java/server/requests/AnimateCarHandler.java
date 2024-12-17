package server.requests;

import server.ServerScreen;
import server.algorithms.Dijkstra;
import server.graphStructure.Network;
import server.graphStructure.Path;

/**
 * Handles the Car animation on a shortest Path in the graph.
 * @see Handler
 * @author Matthias Gaillard
 * @since 01.12.2024
 */
public class AnimateCarHandler extends Handler {

    public AnimateCarHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Animate car";
    }

    @Override
    public String doRequest(UserRequest request) {
        serverScreen.animateCar();
        return "";
    }

}
