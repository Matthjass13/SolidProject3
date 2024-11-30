package graphStructure;

import graphStructure.nodes.Node;

/**
 * This class contains all data stored
 * in the graph representation of the network.
 * Memento pattern is used to keep track of different saves of the network (originator).
 * @see Node
 * @see Star
 * @see NetworkCaretaker
 * @author Matthias Gaillard
 * @since 24.11.2024
 */

public class Network {

    /**
     * Number of nodes in the network
     */
    private final int SIZE;

    private Node[] nodes;

    /**
     * For each node, a list contains
     * all roads incident with it.
     */
    private Star[] stars;


    /**
     * @param nodes Array of Node to be added in the network
     * @param costs A non-zero costs[i][j] means
     *              there is an edge from nodes[i] to nodes[j] of that cost.
     */
    public Network(Node[] nodes, int[][] costs) {
        SIZE = nodes.length;

        this.nodes = nodes;

        stars = new Star[SIZE];
        for (int i = 0; i < SIZE; i++)
            stars[i] = new Star();

        for (int i = 0; i < costs.length; i++)
            for (int j = 0; j < costs[i].length; j++)
                if(costs[i][j]!=0)
                    stars[i].add(new Road(nodes[j], costs[i][j]));

    }


    public int getSIZE() {
        return SIZE;
    }
    public Node[] getNodes() {
        return nodes;
    }
    public Star[] getStars() {
        return stars;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public void setStars(Star[] stars) {
        this.stars = stars;
    }
    public String toString() {
        String string = "Stars of the networks : " + "\n\n";
        for (int i = 0; i < stars.length; i++)
            string += "Node of " + nodes[i].getName() + " : \n" + stars[i];
        string += "\n";
        return string;
    }



    /** This method changes the cost of edge (i, j) to cost.
     * @param i start node
     * @param j end node
     * @param cost new cost
     */
    public void setCost(int i, int j, int cost) {
        for(Road road : stars[i].getRoads()) {
            if(road.getDestination().getID()==j) {
                road.setCost(cost);
                return;
            }
        }
    }
    public void addRoad(int i, Node destination, int cost) {
        stars[i].add(new Road(destination, cost));
    }
    public void removeRoad(int i, int j) {
        for(int index = 0; index < stars[i].getSize(); ++index)
            if(stars[i].getRoads().get(index).getDestination().getID()==j)
                stars[i].removeRoad(index);
    }

    /**
     * Inner class used to apply the memento pattern.
     */
    public class Memento {
        private Network network;
        private Node[] nodes;
        private Star[] stars;
        public Memento(Network network, Node[] nodes, Star[] stars) {
            this.network = network;
            this.nodes = nodes;
            this.stars = stars;
        }
        public void restore() {
            network.setNodes(this.nodes);
            network.setStars(this.stars);
        }
    }
    public Memento save() {
        return new Memento(this, this.nodes, this.stars);
    }


}
