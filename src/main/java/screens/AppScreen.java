package screens;

import screens.ui.Button;
import screens.ui.Label;
import screens.ui.TextField;

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
    private Button search;
    private JLabel shortestPathCostLabel;

    public AppScreen() {
        super();

        super.drawTitle("App");

        shortestPathFromLabel = new Label("Shortest path", 30, 100, 200, this);
        new Label("from", 30, 150, 80, this);
        firstNode = new TextField("First node", 30, 180, this);
        new Label("to", 30, 210, 40, this);
        secondNode = new TextField("Second node", 30, 240, this);

        search = new Button("Search", 30, 300, this);
        search.addActionListener(e -> {
                    sendRequest("Destination Search "
                            + firstNode.getText() + " "
                            + secondNode.getText());
                }
        );


        shortestPathCostLabel = new Label("", 30, 400, 300, this);

        logOut.addActionListener(e -> {
                changeScreen(new ConnectionScreen());
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