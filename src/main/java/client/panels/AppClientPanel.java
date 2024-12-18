package client.panels;

import client.Client;
import client.ui.Button;
import client.ui.Label;
import client.ui.TextField;

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

    private TextField firstNode;
    private TextField secondNode;
    private Button search;
    private Button hamilton;
    private JTextArea shortestPathDescription;
    private Button animate;
    private PathCaretaker pathCaretaker = new PathCaretaker();

    /**
     * This button allows the user
     * to check the previous searched path.
     */
    private Button previous;
    protected Button logOut;


    public AppClientPanel(Client client) {
        super(client);

        super.drawTitle("App");


        client.ui.Rectangle connectionForm = new client.ui.Rectangle(30, 110, 400, 300, this);

        Label shortestPath = new client.ui.Label("Shortest path", 0, 0, true, connectionForm);


        new Label("from", 0, 50, connectionForm);
        firstNode = new TextField("First node", 0, 80, connectionForm);
        new Label("to", 0, 110, connectionForm);
        secondNode = new TextField("Second node", 0, 140, connectionForm);

        search = new Button("Search", 0, 200, connectionForm);
        search.addActionListener(e -> {
                sendRequest("Dijkstra : "
                        + firstNode.getText() + " : "
                        + secondNode.getText());
                pathCaretaker.save(createMemento());
            }
        );

        hamilton = new Button("Hamilton", 200, 200, Color.YELLOW, connectionForm);
        hamilton.addActionListener(e -> {
                    sendRequest("Hamilton : "
                            + firstNode.getText() + " : "
                            + secondNode.getText());
                }
        );




        shortestPathDescription = new JTextArea("Test");
        shortestPathDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
        shortestPathDescription.setLineWrap(true);
        shortestPathDescription.setBounds(200, 30, connectionForm.getWidth()-20, 400);
        shortestPathDescription.setOpaque(false);
        connectionForm.add(shortestPathDescription);



        animate = new Button("Animate", 250, 20, Color.decode("#CD5C5C"), this);
        animate.addActionListener(e -> {
                    sendRequest("Car animation");
                }
        );
        //animate.setVisible(false);


        previous = new Button("Previous", 425, 20, Color.decode("#A9E53D"), this);
        previous.addActionListener(e -> {
                    pathCaretaker.revert(this);
                    sendRequest("Destination Search : "
                            + firstNode.getText() + " : "
                            + secondNode.getText());
                }
        );


        logOut = new Button("Log out", 600, 20, Color.LIGHT_GRAY, this);
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

        shortestPathDescription.append("Path cost : " + parties[parties.length-1]);

        animate.setVisible(true);


    }






    public Memento createMemento() {
        System.out.println("Originator: Saving to Memento.");
        return new Memento(firstNode.getText(), secondNode.getText());
    }

    public void setMemento(Memento memento) {
        firstNode.setText(memento.getStartNode());
        secondNode.setText(memento.getEndNode());
        System.out.println("Originator: State after restoring from Memento: ");
    }

    public class Memento {
        private final String startNode;
        private final String endNode;

        public Memento(String startNode, String endNode) {
            this.startNode = startNode;
            this.endNode = endNode;
        }
        public String getStartNode() {
            return startNode;
        }
        public String getEndNode() {
            return endNode;
        }
    }





}