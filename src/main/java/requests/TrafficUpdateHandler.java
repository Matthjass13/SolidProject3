package requests;

import graphStructure.Network;
import screens.ServerScreen;

/**
 * Handles the road cost changes by the admin users
 * @see Handler
 * @author Sara Pereira
 * @since 01.12.2024
 */
public class TrafficUpdateHandler extends Handler{

    public TrafficUpdateHandler(Network network, ServerScreen serverScreen) {
        super(network, serverScreen);
        type = "Traffic Update";
    }

    public String processRequest(UserRequest request){
        if(request.isAbout("Traffic Update")){


            String nodes = request.getPurpose().substring(15);
            String node1 = nodes.split(" ")[0];
            String node2 = nodes.split(" ")[1];
            String cost = nodes.split(" ")[2];

            System.out.println(node1);
            System.out.println(node2);
            System.out.println(cost);

            int node1ID = network.getIDByName(node1);
            int node2ID = network.getIDByName(node2);

            serverScreen.setRoadCost(node1ID, node2ID, Integer.parseInt(cost));
            serverScreen.repaint();

        }
        else{
            if(successor != null){
                successor.processRequest(request);
            }
        }


        return "";
    }
}
