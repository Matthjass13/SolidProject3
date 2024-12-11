package screens;

import requests.*;
import screens.ui.Button;
import screens.ui.Label;
import screens.ui.TextField;
import users.User;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class will display the main app to the client :
 * the graph but also forms he can fill and submit to make queries.
 * @see Screen
 * @since 04.12.2024
 * @author Sara Pereira De Pina
 */
public class AppScreen extends Screen {

    private Label shortestPathFromLabel;
    private TextField firstNode;
    private Label to;
    private TextField secondNode;
    private Button calculate;
    private JLabel shortestPathCostLabel;

    public AppScreen() {
        super();

        super.drawTitle("App");

        shortestPathFromLabel = new Label("Shortest path from : ", 100, 100, 200, 25, this);
        firstNode = new TextField("First node", 100, 150, 80, 25, this);
        to = new Label("to", 200, 150, 40, 25, this);
        secondNode = new TextField("Second node", 220, 150, 80, 25, this);
        shortestPathCostLabel = new Label("Shortest path cost : ", 100, 200, 200, 25, this);

        calculate = new Button("Calculate", 100, 250, this);
        calculate.addActionListener(e -> {
                    sendRequest("Destination Search "
                            + firstNode.getText() + " "
                            + secondNode.getText());
                }
        );

        // sendRequest("Draw network", this);
        // State pattern pour l'affichage du network
        // Flyweight on node, road

    }


    public void sendRequest(String request) {
        try (Socket socket = new Socket("localhost", 45000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(request);
            System.out.println("Request sent: " + request);

            String response = in.readLine();
            System.out.println("Server response: " + response);

            handleRequestBack(response);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleRequestBack(String response) {

        String[] parties = response.split("Total cost : ");
        String cost = parties[1].trim();

        shortestPathCostLabel.setText("Shortest path cost : " + cost);

    }


}