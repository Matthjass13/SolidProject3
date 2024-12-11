package requests;

import graphStructure.Network;
import screens.Screen;
import screens.ServerScreen;

/**
 * @author Sara Pereira De Pina
 * @since 01.12.2024
 */
public abstract class Handler {
    Handler successor;


    Network network;


    ServerScreen serverScreen;

    public Handler(Network network, ServerScreen serverScreen) {
        this.network = network;
        this.serverScreen = serverScreen;
    }
    public void setSuccessor(Handler successor){
        this.successor = successor;
    };
    public abstract String processRequest(UserRequest request);

}
