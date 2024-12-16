package server.algorithms;

import server.graphStructure.Network;
import server.graphStructure.Path;

/**
 * This class will be in charge of computing
 * shortest paths on a given network.
 * @see Network
 * @see Path
 * @author Matthias Gaillard
 * @since 25.11.2024
 */
public abstract class SearchPathAlgorithm {

    /**
     * Graph
     */
    protected final Network network;

    /**
     * shortestPaths[i][j] is the shortest path
     * between node i and node j.
     */
    protected final Path[][] shortestPaths;

    /**
     * lambda[i][j] will receive the cost
     * of the shortest path from node i to node j.
     */
    protected int[][] lambdas;

    /**
     * Max value of a path.
     * Value by default in the lambdas array.
     */
    protected final int MAX = 10000;

    /**
     * This constructor creates a new ShortestPathAlgorithm
     * based on a network given in argument.
     * It also initializes the lambdas and shortestPaths class fields.
     * @param network graph structure the algorithm will work on
     */
    public SearchPathAlgorithm(Network network) {
        this.network = network;

        lambdas = new int[network.getSIZE()][network.getSIZE()];
        for(int i=0; i<lambdas.length; ++i) {
            for(int j=0; j<lambdas.length; ++j) {
                lambdas[i][j]=MAX;
            }
            lambdas[i][i]=0;
        }

        shortestPaths = new Path[network.getSIZE()][network.getSIZE()];
        for(int i=0; i<lambdas.length; ++i)
            for(int j=0; j<lambdas.length; ++j)
                shortestPaths[i][j] = new Path(network.getStars()[i].getRoot());
    }

    public Path[][] getShortestPaths() {
        return shortestPaths;
    }
    public Path getShortestPaths(int i, int j) {
        return shortestPaths[i][j];
    }


    /**
     * This method computes all shortest paths and shortest path costs
     * and store it in the class fields.
     */
    public void computeShortestPaths() {
        for(int rootIndex=0; rootIndex<lambdas.length; ++rootIndex)
            computeShortestPaths(rootIndex);
    }

    /**
     * This method computes all shortest and shortest path costs coming from start node.
     * These calculations will be done in specific algorithm who inherit this class.
     * @param rootIndex start node index
     */
    public abstract void computeShortestPaths(int rootIndex);

    public void displayLambdas() {
        for(int i=0; i<lambdas.length; ++i) {
            for(int j=0; j<lambdas.length; ++j)
                System.out.print(lambdas[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
    public void displayShortestPaths() {
        for(int start=0; start<shortestPaths.length; ++start)
            displayShortestPaths(start);
        System.out.println();
    }
    public void displayShortestPaths(int start) {
        System.out.println("Shortest paths from " + network.getStars()[start].getRoot().getName());
        for(int j=0; j<shortestPaths.length; ++j)
            displayShortestPath(start, j);
        System.out.println();
    }
    public void displayShortestPath(int i, int j) {
        System.out.print("To " + network.getStars()[j].getRoot().getName() + " : ");
        System.out.println(shortestPaths[i][j]);
    }



}
