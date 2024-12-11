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

    private Path shortestPathToDisplay;


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
    }


    public void drawNetwork(Screen screen, Graphics2D g2d) {
        for(int i=0; i< network.getSIZE(); ++i) {
            Star star = network.getStars()[i];
            int x1 = star.getRoot().getX() + MARGIN;
            int y1 = star.getRoot().getY() + MARGIN;
            g2d.drawString(star.getRoot().getName(), x1 + LABEL_MARGIN, y1 - LABEL_MARGIN);
            for(Road road : star.getRoads()) {
                draw(road, star.getRoot(), g2d);
            }
        }
    }

    public void draw(Road road, Node source, Graphics2D g2d) {
        int x1 = source.getX() + MARGIN;
        int y1 = source.getY() + MARGIN;
        int x2 = road.getDestination().getX() + MARGIN;
        int y2 = road.getDestination().getY() + MARGIN;
        g2d.drawLine(x1, y1, x2, y2);
        g2d.drawString(String.valueOf(road.getCost()), (x1+x2)/2 + LABEL_MARGIN, (y1+y2)/2 - LABEL_MARGIN);
    }

    public void draw(Path path, Graphics2D g2d, Color color) {
        g2d.setColor(color);
        Node current = path.getRoot();
        for(Road road : path.getRoads()) {
            draw(road, current, g2d);
            current = road.getDestination();
        }
    }

    //Utiliser repaint quand on actualise




}
