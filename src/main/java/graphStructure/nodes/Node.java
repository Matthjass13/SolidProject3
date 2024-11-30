package graphStructure.nodes;

/**
 * Node of the network graph.
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public class Node {

    private final String name;

    /**
     * Horizontal position of the node
     */
    private final int x;

    /**
     * Vertical position of the node
     */
    private final int y;

    private final int ID;

    public Node(String name, int x, int y, int ID) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public String toString() {
        return name + ". Position : (" + x + ", " + y + ")";
    }


}
