package requests;

import graphStructure.Network;
import screens.ServerScreen;

/**
 * Abstract that will handle a request received by the server.
 * @author Sara Pereira
 * @since 01.12.2024
 */
public abstract class Handler {

    protected Network network;
    protected Handler successor;
    protected ServerScreen serverScreen;
    protected String type;

    public Handler(Network network, ServerScreen serverScreen) {
        this.network = network;
        this.serverScreen = serverScreen;
    }
    public void setSuccessor(Handler successor){
        this.successor = successor;
    };
    public abstract String processRequest(UserRequest request);

}
