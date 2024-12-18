package server.requests;

import client.ui.Car;
import server.ServerScreen;
import server.graphStructure.Network;

/**
 * Handles the car animation on a path in the graph.
 * @see Handler
 * @see Car
 * @author Matthias Gaillard
 * @since 01.12.2024
 */
public class CarAnimationHandler extends Handler {

    public CarAnimationHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Car animation";
    }

    /**
     * We do not need to return anything to the client for this handler.
     * @param request request to do
     * @return an empty String
     */
    @Override
    public String doRequest(UserRequest request) {
        serverScreen.animateCar();
        return "";
    }

}