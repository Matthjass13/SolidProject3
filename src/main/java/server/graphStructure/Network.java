package server.graphStructure;

import java.util.HashMap;

/**
 * This class contains all data stored
 * in the graph representation of a network.
 * @see Star
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public class Network {

    private final boolean oriented;

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
    private final HashMap<String, Integer> nodesDirectory;


    /**
     * We build the network of Paris in this constructor.
     * See resources for source image.
     */
    public Network(boolean oriented) {

        this.oriented = oriented;

        nodesDirectory = new HashMap<>();

        Node[] nodes = new Node[14];
        nodes[0] = new Node("Eiffel Tower", 318, 480, 0);
        nodes[1] = new Node("Arc de Triomphe", 293, 346, 1);
        nodes[2] = new Node("Place Concorde", 400, 378, 2);
        nodes[3] = new Node("Orsay Museum", 484, 559, 3);
        nodes[4] = new Node("Catacombs", 490, 717, 4);
        nodes[5] = new Node("Pantheon", 582, 596, 5);
        nodes[6] = new Node("Louvre museum", 506, 439, 6);
        nodes[7] = new Node("Notre Dame", 605, 520, 7);
        nodes[8] = new Node("Père Lachaise", 850, 443, 8);
        nodes[9] = new Node("Sacré Coeur", 600, 244, 9);
        nodes[10] = new Node("City of Science", 824, 168, 10);
        nodes[11] = new Node("Bastille", 720, 512, 11);
        nodes[12] = new Node("Saint-Suplice", 610, 767, 12);
        nodes[13] = new Node("Bercy", 845, 685, 13);

        int[][] costs = {
            {0, 1, 10}, {0, 2, 8}, {0, 3, 10}, {0, 6, 15},
            {1, 9, 30}, {1, 2, 10},
            {2, 9, 25}, {2, 6, 10},
            {3, 6, 10}, {3, 5, 5}, {3, 4, 15},
            {4, 12, 12}, {4, 5, 10},
            {5, 12, 14}, {5, 13, 20}, {5, 11, 7}, {5, 7, 5},
            {6, 9, 20}, {6, 7, 5}, {6, 11, 15},
            {8, 11, 20}, {8, 13, 20}, {8, 10, 25},
            {9, 11, 25}, {9, 10, 18},
            {10, 11, 35},
            {11, 13, 18},
            {12, 13, 15}
        };

        SIZE = nodes.length;

        stars = new Star[SIZE];
        for (int i = 0; i < SIZE; i++) {
            nodesDirectory.put(nodes[i].name(), nodes[i].ID());
            stars[i] = new Star(nodes[i]);
        }

        for (int roadIndex = 0; roadIndex < costs.length; roadIndex++)
            stars[costs[roadIndex][0]].add(new Road(nodes[costs[roadIndex][1]], costs[roadIndex][2]));

        // We add missing reverse edges in case of undirected network
        if(!oriented) {
            for(Star star : stars) {
                for(Road road : star.getRoads()) {
                    if(!stars[road.getDestination().ID()].hasLeaf(star.getRoot()))
                        addRoad(road.getDestination().ID(), star.getRoot().ID(), road.getCost());
                }
            }
        }

    }
    /**
     * Network will be undirected by default.
     */
    public Network() {
        this(false);
    }

    public int getSIZE() {
        return SIZE;
    }
    public Star getStar(int index) {
        return stars[index];
    }
    public Node getNode(int index) {
        return stars[index].getRoot();
    }

    /**
     * Returns the id of a node by its name.
     * @param name node name
     * @return the id of the node
     */
    public int getIDByName(String name) {
        return nodesDirectory.get(name);
    }

    /**
     * This method changes the cost of edge (i, j),
     * and (j, i) is the network is not oriented.
     * @param i start node index
     * @param j end node index
     * @param cost new cost
     */
    public void setCost(int i, int j, int cost) {
        for(Road road : stars[i].getRoads()) {
            if(road.getDestination().ID()==j) {
                road.setCost(cost);

                if(!oriented) {
                    for(Road reverseRoad : stars[j].getRoads()) {
                        if(reverseRoad.getDestination().ID()==i)
                            reverseRoad.setCost(cost);
                    }
                }

                return;
            }
        }
        addRoad(i, j, cost);
    }
    public void addRoad(int i, int j, int cost) {
        stars[i].add(new Road(getNode(j), cost));
    }

    public String toString() {
        String string = "Stars of the networks : " + "\n\n";
        for (Star star : stars)
            string += star;
        string += "\n";
        return string;
    }

}