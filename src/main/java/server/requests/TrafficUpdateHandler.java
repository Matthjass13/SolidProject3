package server.requests;

import server.graphStructure.Network;
import server.ServerScreen;

/**
 * Handles the road cost updates by the admin users
 * @see Handler
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class TrafficUpdateHandler extends Handler {

    public TrafficUpdateHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Traffic update";
    }

    /**
     * We return the new cost to the client
     * even though it is useless here...
     * @param request request to do
     * @return new cost
     */
    @Override
    public String doRequest(UserRequest request) {

        String node1 = request.getItem(0);
        String node2 = request.getItem(1);
        String cost = request.getItem(2);

        int node1ID = network.getIDByName(node1);
        int node2ID = network.getIDByName(node2);

        serverScreen.setRoadCost(node1ID, node2ID, Integer.parseInt(cost));
        serverScreen.repaint();

        return cost;
    }

}
