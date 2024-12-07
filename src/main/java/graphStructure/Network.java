package graphStructure;

import graphStructure.nodes.Node;

import java.util.HashMap;

/**
 * This class contains all data stored in the oriented graph representation of the network.
 * Memento pattern is used to keep track of different saves of the network (originator).
 * @see Star
 * @see NetworkCaretaker
 * @author Matthias Gaillard
 * @since 24.11.2024
 */

public class Network {


    /**
     * For each node, a list contains
     * all roads incident with it.
     */
    protected Star[] stars;

    /**
     * Number of nodes in the network
     */
    protected final int SIZE;

    /**
     * This field allows to quickly search the id
     * of a given node name.
     */
    HashMap<String, Integer> nodesDirectory;


    public Network(boolean oriented) {

        nodesDirectory = new HashMap<String, Integer>();

        Node[] nodes = new Node[6];
        nodes[0] = new Node("Saillon", 0, 0, 0);
        nodes[1] = new Node("Leytron", 300, 0, 1);
        nodes[2] = new Node("Riddes", 600, 600, 2);
        nodes[3] = new Node("Chamoson", 900, 0, 3);
        nodes[4] = new Node("Saint-Pierre-de-Clages", 900, 600, 4);
        nodes[5] = new Node("Ardon", 1200, 600, 5);

        int[][] costs = {
                {0, 1, 10}, {0, 2, 30},
                {1, 2, 5}, {1, 3, 15},
                {2, 4, 40},
                {3, 4, 20}, {3, 5, 60},
                {4, 5, 10}
        };

        /* int[][] costs = {
            {0, 1, 10}, {0, 2, 30},
            {1, 0, 10}, {1, 2, 5}, {1, 3, 15},
            {2, 0, 30}, {2, 1, 5}, {2, 4, 40},
            {3, 1, 15}, {3, 4, 20}, {3, 5, 60},
            {4, 2, 40}, {4, 3, 20}, {4, 5, 10},
            {5, 3, 60}, {5, 4, 10}
        };*/
        /*
        int[][] costs = {
            {0, 10, 30, 0, 0, 0},
            {10, 0, 5, 15, 0, 0},
            {30, 5, 0, 0, 40, 0},
            {0, 15, 0, 0, 20, 60},
            {0, 0, 40, 20, 0, 10},
            {0, 0, 0, 60, 10, 0}
        };*/

        SIZE = nodes.length;

        stars = new Star[SIZE];
        for (int i = 0; i < SIZE; i++) {
            nodesDirectory.put(nodes[i].getName(), nodes[i].getID());
            stars[i] = new Star(nodes[i]);
        }

        for (int roadIndex = 0; roadIndex < costs.length; roadIndex++)
            stars[costs[roadIndex][0]].add(new Road(nodes[costs[roadIndex][1]], costs[roadIndex][2]));


        // We add missing reverse edges in case of unoriented network
        if(!oriented) {
            for(Star star : stars) {
                for(Road road : star.getRoads()) {
                    if(!stars[road.getDestination().getID()].hasLeaf(star.getRoot()))
                        addRoad(road.getDestination().getID(), star.getRoot().getID(), road.getCost());
                }
            }
        }

    }

    /**
     * Network will be unoriented by default.
     */
    public Network() {
        this(false);
    }


    public int getSIZE() {
        return SIZE;
    }
    public Star[] getStars() {
        return stars;
    }
    public Star getStar(int index) {
        return stars[index];
    }
    public Node getNode(int index) {
        return stars[index].getRoot();
    }
    public int getIDByName(String name) {
        return nodesDirectory.get(name);
    }
    public void setStars(Star[] stars) {
        this.stars = stars;
    }

    /**
     * This method changes the cost of edge (i, j).
     * @param i start node index
     * @param j end node index
     * @param cost new cost
     */
    public void setCost(int i, int j, int cost) {
        for(Road road : stars[i].getRoads()) {
            if(road.getDestination().getID()==j) {
                road.setCost(cost);
                return;
            }
        }
        addRoad(i, j, cost);
    }
    public void addRoad(int i, int j, int cost) {
        stars[i].add(new Road(getNode(j), cost));
    }
    public void removeRoad(int i, int j) {
        for(int index = 0; index < stars[i].getSize(); ++index)
            if(stars[i].getRoads().get(index).getDestination().getID()==j)
                stars[i].removeRoad(index);
    }

    public String toString() {
        String string = "Stars of the networks : " + "\n\n";
        for (int i = 0; i < stars.length; i++)
            string += stars[i];
        string += "\n";
        return string;
    }


    /**
     * Inner class used to apply the memento pattern.
     */
    public class Memento {
        private Network network;
        private Star[] stars;
        public Memento(Network network, Star[] stars) {
            this.network = network;
            this.stars = stars;
        }
        public void restore() {
            network.setStars(this.stars);
        }
    }
    public Memento save() {
        return new Memento(this, this.stars);
    }


}
