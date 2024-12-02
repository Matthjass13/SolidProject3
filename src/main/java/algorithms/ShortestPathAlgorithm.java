package algorithms;

import graphStructure.Network;
import graphStructure.Path;

/**
 * This class will be in charge of computing
 * shortest paths on a given network.
 * @see Network
 * @see Path
 * @author Matthias Gaillard
 * @since 25.11.2024
 */

public abstract class ShortestPathAlgorithm {

    //
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
    int[][] lambdas;

    /**
     * Max value of a path.
     * Value by default in the lambdas array.
     */
    final int MAX = 10000;

    /** This constructor creates a new ShortestPathAlgorithm
     * based on a network given in argument.
     * It also initializes the lambdas and shortestPaths class fields.
     * @param network graph structure the algorithm will work on
     */
    public ShortestPathAlgorithm(Network network) {
        this.network = network;

        lambdas = new int[network.getSIZE()][network.getSIZE()];
        for(int i=0; i<lambdas.length; ++i) {
            for(int j=0; j<lambdas.length; ++j) {
                lambdas[i][j]=MAX;
            }
            lambdas[i][i]=0;
        }

        shortestPaths = new Path[network.getSIZE()][network.getSIZE()];
        for(int i=0; i<lambdas.length; ++i) {
            for(int j=0; j<lambdas.length; ++j) {
                shortestPaths[i][j] = new Path();
            }
        }

    }


    /**
     * This method computes all shortest paths and shortest path costs
     * and store it in the class fields.
     */
    public void computeShortestPaths() {
        for(int i=0; i<lambdas.length; ++i)
            computeShortestPaths(i);
    }


    /**
     * This method computes all shortest and shortest path costs
     * coming from start node and store.
     * These calculations will be done in specific algorithm
     * who inherit this class.
     */
    public abstract void computeShortestPaths(int start);

    public void displayLambdas() {
        for(int i=0; i<lambdas.length; ++i) {
            for(int j=0; j<lambdas.length; ++j) {
                System.out.print(lambdas[i][j] + " ");
            }
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
        System.out.println("Shortest path from " + network.getNodes()[start].getName());
        for(int j=0; j<shortestPaths.length; ++j)
            displayShortestPath(start, j);
        System.out.println();
    }
    public void displayShortestPath(int i, int j) {
        System.out.print("To " + network.getNodes()[j].getName() + " :");
        System.out.println(shortestPaths[i][j]);
    }

    /**
     * This method is not used in the application
     * It was used in testing to check
     * if the paths were correctly calculated
     */
    public void displayShortestPathsCosts() {
        for(int i=0; i<shortestPaths.length; ++i) {
            for(int j=0; j<shortestPaths.length; ++j) {
                System.out.print(shortestPaths[i][j].getCost() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
