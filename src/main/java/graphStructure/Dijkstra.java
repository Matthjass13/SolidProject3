package graphStructure;

import java.util.List;

/**
 * This class contains a network
 * and will be in charge of computing
 * shortest paths using Dijkstra algorithm.
 * @author Matthias Gaillard
 * @since 24.11.2024
 */

public class Dijkstra {

    private Network network;

    /**
     * shortestPaths[i][j] is the shortest path
     * between city i and city j.
     */
    private Path[][] shortestPaths;

    /**
     * lambda[i][j] will receive the cost
     * of the shortest path from node i to node j.
     */
    int[][] lambdas;

    /**
     * Max value of a path.
     * Value by default in the lambdas array.
     */
    final int MAX = 10000;

    /** This constructor creates a new Dijsktra
     * based on a network given in argument.
     * It also initializes the lambdas and shortestPaths class fields.
     * @param network
     */
    public Dijkstra(Network network) {
        this.network = network;

        lambdas = new int[network.getSIZE()][network.getSIZE()];
        for(int i=0; i<lambdas.length; ++i) {
            for(int j=0; j<lambdas.length; ++j) {
                lambdas[i][j]=MAX;
            }
            lambdas[i][i]=0;
        }

        shortestPaths = new Path[network.getSIZE()][network.getSIZE()];
        for(int i=0; i<lambdas.length; ++i) {
            for(int j=0; j<lambdas.length; ++j) {
                shortestPaths[i][j] = new Path();
            }
        }

    }


    /**
     * This method computes all shortest paths values
     * and store it in lambdas.
     * It naturally uses Dijkstra algorithm.
     */
    public void computeShortestPaths() {
        for(int i=0; i<lambdas.length; ++i)
            computeShortestPaths(i);
    }

    /**
     * This method computes all shortest paths
     * from start node to all other nodes.
     * It updates both lambdas and shortestPaths fields.
     * @param start start node
     */
    public void computeShortestPaths(int start) {

        boolean[] marques = new boolean[lambdas.length];
        for(int i = 0; i < marques.length; ++i)
            marques[i]=false;
        marques[start]=true;

        int currentNodeIndex = start;

        while(!isComplete(marques)) {
            Road road = network.getFiles()[currentNodeIndex].getFirst();

            while(road!=null) {
                if(lambdas[start][currentNodeIndex]
                        + road.getCost()
                        < lambdas[start][road.getDestination().getID()]) {
                    lambdas[start][road.getDestination().getID()]
                            = lambdas[start][currentNodeIndex]
                            + road.getCost();

                    shortestPaths[start][road.getDestination().getID()].reconstruct(
                            shortestPaths[start][currentNodeIndex], road);
                }
                road=road.getNext();
            }

            currentNodeIndex = indexMin(start, marques);
            if(currentNodeIndex!=-1)
                marques[currentNodeIndex]=true;
            else
                break;

        }

    }
    /**
     * This method tests if a given boolean array
     * contains any false value in it.
     * @param visited boolean array
     */
    private boolean isComplete(boolean[] visited) {
        for (boolean v : visited)
            if (!v)
                return false;
        return true;
    }
    /**
     * This method returns the smallest value in lambdas[start]
     * among the vertices not yet validated by Dijkstra algorithm.
     * @param visited boolean array
     */
    private int indexMin(int start, boolean[] visited) {
        int indexMin = -1;
        int valeurMin = 10000;
        for(int i=0; i<visited.length; ++i) {
            if(!visited[i] && lambdas[start][i]<valeurMin) {
                indexMin = i;
                valeurMin = lambdas[start][indexMin];
            }
        }
        return indexMin;
    }


    public void displayLambdas() {
        for(int i=0; i<lambdas.length; ++i) {
            for(int j=0; j<lambdas.length; ++j) {
                System.out.print(lambdas[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void displayShortestPaths() {
        for(int start=0; start<shortestPaths.length; ++start)
            displayShortestPaths(start);
        System.out.println();
    }
    public void displayShortestPaths(int start) {
        for(int j=0; j<shortestPaths.length; ++j)
            displayShortestPath(start, j);
        System.out.println("----------------");
    }
    public void displayShortestPath(int i, int j) {
        System.out.println(shortestPaths[i][j]);
    }

    /**
     * This method is not used in the application
     * It was used in testing to check if the paths
     * were correctly calculated
     */
    public void displayShortestPathsCosts() {
        for(int i=0; i<shortestPaths.length; ++i) {
            for(int j=0; j<shortestPaths.length; ++j) {
                System.out.print(shortestPaths[i][j].getCost() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
