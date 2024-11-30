package graphStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will store snapshots of the state
 * of a network, to retrieve them later.
 * It is a participant of the memento pattern.
 * @see Network
 * @author Matthias Gaillard
 * @since 30.11.2024
 */
public class NetworkCaretaker {

    private Network network;
    private List<Network.Memento> history = new ArrayList<Network.Memento>();

    public NetworkCaretaker(Network network) {
        this.network = network;
    }

    public void save() {
        history.add(network.save());
    }
    public void undo(){
        (history.remove(history.size()-1)).restore();
    }

}
