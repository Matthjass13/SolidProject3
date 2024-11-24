package graphStructure;

/**
 * Node of the network graph.
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public class City {

    private String name;

    /**
     * Horizontal position of the city
     */
    private int x;

    /**
     * Vertical position of the city
     */
    private int y;

    private int ID;

    public City(String name, int x, int y, int ID) {
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
        return "City of " + name + ". Position : (" + x + ", " + y + ")";
    }

    // Use factory method ?
    // Use flyweight to draw ?

}
