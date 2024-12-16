package server.algorithms;

import server.graphStructure.Network;

/**
 * This class will compute Hamilton path
 * (path that goes through every vertex once)
 * on a given Network from a given node
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

    }

}
