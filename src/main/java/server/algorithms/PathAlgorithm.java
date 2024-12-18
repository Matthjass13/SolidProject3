package server.algorithms;

import server.graphStructure.Network;
import server.graphStructure.Path;

/**
 * This class will be in charge of computing
 * paths on a given network.
 * @see Network
 * @see Path
 * @author Matthias Gaillard
 * @since 25.11.2024
 */
public abstract class PathAlgorithm {

    /**
     * Graph to work on
     */
    protected final Network network;

    public PathAlgorithm(Network network) {
        this.network = network;
    }

    /**
     * Finds the path between node i and node j,
     * based on an algorithm defined in the subclass.
     * @param i source node index
     * @param j destination node index
     * @return the path
     */
    public abstract Path findPath(int i, int j);

}
