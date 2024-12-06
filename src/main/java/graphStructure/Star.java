package graphStructure;

import graphStructure.nodes.Node;

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
