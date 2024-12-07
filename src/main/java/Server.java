import algorithms.ShortestPathAlgorithm;
import graphStructure.Network;

import java.io.*;
import java.net.*;
import graphStructure.Path;
import algorithms.Dijkstra;


/**
 * Server class that will do operations on a given network.
 * @see Network
 * @since 06.12.2024
 * @author Matthias Gaillard
 */
public class Server {
    private Network network;
    private int port;

    public Server(Network network, int port) {
        this.network = network;
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request = in.readLine();
            System.out.println("Received request: " + request);

            String[] parts = request.split(" to ");
            if (parts.length != 2) {
                out.println("Invalid request format. Use: startNode to endNode");
                return;
            }
            String startNodeName = parts[0];
            String endNodeName = parts[1];

            // Recherche du plus court chemin
            ShortestPathAlgorithm dijkstra = new Dijkstra(network);
            dijkstra.computeShortestPaths();
            Path shortestPath = dijkstra.getShortestPaths(startNodeName, endNodeName);
            if (shortestPath == null)
                out.println("No path found between " + startNodeName + " and " + endNodeName);
            else
                out.println("Shortest path: " + shortestPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Network network = new Network();
        Server server = new Server(network, 45000);
        server.start();
    }

}
