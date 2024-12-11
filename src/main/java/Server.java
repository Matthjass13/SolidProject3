import graphStructure.Network;

import java.awt.*;
import java.io.*;
import java.net.*;
import graphStructure.Path;
import graphStructure.Road;
import graphStructure.Star;
import graphStructure.nodes.Node;
import requests.*;
import screens.Screen;
import screens.ServerScreen;

import javax.swing.*;

/**
 * This class will do operations on a given network
 * and answer the client when asked by him.
 * @see Network
 * @since 06.12.2024
 * @author Matthias Gaillard
 */
public class Server {
    private Network network;
    private int port;


    private ServerScreen serverScreen;

    /**
     * Space between the graph and the border of the JFrame
     */
    private final static int MARGIN = 100;

    /**
     * Space between the drawings and labels
     */
    private final static int LABEL_MARGIN = 5;

    private Handler destinationSearchHandler;
    private Handler networkDrawHandler;
    private Handler routeHistoryHandler;
    private Handler settingsUpdateHandler;
    private Handler trafficUpdateHandler;


    public Server(Network network, int port) {
        this.network = network;
        this.port = port;

        serverScreen = new ServerScreen(network);

        destinationSearchHandler = new DestinationSearchHandler(network, serverScreen);
        networkDrawHandler = new NetworkDrawHandler(network, serverScreen);
        routeHistoryHandler = new RouteHistoryHandler(network, serverScreen);
        settingsUpdateHandler = new SettingsUpdateHandler(network, serverScreen);
        trafficUpdateHandler = new TrafficUpdateHandler(network, serverScreen);

        destinationSearchHandler.setSuccessor(networkDrawHandler);
        networkDrawHandler.setSuccessor(routeHistoryHandler);
        routeHistoryHandler.setSuccessor(settingsUpdateHandler);
        settingsUpdateHandler.setSuccessor(trafficUpdateHandler);


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

            UserRequest userRequest = new UserRequest(request);

            String result = destinationSearchHandler.processRequest(userRequest);


            System.out.println(result + "côté serveur");
            out.println(result + "côté client");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Network network = new Network();
        Server server = new Server(network, 45000);
        server.start();
    }






    //---------------------------




    /*


    public GPS() {

        Dijkstra dijkstra = new Dijkstra(network);
        dijkstra.computeShortestPaths();
        shortestPathToDisplay = dijkstra.getShortestPaths()[5][0];
        add(new GPS.DrawNetwork());

    }*/


    /*
    private class DrawNetwork extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLACK);

            //draw(network, g2d);
            draw(shortestPathToDisplay, g2d, Color.RED);
        }
    }*/


}
