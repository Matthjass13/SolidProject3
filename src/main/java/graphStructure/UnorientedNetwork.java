package graphStructure;

/**
 * This class exists only to speed up
 * the creation process of network with unoriented edges.
 * We can consider an unoriented network to be an standard oriented network
 * with 2 or 0 edges between each node.
 * @see Network
 * @author Matthias Gaillard
 * @since 07.12.2024
 */
public class UnorientedNetwork extends Network {

    /**
     * In this constructor, we first create an oriented Network.
     * Then, for each edge (i, j), we add the edge (j, i) of the same cost.
     * We thus obtain an oriented network which acts as an unoriented one.
     */
    public UnorientedNetwork() {
        super();
        for(Star star : stars) {
            for(Road road : star.getRoads()) {
                if(stars[road.getDestination().getID()].hasLeaf(star.getRoot()))
                    addRoad(road.getDestination().getID(), star.getRoot().getID(), road.getCost());
            }
        }
    }

}
