package server.requests;

import server.algorithms.Dijkstra;
import server.graphStructure.Network;
import server.graphStructure.Path;
import server.ServerScreen;

/**
 * Handles the search of the shortest path between two nodes,
 * using Dijkstra algorithm.
 * @see Handler
 * @see Dijkstra
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class DijkstraHandler extends Handler {

    public DijkstraHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Dijkstra";
    }

    @Override
    public String doRequest(UserRequest request) {
        String node1 = request.getItem(0);
        String node2 = request.getItem(1);

        int node1ID = network.getIDByName(node1);
        int node2ID = network.getIDByName(node2);

        Dijkstra dijkstra = new Dijkstra(network);
        Path shortestPathToDisplay = dijkstra.findPath(node1ID, node2ID);

        serverScreen.setPathToDisplay(shortestPathToDisplay);
        serverScreen.repaint();

        return shortestPathToDisplay.toString();
    }

}