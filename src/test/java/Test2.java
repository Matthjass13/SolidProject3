public class Test2 {
    public static void main(String[] args) {


        String request = "Destination search Riddes Ardon";

        String nodes = request.substring(19);

        System.out.println("*"+nodes+"*");


        String[] nodesString = nodes.split(" ");

        System.out.println("* string0 " + nodesString[0] + " *");
        System.out.println("* string1 " + nodesString[1] + " *");



        /*
        int node1ID = network.getNodesDirectory().get(node1);
        int node2ID = network.getNodesDirectory().get(node2);

        */


        /*
        Network network = new Network();
        Dijkstra dijkstra = new Dijkstra(network);
        dijkstra.computeShortestPaths();

        System.out.println(network);
        dijkstra.displayLambdas();
        dijkstra.displayShortestPathsCosts();
        dijkstra.displayShortestPaths();
*/

    }

}
