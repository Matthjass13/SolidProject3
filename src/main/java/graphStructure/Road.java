package graphStructure;

import graphStructure.nodes.Node;

/**
 * Edges of the graph
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
        return "Road to " + destination + ". Cost : " + cost;
    }

}
