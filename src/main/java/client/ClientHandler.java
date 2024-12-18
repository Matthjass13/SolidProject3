package client;

import client.panels.ClientPanel;
import server.requests.Handler;
import server.requests.UserRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * This class will manage the different clients,
 * each will use a different thread.
 * @see ClientPanel
 * @author Matthias Gaillard
 * @author Sara Pereira
 * @since 18.12.2024
 */

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Handler handlerChain;

    public ClientHandler(Socket clientSocket, Handler handlerChain) {
        this.clientSocket = clientSocket;
        this.handlerChain = handlerChain;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("Request from client: " + request);

                // Traiter la requête avec la chaîne de responsabilité
                UserRequest userRequest = new UserRequest(request);
                String result = handlerChain.processRequest(userRequest);

                // Envoyer la réponse au client
                out.println(result);

                if (request.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}