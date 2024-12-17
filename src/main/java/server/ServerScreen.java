package server;

import client.panels.ClientPanel;
import client.ui.Car;
import server.algorithms.Dijkstra;
import server.graphStructure.Network;
import server.graphStructure.Path;
import server.graphStructure.Road;
import server.graphStructure.Star;
import server.graphStructure.Node;

import javax.swing.*;
import java.awt.*;


/**
 * This class will display the network from the server.
 * @see ClientPanel
 * @author Matthias Gaillard
 * @since 10.12.2024
 */

public class ServerScreen extends JFrame {

    private Network network;

    /**
     * Space between the graph and the border of the JFrame
     */
    private final static int MARGIN_X = 0;
    private final static int MARGIN_Y = 0;

    /**
     * Space between the drawings and labels
     */
    private final static int LABEL_MARGIN = 5;


    private Path shortestPathToDisplay = null;

    private Color shortestPathColor;

    private DrawNetwork drawNetwork;

    private Car car;

    public ServerScreen(Network network) {
        super();

        setBounds(550, 0, 993, 925);

        this.network = network;
        //super.drawTitle("server.Server");
        shortestPathColor = Color.blue;


        // Utiliser un JLayeredPane pour gérer les couches
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 993, 925);
        add(layeredPane);



        // Ajouter l'image en arrière-plan
        ImageIcon backgroundImg = new ImageIcon("src/main/resources/ParisMap.png");
        JLabel imageLabel = new JLabel(backgroundImg);
        imageLabel.setBounds(0, 0, backgroundImg.getIconWidth(), backgroundImg.getIconHeight());
        layeredPane.add(imageLabel, Integer.valueOf(0)); // Ajouter l'image à la couche inférieure



        this.car = new Car();
        car.setBounds(400, 400, 100, 100);
        car.setVisible(true);
        layeredPane.add(car, 2);


        // Ajouter le panneau DrawNetwork en avant-plan
        drawNetwork = new DrawNetwork();
        drawNetwork.setBounds(0, 0, 993, 925); // Même taille que le JFrame
        drawNetwork.setOpaque(false); // Rendre le panneau transparent pour laisser passer l'image
        layeredPane.add(drawNetwork, Integer.valueOf(1)); // Ajouter à la couche supérieure

        //animateCar();
        //test();


        Dijkstra d = new Dijkstra(network);
        d.computeShortestPaths();
        shortestPathToDisplay = d.getShortestPaths(1, 8);
    }


    public class DrawNetwork extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


            g2d.setStroke(new BasicStroke(1.0f));
            draw(network, g2d);


            g2d.setStroke(new BasicStroke(5.0f));
            if (shortestPathToDisplay != null) {
                drawShortestPath(g2d, shortestPathColor);
            }
            //car.repaint();
            g.drawImage(car.getImage(), (int) car.getCurrentX() - 32, (int) car.getCurrentY() -32, this);
        }
    }

    public void draw(Network network, Graphics2D g2d) {

        for(int i=0; i< network.getSIZE(); ++i) {

            Star star = network.getStars()[i];
            int x1 = star.getRoot().getX() + MARGIN_X;
            int y1 = star.getRoot().getY() + MARGIN_Y;


            // Obtenir les dimensions du texte
            String costText = String.valueOf(star.getRoot().getName());
            FontMetrics metrics = g2d.getFontMetrics();
            int textWidth = metrics.stringWidth(costText);
            int textHeight = metrics.getHeight();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(x1 + LABEL_MARGIN - 2, y1 - LABEL_MARGIN - textHeight + metrics.getDescent() - 2, textWidth + 4, textHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawString(star.getRoot().getName(), x1 + LABEL_MARGIN, y1 - LABEL_MARGIN);

            for(Road road : star.getRoads()) {
                draw(road, star.getRoot(), g2d, Color.BLACK);
            }

        }

    }



    public void draw(Road road, Node source, Graphics2D g2d, Color color) {
        int x1 = source.getX() + MARGIN_X;
        int y1 = source.getY() + MARGIN_Y;
        int x2 = road.getDestination().getX() + MARGIN_X;
        int y2 = road.getDestination().getY() + MARGIN_Y;

        // Dessiner la ligne avec la couleur spécifiée
        g2d.setColor(color);
        g2d.drawLine(x1, y1, x2, y2);

        int labelX = (x1 + x2) / 2 + LABEL_MARGIN;
        int labelY = (y1 + y2) / 2 - LABEL_MARGIN;

        // Obtenir les dimensions du texte
        String costText = String.valueOf(road.getCost());
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(costText);
        int textHeight = metrics.getHeight();

        // Dessiner un rectangle blanc derrière le texte
        g2d.setColor(Color.WHITE);
        g2d.fillRect(labelX - 2, labelY - textHeight + metrics.getDescent() - 2, textWidth + 4, textHeight);

        // Dessiner le texte par-dessus le rectangle
        g2d.setColor(Color.BLACK);
        g2d.drawString(costText, labelX, labelY);
    }

    public void drawShortestPath(Graphics2D g2d, Color color) {
        if (shortestPathToDisplay != null) {
            Node current = shortestPathToDisplay.getRoot();
            for (Road road : shortestPathToDisplay.getRoads()) {
                draw(road, current, g2d, color); // Spécifiez la couleur pour chaque segment
                current = road.getDestination();
            }
        }

    }



    public void test() {

        Dijkstra d = new Dijkstra(network);
        d.computeShortestPaths();
        Path path = d.getShortestPaths(1, 8);

        car.startAnimation(path);
    }


    public void animateCar() {


        /*
        Dijkstra d = new Dijkstra(network);
        d.computeShortestPaths();
        shortestPathToDisplay = d.getShortestPaths(1, 8);
*/
        car.startAnimation(shortestPathToDisplay);
    }

    public void setShortestPathToDisplay(Path shortestPathToDisplay) {
        this.shortestPathToDisplay = shortestPathToDisplay;
    }


    public void setRoadCost(int node1ID, int node2ID, int cost) {
        network.setCost(node1ID, node2ID, cost);
        System.out.println("prout" + network.getStars()[node1ID].getRoads().getFirst().toString());
    }


    public void repaint() {
        drawNetwork.repaint();
    }

}
