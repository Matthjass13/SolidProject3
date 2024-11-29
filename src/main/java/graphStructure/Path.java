package graphStructure;


/**
 * This class represents a continuous path in the network.
 * It consists of adjacent roads putted together.
 * @see Tree
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public class Path extends Tree {

    private int cost = 0;

    public Path() {
        super();
    }

    public int getCost() {
        return cost;
    }

    @Override
    public void add(Road road) {
        super.add(road);
        cost+=road.getCost();
    }
    @Override
    public void clear() {
        super.clear();
        cost = 0;
    }

    public void concat(Path path2) {
        if(!path2.isEmpty())
            roads.addAll(path2.roads);
        cost+=path2.cost;
    }

    /**
     * This method is used in Dijkstra algorithm
     * to redefine the shortest path.
     * @param path2 second path to concatenate
     * @param road last road to add to the concatenation
     */
    public void reconstruct(Path path2, Road road) {
        clear();
        concat(path2);
        add(road);
    }

    public String toString() {
        if(isEmpty())
            return "Empty";
        String string = "";
        for (Road road : roads)
            string += " -> " + road.getDestination().getName();
        string += ". Total cost : " + cost;
        return string;
    }

}
