package graphStructure;

/**
 * This class contains all data stored
 * in the graph representation of the network.
 * @see Node
 * @see Star
 * @author Matthias Gaillard
 * @since 24.11.2024
 */

public class Network {

    /**
     * Number of nodes in the network
     */
    private final int SIZE;

    private final Node[] nodes;

    /**
     * For each node, a list contains
     * all roads incident with it.
     */
    private final Star[] stars;


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
    public String toString() {
        String string = "Stars of the networks : " + "\n\n";
        for (int i = 0; i < stars.length; i++)
            string += "Node of " + nodes[i].getName() + " : \n" + stars[i];
        string += "\n";
        return string;
    }





}
