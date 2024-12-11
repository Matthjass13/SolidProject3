package requests;

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
        if(request.getPurpose().equals(allow)){
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
