package server.requests;

import server.ServerScreen;
import server.algorithms.Hamilton;
import server.graphStructure.Network;
import server.graphStructure.Path;

/**
 * Searches a Hamilton path between two nodes.
 * @see Handler
 * @see Hamilton
 * @author Matthias Gaillard
 * @since 18.12.2024
 */
public class HamiltonHandler extends Handler {

    public HamiltonHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Hamilton";
    }

    @Override
    public String doRequest(UserRequest request) {
        String node1 = request.getItem(0);
        String node2 = request.getItem(1);

        int node1ID = network.getIDByName(node1);
        int node2ID = network.getIDByName(node2);

        Hamilton hamilton = new Hamilton(network);
        Path shortestPathToDisplay = hamilton.findPath(node1ID, node2ID);

        serverScreen.setPathToDisplay(shortestPathToDisplay);
        serverScreen.repaint();

        return "";
    }

}
