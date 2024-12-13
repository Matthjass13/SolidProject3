package graphStructure;

import java.util.ArrayList;

/**
 * This class represents a continuous path in the network.
 * It consists of an ordered list of adjacent roads putted together.
 * @see Tree
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public class Path extends Tree {

    private int cost = 0;

    public Path(Node root) {
        super(root);
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
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

    /**
     * This method is used in Dijkstra algorithm
     * to redefine the shortest path.
     * @param path2 new path to use
     * @param road last road to add to the concatenation
     */
    public void rebuild(Path path2, Road road) {
        clear();
        concat(path2);
        add(road);
    }

    /**
     * This method adds path2 to the current path.
     * @param path2 second path to concatenate
     */
    private void concat(Path path2) {
        roads.addAll(path2.roads);
        cost+=path2.cost;
    }

    @Override
    public String toString() {
        if(isEmpty())
            return "Empty";
        String string = root.getName();
        for (Road road : roads)
            string += " : " + road.getDestination().getName();
        string += " : " + cost;
        return string;
    }


    /**
     * Inner class used to apply the memento pattern.
     */
    public class Memento {

        private Path path;
        private Node root;
        private ArrayList<Road> roads;
        private int cost;

        public Memento(Path path, Node root, ArrayList<Road> roads, int cost) {
            this.path = path;
            this.root = root;
            this.roads = roads;
            this.cost = cost;
        }

        public void restore() {
            path.setRoot(this.root);
            path.setRoads(this.roads);
            path.setCost(this.cost);
        }
    }
    public Path.Memento save() {
        return new Path.Memento(this, this.root, this.roads, this.cost);
    }



}