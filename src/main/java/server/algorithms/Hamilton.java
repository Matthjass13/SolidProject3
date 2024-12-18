package server.algorithms;

import server.graphStructure.Network;
import server.graphStructure.Path;
import server.graphStructure.Road;

/**
 * This class will compute Hamilton path
 * (path that goes through every vertex once)
 * on a given Network from a given node to another one.
 * @see SearchPathAlgorithm
 * @author Matthias Gaillard
 * @since 12.12.2024
 */
public class Hamilton extends SearchPathAlgorithm {
    public Hamilton(Network network) {
        super(network);
    }

    @Override
    public void computeShortestPaths(int rootIndex) {
        for(int j=0; j<network.getSIZE(); ++j) {
            shortestPaths[rootIndex][j] = findHamiltonianPath(rootIndex, j);
            lambdas[rootIndex][j] = shortestPaths[rootIndex][j].getCost();
        }
    }

    public Path findHamiltonianPath(int source, int destination) {
        boolean[] visited = new boolean[network.getSIZE()];
        Path path = new Path(network.getStars()[source].getRoot());

        visited[source] = true;

        if (dfs(source, destination, visited, path)) {
            return path;
        } else {
            System.out.println("No hamiltonian path found between " + source + " and " + destination);
            return null;
        }
    }

    private boolean dfs(int currentNode, int destination, boolean[] visited, Path path) {
        if (path.getSize() + 1 == network.getSIZE()) {
            return currentNode == destination;
        }

        for (Road road : network.getStars()[currentNode].getRoads()) {
            int neighbor = road.getDestination().getID();

            if (!visited[neighbor]) {
                visited[neighbor] = true;

                path.add(road);

                if (dfs(neighbor, destination, visited, path)) {
                    return true;
                }

                visited[neighbor] = false;

                path.removeRoad(path.getSize() - 1);
            }
        }

        return false;
    }


}


