package requests;

import algorithms.Dijkstra;
import graphStructure.Network;
import screens.ServerScreen;

/**
 * Handle the road cost changes by the admin users
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public class TrafficUpdateHandler extends Handler{
    final String allow = "Traffic Update";

    public TrafficUpdateHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
    }

    public String processRequest(UserRequest request){
        if(request.getPurpose().contains(allow)){


            String nodes = request.getPurpose().substring(15);
            String node1 = nodes.split(" ")[0];
            String node2 = nodes.split(" ")[1];
            String cost = nodes.split(" ")[2];

            System.out.println(node1);
            System.out.println(node2);
            System.out.println(cost);

            int node1ID = network.getNodesDirectory().get(node1);
            int node2ID = network.getNodesDirectory().get(node2);




            serverScreen.setRoadCost(node1ID, node2ID, Integer.parseInt(cost));
            serverScreen.repaint();




            System.out.println("Traffic Update Handler will execute the request : "+allow);
        }
        else{
            if(successor != null){
                successor.processRequest(request);
            }
        }


        return "";
    }
}
