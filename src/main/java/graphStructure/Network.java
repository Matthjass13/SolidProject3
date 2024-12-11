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


        // New version
        Node[] nodes = new Node[14];
        nodes[0] = new Node("Eiffel tower", 318, 480, 0);
        nodes[1] = new Node("Arc de Triomphe", 293, 346, 1);
        nodes[2] = new Node("Place Concorde", 400, 378, 2);
        nodes[3] = new Node("Orsay Museum", 484, 559, 3);
        nodes[4] = new Node("Catacombs", 490, 717, 4);
        nodes[5] = new Node("Pantheon", 582, 596, 5);
        nodes[6] = new Node("Louvre museum", 506, 439, 6);
        nodes[7] = new Node("Notre Dame", 615, 520, 7);
        nodes[8] = new Node("Père Lachaise Cemetery", 850, 443, 8);
        nodes[9] = new Node("Sacré Coeur", 600, 244, 9);
        nodes[10] = new Node("City of Science", 824, 168, 10);
        nodes[11] = new Node("Bastille", 720, 512, 11);
        nodes[12] = new Node("Saint-Suplice Church", 610, 767, 12);
        nodes[13] = new Node("Bercy", 845, 685, 13);

        int[][] costs = {
                {0, 1, 10}, {0, 2, 8}, {0, 3, 10}, {0, 6, 15},
                {1, 9, 30}, {1, 2, 10},
                {2, 9, 25}, {2, 6, 10},
                {3, 6, 10}, {3, 5, 5}, {3, 4, 15},
                {4, 12, 12}, {4, 5, 10},
                {5, 12, 14}, {5, 13, 20}, {5, 11, 7}, {5, 7, 5},
                {6, 9, 20}, {6, 7, 5}, {6, 11, 15},
                {7, 11, 10},
                {8, 11, 10}, {8, 13, 20}, {8, 10, 25},
                {9, 11, 25}, {9, 10, 18},
                {10, 11, 35},
                {11, 13, 18},
                {12, 13, 15}
        };


        // First version
        /*
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
        };*/

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

    public HashMap<String, Integer> getNodesDirectory() {
        return nodesDirectory;
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
