package algorithms;

import graphStructure.Network;
import graphStructure.Road;

public class Hamilton extends ShortestPathAlgorithm {
    public Hamilton(Network network) {
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
        visited[start] = true;

        int current = start;
        // Current node index

        while(!isComplete(visited)) {
            for(Road road : network.getStars()[current].getRoads()) {
                if(lambdas[start][current] + road.getCost() < lambdas[start][road.getDestination().getID()]) {
                    lambdas[start][road.getDestination().getID()] = lambdas[start][current] + road.getCost();
                    shortestPaths[start][road.getDestination().getID()].rebuild(shortestPaths[start][current], road);
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
