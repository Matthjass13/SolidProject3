package requests;

import algorithms.Dijkstra;
import graphStructure.Network;
import graphStructure.Path;
import screens.ServerScreen;

/**
 * Handles the search of the shortest path between two nodes
 * @see Handler
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class DestinationSearchHandler extends Handler {

    public DestinationSearchHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Destination Search";
    }

    @Override
    public String doRequest(UserRequest request) {
        Dijkstra dijkstra = new Dijkstra(network);
        dijkstra.computeShortestPaths();

        String node1 = request.getItem(0);
        String node2 = request.getItem(1);

        int node1ID = network.getIDByName(node1);
        int node2ID = network.getIDByName(node2);

        Path shortestPathToDisplay = dijkstra.getShortestPaths()[node1ID][node2ID];

        serverScreen.setShortestPathToDisplay(shortestPathToDisplay);
        serverScreen.repaint();

        return shortestPathToDisplay.toString();
    }

}
