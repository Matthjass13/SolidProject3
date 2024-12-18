package server.graphStructure;

/**
 * Node of the network graph
 * @param x Horizontal position of the node
 * @param y Vertical position of the node
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public record Node(String name, int x, int y, int ID) {
    public String toString() {
        return name + ". Position : (" + x + ", " + y + ")";
    }

}
