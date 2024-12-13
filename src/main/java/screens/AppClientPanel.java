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
 * @see ClientPanel
 * @author Sara Pereira
 * @since 04.12.2024
 */
public class AppClientPanel extends ClientPanel {

    private TextField firstNode;
    private TextField secondNode;
    private Button search;

    private JTextArea shortestPathDescription;
    private Label shortestPathCostLabel;
    protected Button logOut;

    public AppClientPanel(Client client) {
        super(client);

        super.drawTitle("App");


        screens.ui.Rectangle connectionForm = new Rectangle(30, 110, 250, 450, this);

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






        shortestPathDescription = new JTextArea("");
        shortestPathDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
        shortestPathDescription.setLineWrap(true);
        shortestPathDescription.setBounds(20, 280, connectionForm.getWidth()-20, 400);
        shortestPathDescription.setOpaque(false);
        connectionForm.add(shortestPathDescription);


        handleShortestPathDisplay("Orsay Museum : Pantheon : Bercy : 4");


        shortestPathCostLabel = new Label("", 0, 350, 300, connectionForm);


        logOut = new Button("Log out", 500, 20, Color.LIGHT_GRAY, this);
        logOut.addActionListener(e -> {
                logOut();
            }
        );


        revalidate();
        repaint();


        // sendRequest("Draw network", this);
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
        if(response!=null && response.contains(" : ")) {
            handleShortestPathDisplay(response);
            //shortestPathCostLabel.setText("Shortest path cost : " + cost);
        }
    }

    private void handleShortestPathDisplay(String response) {

        shortestPathDescription.setText("");

        String[] parties = response.split(" : ");

        shortestPathDescription.setText("Shortest path : \n");

        for(int i=0; i<parties.length-1; ++i) {
            shortestPathDescription.append(" -> " + parties[i] + "\n");
        }

        shortestPathDescription.append("Shortest path cost : " + parties[parties.length-1]);
    }


}