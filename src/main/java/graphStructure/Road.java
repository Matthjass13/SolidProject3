package graphStructure;

/**
 * Edges of the graph
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public class Road {

    private City destination;
    private int cost;

    /**
     * This field will be used to store
     * a reference to the next road
     * in the list of all roads
     * incident with a given vertex.
     */
    private Road next;

    public Road(City destination, int cost)
    {
        this.destination = destination;
        this.cost = cost;
        next = null;
    }
    public Road() {
        this(null, 0);
    }

    public Road getNext() {
        return next;
    }

    public void setNext(Road next) {
        this.next = next;
    }

    public City getDestination() {
        return destination;
    }

    public int getCost() {
        return cost;
    }

    public String toString() {
        return "Road to " + destination + ". Cost : " + cost;
    }

}
