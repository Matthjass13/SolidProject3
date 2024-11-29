package graphStructure;

/**
 * This class stores all edges incident with a given vertex
 * in our graph.
 * @see Tree
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public class Star extends Tree {

    public Star() {
        super();
    }

    @Override
    public String toString() {
        if(isEmpty())
            return "Empty";
        String string = "";
        for (Road road : roads)
            string += road.toString() + "\n";
        string += "\n";
        return string;
    }

}
