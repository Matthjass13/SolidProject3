
import algorithms.Dijkstra;
import graphStructure.Network;

public class Test2 {
    public static void main(String[] args) {

        Network network = new Network();
        Dijkstra dijkstra = new Dijkstra(network);
        dijkstra.computeShortestPaths();

        System.out.println(network);
        dijkstra.displayLambdas();
        dijkstra.displayShortestPathsCosts();
        dijkstra.displayShortestPaths();


    }

}
