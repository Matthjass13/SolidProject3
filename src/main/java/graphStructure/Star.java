package graphStructure;

/**
 * This class stores all edges incident with a given vertex in our graph.
 * @see Tree
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public class Star extends Tree {

    public Star(Node root) {
        super(root);
    }

    /**
     * @param node
     * @return true if the node is a leaf of the star
     */
    public boolean hasLeaf(Node node) {
        for(Road road : roads)
            if(road.getDestination()==node)
                return true;
        return false;
    }

    @Override
    public String toString() {
        if(isEmpty())
            return "Empty";
        String string = "Node of " + root.getName() + " : \n";
        for (Road road : roads)
            string += road.toString() + "\n";
        string += "\n";
        return string;
    }

}
