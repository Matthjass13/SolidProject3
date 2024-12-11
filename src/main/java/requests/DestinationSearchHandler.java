package requests;


import algorithms.Dijkstra;
import graphStructure.Network;
import graphStructure.Path;
import screens.ServerScreen;

import java.awt.*;

/**
 * Handles the search of the shortest path between two nodes
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public class DestinationSearchHandler extends Handler {
    final String allow = "Destination Search"; /* for the exemple i put some purpose
    but instead we can implement for exemple action/event*/


    public DestinationSearchHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
    }

    public String processRequest(UserRequest request){
        if(request.getPurpose().contains(allow)){

            Dijkstra dijkstra = new Dijkstra(network);
            dijkstra.computeShortestPaths();

            String nodes = request.getPurpose().substring(19);
            String node1 = nodes.split(" ")[0];
            String node2 = nodes.split(" ")[1];

            System.out.println(node1);
            System.out.println(node2);

            int node1ID = network.getNodesDirectory().get(node1);
            int node2ID = network.getNodesDirectory().get(node2);

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
