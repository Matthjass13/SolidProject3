package server.algorithms;

import server.graphStructure.Network;
import server.graphStructure.Path;
import server.graphStructure.Road;

/**
 * This class will compute Hamilton path
 * (path that goes through every vertex once)
 * on a given Network from a given node to another one.
 * Mostly ChatGPT generated.
 * @see PathAlgorithm
 * @author Matthias Gaillard
 * @since 12.12.2024
 */
public class Hamilton extends PathAlgorithm {

    public Hamilton(Network network) {
        super(network);
    }

    public Path findPath(int i, int j) {
        boolean[] visited = new boolean[network.getSIZE()];
        Path path = new Path(network.getNode(i));

        visited[i] = true;

        if (dfs(i, j, visited, path))
            return path;
        else
            return null;
    }

    private boolean dfs(int currentNode, int destination, boolean[] visited, Path path) {
        if (path.getSize() + 1 == network.getSIZE())
            return currentNode == destination;

        for (Road road : network.getStar(currentNode).getRoads()) {
            int neighbor = road.getDestination().ID();

            if (!visited[neighbor]) {
                visited[neighbor] = true;
                path.add(road);
                if (dfs(neighbor, destination, visited, path))
                    return true;
                visited[neighbor] = false;
                path.removeRoad(path.getSize() - 1);
            }
        }

        return false;
    }

}