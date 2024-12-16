package server.graphStructure;

import java.util.ArrayList;

/**
 * This class will be used as an abstract data structure
 * to store a collection of roads.
 * Subclasses : {@link Path} and {@link Star}
 * @see Road
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public abstract class Tree {

    protected Node root;
    protected ArrayList<Road> roads;

    public Tree(Node root) {
        this.root = root;
        roads = new ArrayList<>();
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root=root;
    }
    public ArrayList<Road> getRoads() {
        return roads;
    }

    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }

    public int getSize() {
        return roads.size();
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

}
