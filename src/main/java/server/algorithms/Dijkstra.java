package server.algorithms;

import server.graphStructure.Network;
import server.graphStructure.Path;
import server.graphStructure.Road;

/**
 * This class will be in charge of computing
 * shortest paths using Dijkstra algorithm.
 * @see PathAlgorithm
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public class Dijkstra extends PathAlgorithm {

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

    public Dijkstra(Network network) {
        super(network);

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
                shortestPaths[i][j] = new Path(network.getNode(i));
    }

    @Override
    public Path findPath(int i, int j) {
        computeShortestPaths(i);
        return shortestPaths[i][j];
    }

    /**
     * Computes all shortest paths
     * from start node to all other nodes.
     * It updates both lambdas and shortestPaths fields.
     * @param start start node
     */
    public void computeShortestPaths(int start) {

        boolean[] visited = new boolean[lambdas.length];
        visited[start] = true;

        int current = start;

        while(!isComplete(visited)) {
            for(Road road : network.getStar(current).getRoads()) {
                if(lambdas[start][current] + road.getCost() < lambdas[start][road.getDestination().ID()]) {
                    lambdas[start][road.getDestination().ID()] = lambdas[start][current] + road.getCost();
                    shortestPaths[start][road.getDestination().ID()].rebuild(shortestPaths[start][current], road);
                }
            }

            current = indexMin(start, visited);
            if(current!=-1)
                visited[current]=true;
            else
                break;
        }

    }

    /**
     * Tests if a given boolean array
     * contains any false value in it.
     * @param visited boolean array
     */
    private boolean isComplete(boolean[] visited) {
        for (boolean v : visited)
            if (!v)
                return false;
        return true;
    }

    /**
     * This method returns the smallest value in lambdas[start]
     * among the vertices not yet validated by Dijkstra algorithm.
     * @param visited boolean array
     */
    private int indexMin(int start, boolean[] visited) {
        int minIndex = -1;
        int minValue = MAX;
        for(int i = 0; i < visited.length; ++i) {
            if(!visited[i] && lambdas[start][i]<minValue) {
                minIndex = i;
                minValue = lambdas[start][minIndex];
            }
        }
        return minIndex;
    }

}