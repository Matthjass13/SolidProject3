package graphStructure;


import java.util.ArrayList;

/**
 * This class will be used as an abstract data structure
 * to store a collection of roads.
 * @see Road
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public abstract class Tree {

    protected ArrayList<Road> roads;



    public Tree() {
        roads = new ArrayList<>();
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public void add(Road road) {
        roads.add(road);
    }

    public void removeRoad(int i) {
        roads.remove(i);
    }
    public void clear() {
        roads.clear();
    }
    public boolean isEmpty() {
        return roads.isEmpty();
    }
    public abstract String toString();

    public int getSize() {
        return roads.size();
    }

}