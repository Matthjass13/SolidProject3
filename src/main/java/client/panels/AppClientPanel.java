package client.panels;

import client.Client;
import client.ui.Button;

import javax.swing.*;
import java.awt.*;

/**
 * This class will display the main app to the client :
 * the graph but also forms he can fill and submit to make queries.
 * @see ClientPanel
 * @author Sara Pereira
 * @since 04.12.2024
 */
public class AppClientPanel extends ClientPanel {

    private client.ui.TextField firstNode;
    private client.ui.TextField secondNode;
    private client.ui.Button search;

    private JTextArea shortestPathDescription;
    private client.ui.Label shortestPathCostLabel;
    protected client.ui.Button logOut;

    public AppClientPanel(Client client) {
        super(client);

        super.drawTitle("App");


        client.ui.Rectangle connectionForm = new client.ui.Rectangle(30, 110, 250, 450, this);

        new client.ui.Label("Shortest path", 0, 0, connectionForm);
        new client.ui.Label("from", 0, 50, connectionForm);
        firstNode = new client.ui.TextField("First node", 0, 80, connectionForm);
        new client.ui.Label("to", 0, 110, connectionForm);
        secondNode = new client.ui.TextField("Second node", 0, 140, connectionForm);

        search = new client.ui.Button("Search", 0, 200, connectionForm);
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



        shortestPathCostLabel = new client.ui.Label("", 0, 350, 300, connectionForm);


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