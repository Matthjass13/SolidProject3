package server.graphStructure;

/**
 * Edges of the graph
 * Root node is not stored in this class,
 * but will instead be stored in specific road structures {@link Path} and {@link Star}
 * @see Node
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public class Road {

    private final Node destination;
    private int cost;

    public Road(Node destination, int cost) {
        this.destination = destination;
        this.cost = cost;
    }

    public Node getDestination() {
        return destination;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public String toString() {
        return "Road to " + destination.getName() + ". Cost : " + cost;
    }

}
