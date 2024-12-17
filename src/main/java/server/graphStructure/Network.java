package server.graphStructure;

import java.util.HashMap;

/**
 * This class contains all data stored in the oriented graph representation of the network.
 * @see Star
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
     * This field allows to quickly search
     * the id of a given node name.
     */
    HashMap<String, Integer> nodesDirectory;


    /**
     * We build the network of Paris in this constructor.
     * See ressources for source image.
     */
    public Network(boolean oriented) {

        nodesDirectory = new HashMap<String, Integer>();

        // New version
        Node[] nodes = new Node[14];
        nodes[0] = new Node("Eiffel Tower", 318, 480, 0);
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
                {8, 11, 20}, {8, 13, 20}, {8, 10, 25},
                {9, 11, 25}, {9, 10, 18},
                {10, 11, 35},
                {11, 13, 18},
                {12, 13, 15}
        };

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

    /**
     * Returns the id of a node by its name.
     * @param name
     * @return the id of the node
     */
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

}
