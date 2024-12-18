package server;

import client.ClientHandler;
import server.graphStructure.Network;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.requests.*;

/**
 * This class will do operations on a given network
 * and answer the client when asked by him.
 * @see Network
 * @author Matthias Gaillard
 * @since 06.12.2024
 */
public class Server {

    private final int port;

    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    private final Handler userCreationHandler;


    public Server(Network network, int port) {

        this.port = port;

        ServerScreen serverScreen = new ServerScreen(network);
        serverScreen.setVisible(true);

        userCreationHandler = new UserCreationHandler(network, serverScreen);
        Handler userCheckHandler = new UserCheckHandler(network, serverScreen);
        Handler dijkstraHandler = new DijkstraHandler(network, serverScreen);
        Handler hamiltonHandler = new HamiltonHandler(network, serverScreen);
        Handler carAnimationHandler = new CarAnimationHandler(network, serverScreen);
        Handler trafficUpdateHandler = new TrafficUpdateHandler(network, serverScreen);

        userCreationHandler.setSuccessor(userCheckHandler);
        userCheckHandler.setSuccessor(dijkstraHandler);
        dijkstraHandler.setSuccessor(hamiltonHandler);
        hamiltonHandler.setSuccessor(carAnimationHandler);
        carAnimationHandler.setSuccessor(trafficUpdateHandler);

    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");
                threadPool.submit(new ClientHandler(clientSocket, userCreationHandler));
            }
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