package algorithms;

import graphStructure.Network;
import graphStructure.Road;

/**
 * This class will be in charge of computing
 * shortest paths using Dijkstra algorithm.
 * @see ShortestPathAlgorithm
 * @author Matthias Gaillard
 * @since 24.11.2024
 */

public class Dijkstra extends ShortestPathAlgorithm {

    public Dijkstra(Network network) {
        super(network);
    }

    /**
     * This method computes all shortest paths
     * from start node to all other nodes.
     * It updates both lambdas and shortestPaths fields.
     * @param start start node
     */
    @Override
    public void computeShortestPaths(int start) {

        boolean[] visited = new boolean[lambdas.length];

        visited[start]=true;

        int currentNodeIndex = start;

        while(!isComplete(visited)) {

            for(Road road : network.getStars()[currentNodeIndex].getRoads()) {
                if(lambdas[start][currentNodeIndex]
                        + road.getCost()
                        < lambdas[start][road.getDestination().getID()]) {
                    lambdas[start][road.getDestination().getID()]
                            = lambdas[start][currentNodeIndex]
                            + road.getCost();
                    shortestPaths[start][road.getDestination().getID()].reconstruct(
                            shortestPaths[start][currentNodeIndex], road);
                }
            }

            currentNodeIndex = indexMin(start, visited);
            if(currentNodeIndex!=-1)
                visited[currentNodeIndex]=true;
            else
                break;

        }

    }
    /**
     * This method tests if a given boolean array
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
        int minValue = 10000;
        for(int i=0; i<visited.length; ++i) {
            if(!visited[i] && lambdas[start][i]<minValue) {
                minIndex = i;
                minValue = lambdas[start][minIndex];
            }
        }
        return minIndex;
    }

}
