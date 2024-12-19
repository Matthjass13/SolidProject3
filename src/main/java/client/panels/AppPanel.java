package client.panels;

import client.Client;
import client.ui.Button;
import client.ui.Label;
import client.ui.TextField;
import client.ui.Rectangle;

import javax.swing.*;
import java.awt.*;

/**
 * This class will display the main app to the client :
 * He can fill a form and submit it to make research queries.
 * @see ClientPanel
 * @author Sara Pereira
 * @since 04.12.2024
 */
public class AppPanel extends ClientPanel {

    private final TextField source;
    private final TextField sink;
    private final JTextArea pathDescription;
    private final Button animate;

    /**
     * Allows the user
     * to check the previously searched path.
     */
    private final Button previous;
    private final PathCaretaker pathCaretaker = new PathCaretaker();
    protected Button logOut;


    public AppPanel(Client client) {
        super(client);
        super.drawTitle("App");

        Rectangle researchForm = new Rectangle(30, 110, 400, 300, this);
        Label shortestPath = new Label("Shortest path", 0, 0, true, researchForm);

        new Label("from", 0, 50, researchForm);
        source = new TextField("First node", 0, 80, researchForm);
        new Label("to", 0, 110, researchForm);
        sink = new TextField("Second node", 0, 140, researchForm);

        Button dijkstra = new Button("Search", 0, 200, researchForm);
        dijkstra.addActionListener(e -> {
                sendRequest("Dijkstra : "
                        + source.getText() + " : "
                        + sink.getText());
                pathCaretaker.save(createMemento());
            }
        );

        Button hamilton = new Button("Hamilton", 200, 200, Color.decode("#FFC934"), researchForm);
        hamilton.addActionListener(e ->
                sendRequest("Hamilton : " + source.getText() + " : "  + sink.getText())
        );

        pathDescription = new JTextArea("");
        pathDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pathDescription.setLineWrap(true);
        pathDescription.setBounds(200, 30, researchForm.getWidth()-20, 400);
        pathDescription.setOpaque(false);
        researchForm.add(pathDescription);

        animate = new Button("Animate", 250, 20, Color.decode("#CD5C5C"), this);
        animate.addActionListener(e -> sendRequest("Car animation"));
        animate.setVisible(false);

        previous = new Button("Previous", 425, 20, Color.decode("#A9E53D"), this);
        previous.addActionListener(e -> {
                    pathCaretaker.revert(this);
                    sendRequest("Destination Search : "
                            + source.getText() + " : "
                            + sink.getText());
                }
        );
        previous.setVisible(false);

        logOut = new Button("Log out", 600, 20, Color.LIGHT_GRAY, this);
        logOut.addActionListener(e -> logOut());

        revalidate();
        repaint();

    }

    public void handleRequestBack(String response) {
        if(response!=null && response.contains(" : "))
            handleShortestPathDisplay(response);
    }

    private void handleShortestPathDisplay(String response) {
        pathDescription.setText("");
        String[] parties = response.split(" : ");
        pathDescription.setText("Shortest path : \n");
        for(int i=0; i<parties.length-1; ++i)
            pathDescription.append(" -> " + parties[i] + "\n");
        pathDescription.append("Path cost : " + parties[parties.length-1]);
        animate.setVisible(true);
        previous.setVisible(true);
    }

    public Memento createMemento() {
        return new Memento(source.getText(), sink.getText());
    }
    public void setMemento(Memento memento) {
        source.setText(memento.getSource());
        sink.setText(memento.getSink());
    }
    public class Memento {
        private final String source;
        private final String sink;

        public Memento(String startNode, String endNode) {
            this.source = startNode;
            this.sink = endNode;
        }
        public String getSource() {
            return source;
        }
        public String getSink() {
            return sink;
        }
    }

}