package graphStructure;

/**
 * This class contains all data stored
 * in the graph representation of the network.
 * @author Matthias Gaillard
 * @since 24.11.2024
 */

public class Network {

    /**
     * Number of cities in the network
     */
    private int SIZE = 10;

    private City[] cities;

    /**
     * For each city, a list contains
     * all roads incident with it.
     */
    private File[] files;


    /**
     * @param cities Array of City to be added in the network
     * @param costs A non-zero costs[i][j] means
     *              there is an edge from cities[i] to cities[j] of that cost.
     */
    public Network(City[] cities, int[][] costs) {
        SIZE = cities.length;

        this.cities = cities;

        files = new File[SIZE];
        for (int i = 0; i < SIZE; i++)
            files[i] = new File();

        for (int i = 0; i < costs.length; i++)
            for (int j = 0; j < costs[i].length; j++)
                if(costs[i][j]!=0)
                    files[i].add(new Road(cities[j], costs[i][j]));
    }


    public int getSIZE() {
        return SIZE;
    }

    public File[] getFiles() {
        return files;
    }

    public void displayFiles() {
        for (int i = 0; i < files.length; i++) {
            System.out.println("City of " + cities[i].getName() + ":");
            files[i].display();
        }
    }

    // Use Memento ?
    // Use factory

}
