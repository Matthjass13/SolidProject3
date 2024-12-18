package server.requests;

import server.ServerScreen;
import server.algorithms.Dijkstra;
import server.algorithms.Hamilton;
import server.graphStructure.Network;
import server.graphStructure.Path;

/**
 * Handles the search of an Hamilton Path path between two nodes
 * @see Handler
 * @author Matthias Gaillard
 * @since 18.12.2024
 */
public class HamiltonHandler extends Handler {

    public HamiltonHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Hamilton Search";
    }

    @Override
    public String doRequest(UserRequest request) {

        System.out.println("*" + request.getPurpose() + "*");
        System.out.println("*" + request.getItem(0) + "*");
        System.out.println("*" + request.getItem(1) + "*");


        Hamilton hamilton = new Hamilton(network);


        String node1 = request.getItem(0);
        String node2 = request.getItem(1);

        int node1ID = network.getIDByName(node1);
        int node2ID = network.getIDByName(node2);

        Path shortestPathToDisplay = hamilton.findHamiltonianPath(node1ID, node2ID);
        System.out.println(node1ID + " et " + node2ID);

        serverScreen.setShortestPathToDisplay(shortestPathToDisplay);
        serverScreen.repaint();

        return "";
    }

}
