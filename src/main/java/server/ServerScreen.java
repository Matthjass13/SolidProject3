package server;

import client.ui.Car;
import server.algorithms.Hamilton;
import server.graphStructure.Network;
import server.graphStructure.Path;
import server.graphStructure.Road;
import server.graphStructure.Star;
import server.graphStructure.Node;

import javax.swing.*;
import java.awt.*;

/**
 * This class will display the network from the server.
 * @author Matthias Gaillard
 * @since 10.12.2024
 */
public class ServerScreen extends JFrame {

    private final Network network;
    private final DrawNetwork drawNetwork;
    private Path pathToDisplay = null;

    /**
     * Type of the path to highlight
     * (Dijkstra or Hamilton)
     */
    private String pathType = "Dijkstra";
    private final Color dijkstraColor = Color.BLUE;
    private final Color hamiltonColor = Color.decode("#DB7B27");
    private final BasicStroke thickStroke = new BasicStroke(5.0f);
    private final BasicStroke thinStroke = new BasicStroke(1.0f);
    private final Font boldFont = new Font("Tahoma", Font.BOLD, 15);
    private final Car car;

    public ServerScreen(Network network) {
        super();

        setBounds(550, 0, 993, 925);
        this.network = network;

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 993, 925);
        add(layeredPane);

        ImageIcon backgroundImg = new ImageIcon("src/main/resources/ParisMap.png");
        JLabel imageLabel = new JLabel(backgroundImg);
        imageLabel.setBounds(0, 0, backgroundImg.getIconWidth(), backgroundImg.getIconHeight());
        layeredPane.add(imageLabel, Integer.valueOf(0));

        drawNetwork = new DrawNetwork();
        drawNetwork.setBounds(0, 0, 993, 925);
        drawNetwork.setOpaque(false);
        layeredPane.add(drawNetwork, Integer.valueOf(1));

        this.car = new Car();
        car.setBounds(400, 400, 100, 100);
        car.setVisible(false);
        layeredPane.add(car, 2);

    }

    public void setPathToDisplay(Path pathToDisplay) {
        this.pathToDisplay = pathToDisplay;
    }
    public void setRoadCost(int i, int j, int cost) {
        network.setCost(i, j, cost);
    }
    public void setPathType(String pathType) {
        this.pathType = pathType;
    }

    public class DrawNetwork extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            drawNetwork.setOpaque(false);
            drawNetwork.setBackground(new Color(0, 0, 0, 0));
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            draw(network, g2d);
            if(pathType.equals("Dijkstra"))
                highlightPath(g2d, dijkstraColor);
            else
                highlightPath(g2d, hamiltonColor);
            g.drawImage(car.getImage(), (int) car.getCurrentX() - 32, (int) car.getCurrentY() - 32, this);
        }
    }
    private void draw(Network network, Graphics2D g2d) {
        g2d.setFont(boldFont);
        g2d.setStroke(thinStroke);

        for(int i=0; i< network.getSIZE(); ++i) {

            Star star = network.getStar(i);
            int x1 = star.getRoot().x();
            int y1 = star.getRoot().y();

            for(Road road : star.getRoads())
                draw(road, star.getRoot(), g2d, Color.BLACK);

        }
    }
    private void draw(Road road, Node source, Graphics2D g2d, Color color) {
        int x1 = source.x();
        int y1 = source.y();
        int x2 = road.getDestination().x();
        int y2 = road.getDestination().y();

        g2d.setColor(color);
        g2d.drawLine(x1, y1, x2, y2);

        int labelX = (x1 + x2) / 2;
        int labelY = (y1 + y2) / 2;

        String costText = String.valueOf(road.getCost());
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(costText);
        int textHeight = metrics.getHeight();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(labelX - 2, labelY - textHeight + metrics.getDescent(), textWidth + 4, textHeight);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(thinStroke);
        g2d.drawRect(labelX - 2, labelY - textHeight + metrics.getDescent(), textWidth + 4, textHeight);

        g2d.setColor(Color.BLACK);
        g2d.drawString(costText, labelX, labelY);

        costText = String.valueOf(source.name());
        textWidth = metrics.stringWidth(costText);
        textHeight = metrics.getHeight();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x1 - 22, y1 - textHeight + metrics.getDescent() - 2, textWidth + 4, textHeight);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x1 - 22, y1 - textHeight + metrics.getDescent() - 2, textWidth + 4, textHeight);
        g2d.drawString(source.name(), x1-20, y1);

    }
    private void highlightPath(Graphics2D g2d, Color color) {
        if (pathToDisplay != null) {
            Node current = pathToDisplay.getRoot();
            for (Road road : pathToDisplay.getRoads()) {
                g2d.setStroke(thickStroke);
                draw(road, current, g2d, color);
                current = road.getDestination();
            }
        }
    }

    public void animateCar() {
        if(pathToDisplay !=null) {
            car.setVisible(true);
            car.startAnimation(pathToDisplay);
        }
    }

    public void repaint() {
        drawNetwork.repaint();
    }

    /**
     * Unused method
     */
    public void test() {
        Hamilton h = new Hamilton(network);
        pathToDisplay = h.findPath(0, 1);
        pathType = "Hamilton";
        animateCar();
    }

}
