package graphStructure.nodes;

import graphStructure.Network;
import graphStructure.nodes.buildings.Building;
import graphStructure.nodes.stations.Station;

/**
 * This class is the abstract factory for node creation.
 * @see Network
 * @author Matthias Gaillard
 * @since 30.11.2024
 */
public interface NodeFactory {
    public Building createBuilding();
    public Station createStation();

}
