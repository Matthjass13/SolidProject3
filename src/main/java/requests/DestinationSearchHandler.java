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

    public String processRequest(UserRequest request){
        if(request.isAbout(type)){

            Dijkstra dijkstra = new Dijkstra(network);
            dijkstra.computeShortestPaths();

            String purpose = request.getPurpose();
            String node1 = purpose.split(" : ")[1];
            String node2 = purpose.split(" : ")[2];

            System.out.println(node1);
            System.out.println(node2);

            int node1ID = network.getIDByName(node1);
            int node2ID = network.getIDByName(node2);

            Path shortestPathToDisplay = dijkstra.getShortestPaths()[node1ID][node2ID];

            serverScreen.setShortestPathToDisplay(shortestPathToDisplay);
            serverScreen.repaint();

            return shortestPathToDisplay.toString();

        }
        else{
            if(successor != null){
                successor.processRequest(request);
            }
        }

        return "";
    }

}
