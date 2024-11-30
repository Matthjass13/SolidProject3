package graphStructure.nodes.buildings;

import graphStructure.nodes.Node;

/**
 * @author Matthias Gaillard
 * @see Building
 * @since 29.11.2024
 */
public class Hotel extends Building {
    private int stars;

    public Hotel(String name, int x, int y, int ID, int stars) {
        super(name, x, y, ID);
        this.stars = stars;
    }
}
