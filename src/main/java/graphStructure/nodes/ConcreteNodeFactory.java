package graphStructure.nodes;

import graphStructure.nodes.buildings.Building;
import graphStructure.nodes.stations.Station;

/**
 * This class is the concrete factory for node creation.
 * Not sure if this class is useful at all...
 * @see NodeFactory
 * @author Matthias Gaillard
 * @since 30.11.2024
 */
public class ConcreteNodeFactory implements NodeFactory {
    @Override
    public Building createBuilding() {
        return null;
    }

    @Override
    public Station createStation() {
        return null;
    }
}
