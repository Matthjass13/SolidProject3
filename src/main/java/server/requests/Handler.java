package server.requests;

import server.graphStructure.Network;
import server.ServerScreen;

/**
 * Abstract class that will handle a request received by the server.
 * It implements both the chain of responsibility pattern
 * and the template pattern.
 * @author Sara Pereira
 * @since 01.12.2024
 */
public abstract class Handler {


    protected Network network;
    protected ServerScreen serverScreen;
    protected String type;
    protected Handler successor;

    public Handler(Network network, ServerScreen serverScreen) {
        this.network = network;
        this.serverScreen = serverScreen;
    }
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    /**
     * Template method for the template pattern.
     * It stays the same in subclasses,
     * but it calls the other method doRequest(),
     * which will be specified in each subclass.
     * @param request request to process
     * @return some String for the client
     */
    public String processRequest(UserRequest request) {
        if(request.getPurpose().equals(type))
            return doRequest(request);
        else {
            if(successor != null)
                return successor.processRequest(request);
        }
        return "";
    }

    /**
     * Will actually do the request.
     * @param request request to do
     * @return some String for the client
     */
    public abstract String doRequest(UserRequest request);

}