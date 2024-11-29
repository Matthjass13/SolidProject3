import graphStructure.Node;
import graphStructure.Dijkstra;
import graphStructure.Network;

public class Test2 {
    public static void main(String[] args) {

        Node[] cities = new Node[6];
        cities[0] = new Node("Saillon", 0, 0, 0);
        cities[1] = new Node("Leytron", 1, 0, 1);
        cities[2] = new Node("Riddes", 1, 1, 2);
        cities[3] = new Node("Chamoson", 2, 0, 3);
        cities[4] = new Node("Saint-Pierre-de-Clages", 2, 1, 4);
        cities[5] = new Node("Ardon", 3, 1, 5);

        int[][] costs = {
            {0, 10, 30, 0, 0, 0},
            {10, 0, 5, 15, 0, 0},
            {30, 5, 0, 0, 40, 0},
            {0, 15, 0, 0, 20, 60},
            {0, 0, 40, 20, 0, 10},
            {0, 0, 0, 60, 10, 0}
        };

        Network network = new Network(cities, costs);
        System.out.println(network);

        Dijkstra dijkstra = new Dijkstra(network);
        dijkstra.computeShortestPaths();

        dijkstra.displayLambdas();
        dijkstra.displayShortestPathsCosts();
        dijkstra.displayShortestPaths();


    }

}
