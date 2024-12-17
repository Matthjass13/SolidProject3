package server;

import server.graphStructure.Network;
import java.io.*;
import java.net.*;
import server.requests.*;

/**
 * This class will do operations on a given network
 * and answer the client when asked by him.
 * @see Network
 * @author Matthias Gaillard
 * @since 06.12.2024
 */
public class Server {
    private Network network;
    private int port;
    private ServerScreen serverScreen;


    private Handler createUserHandler;
    private Handler checkUserHandler;
    private Handler destinationSearchHandler;
    private Handler networkDrawHandler;
    private Handler routeHistoryHandler;
    private Handler settingsUpdateHandler;
    private Handler trafficUpdateHandler;


    public Server(Network network, int port) {
        this.network = network;
        this.port = port;

        serverScreen = new ServerScreen(network);
        serverScreen.setVisible(true);


        createUserHandler = new CreateUserHandler(network, serverScreen);
        checkUserHandler = new CheckUserHandler(network, serverScreen);
        destinationSearchHandler = new DestinationSearchHandler(network, serverScreen);
        networkDrawHandler = new NetworkDrawHandler(network, serverScreen);
        routeHistoryHandler = new RouteHistoryHandler(network, serverScreen);
        settingsUpdateHandler = new SettingsUpdateHandler(network, serverScreen);
        trafficUpdateHandler = new TrafficUpdateHandler(network, serverScreen);


        createUserHandler.setSuccessor(checkUserHandler);
        checkUserHandler.setSuccessor(destinationSearchHandler);
        destinationSearchHandler.setSuccessor(networkDrawHandler);
        networkDrawHandler.setSuccessor(routeHistoryHandler);
        routeHistoryHandler.setSuccessor(settingsUpdateHandler);
        settingsUpdateHandler.setSuccessor(trafficUpdateHandler);

    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("server.Server started on port " + port);
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
            String result = checkUserHandler.processRequest(userRequest);

            out.println(result);

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
