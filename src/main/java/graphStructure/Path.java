package graphStructure;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class represents a continuous path in the network.
 * It consists of adjacent roads putted together.
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public class Path {

    private ArrayList<Road> roads;
    private int cost = 0;

    public Path() {
        roads = new ArrayList<>();
    }

    public int getCost() {
        return cost;
    }

    public void concat(Path path2) {
        if(!path2.isEmpty())
            roads.addAll(path2.roads);
        cost+=path2.cost;
    }
    public void add(Road road) {
        roads.add(road);
        cost+=road.getCost();
    }
    public void removeLastRoad() {
        if(!isEmpty()) {
            cost-=roads.getLast().getCost();
            roads.removeLast();
        }
    }
    public void delete() {
        while(!isEmpty()) {
            removeLastRoad();
        }
    }

    /**
     * This method is used in Dijkstra algorithm
     * to redefine the shortest path.
     * @param path2
     * @param road
     */

    public void reconstruct(Path path2, Road road) {
        delete();
        concat(path2);
        add(road);
    }

    public boolean isEmpty() {
        return roads.isEmpty();
    }


    public String toString() {
        String string = "";
        if(roads.isEmpty())
            return "empty";
        else
            for (Road road : roads)
                string += " -> " + road.getDestination().getName();

        string += ". Total cost : " + cost;
        return string;

    }

}
