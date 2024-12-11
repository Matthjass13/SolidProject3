package screens;

import graphStructure.Network;
import graphStructure.Path;
import graphStructure.Road;
import graphStructure.Star;
import graphStructure.nodes.Node;

import javax.swing.*;
import java.awt.*;

public class ServerScreen extends Screen {

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

    private Color shortestPathColor = Color.red;

    public ServerScreen(Network network) {
        super();

        logOut.setVisible(false);

        setBounds(600, 0, 993, 925);

        this.network = network;
        super.drawTitle("Server");
        System.out.println("Hellox");

        // Utiliser un JLayeredPane pour gérer les couches
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 993, 925);
        add(layeredPane);

        // Ajouter l'image en arrière-plan
        ImageIcon backgroundImg = new ImageIcon("src/main/resources/ParisMap.png");
        JLabel imageLabel = new JLabel(backgroundImg);
        imageLabel.setBounds(0, 0, backgroundImg.getIconWidth(), backgroundImg.getIconHeight());
        layeredPane.add(imageLabel, Integer.valueOf(0)); // Ajouter l'image à la couche inférieure

        // Ajouter le panneau DrawNetwork en avant-plan
        DrawNetwork drawNetwork = new DrawNetwork();
        drawNetwork.setBounds(0, 0, 993, 925); // Même taille que le JFrame
        drawNetwork.setOpaque(false); // Rendre le panneau transparent pour laisser passer l'image
        layeredPane.add(drawNetwork, Integer.valueOf(1)); // Ajouter à la couche supérieure

    }





    public class DrawNetwork extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLACK);

            draw(network, g2d);
            if (shortestPathToDisplay != null) {
                drawShortestPath(g2d, shortestPathColor);
            }

        }

    }

    public void draw(Network network, Graphics2D g2d) {

        for(int i=0; i< network.getSIZE(); ++i) {

            Star star = network.getStars()[i];
            int x1 = star.getRoot().getX() + MARGIN_X;
            int y1 = star.getRoot().getY() + MARGIN_Y;
            g2d.drawString(star.getRoot().getName(), x1 + LABEL_MARGIN, y1 - LABEL_MARGIN);
            for(Road road : star.getRoads()) {
                draw(road, star.getRoot(), g2d);
            }

            System.out.println(shortestPathToDisplay + "SHORTEST PATH");
        }

    }

    public void draw(Road road, Node source, Graphics2D g2d) {
        int x1 = source.getX() + MARGIN_X;
        int y1 = source.getY() + MARGIN_Y;
        int x2 = road.getDestination().getX() + MARGIN_X;
        int y2 = road.getDestination().getY() + MARGIN_Y;
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
        g2d.drawString(String.valueOf(road.getCost()), labelX, labelY);



    }

    public void drawShortestPath(Graphics2D g2d, Color color) {
        g2d.setColor(color);

        if(shortestPathToDisplay!=null) {
            Node current = shortestPathToDisplay.getRoot();
            for(Road road : shortestPathToDisplay.getRoads()) {
                draw(road, current, g2d);
                current = road.getDestination();
            }
        }
    }


    public void setShortestPathToDisplay(Path shortestPathToDisplay) {
        this.shortestPathToDisplay = shortestPathToDisplay;
        repaint();
    }


    public void setRoadCost(int node1ID, int node2ID, int cost) {
        network.setCost(node1ID, node2ID, cost);
        repaint();
    }


}
