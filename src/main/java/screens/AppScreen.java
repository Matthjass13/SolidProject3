package screens;

import screens.ui.Button;
import screens.ui.Label;
import screens.ui.Rectangle;
import screens.ui.TextField;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class will display the main app to the client :
 * the graph but also forms he can fill and submit to make queries.
 * @see Screen
 * @author Sara Pereira
 * @since 04.12.2024
 */
public class AppScreen extends Screen {

    private TextField firstNode;
    private TextField secondNode;
    private Button search;
    private Label shortestPathCostLabel;
    protected Button logOut;

    public AppScreen(Client client) {
        super(client);

        super.drawTitle("App");


        screens.ui.Rectangle connectionForm = new Rectangle(30, 110, 250, 300, this);

        new Label("Shortest path", 0, 0, connectionForm);
        new Label("from", 0, 50, connectionForm);
        firstNode = new TextField("First node", 0, 80, connectionForm);
        new Label("to", 0, 110, connectionForm);
        secondNode = new TextField("Second node", 0, 140, connectionForm);

        search = new Button("Search", 0, 200, connectionForm);
        search.addActionListener(e -> {
                sendRequest("Destination Search : "
                        + firstNode.getText() + " : "
                        + secondNode.getText());
            }
        );


        shortestPathCostLabel = new Label("", 0, 400, 300, connectionForm);


        logOut = new Button("Log out", 500, 20, Color.LIGHT_GRAY, this);
        logOut.addActionListener(e -> {
                logOut();
            }
        );


        revalidate();
        repaint();


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