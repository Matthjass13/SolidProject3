package screens;

import algorithms.Dijkstra;
import graphStructure.*;
import graphStructure.nodes.Node;

import javax.swing.*;
import java.awt.*;


public class GPS extends JFrame {

    private Network network;


    /**
     * Space between the graph and the border of the JFrame
     */
    private final static int MARGIN = 100;

    /**
     * Space between the drawings and labels
     */
    private final static int LABEL_MARGIN = 5;

    private Path shortestPathToDisplay;


    public GPS() {
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.network = new Network();

        Dijkstra dijkstra = new Dijkstra(network);
        dijkstra.computeShortestPaths();
        shortestPathToDisplay = dijkstra.getShortestPaths()[5][0];

        add(new DrawNetwork());

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GPS s = new GPS();
            s.setVisible(true);
        });
    }

    private class DrawNetwork extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.BLACK);

            draw(network, g2d);
            draw(shortestPathToDisplay, g2d, Color.RED);

        }
    }

    public void draw(Network network, Graphics2D g2d) {
        for(int i=0; i< network.getSIZE(); ++i) {
            Star star = network.getStars()[i];
            int x1 = star.getRoot().getX() + MARGIN;
            int y1 = star.getRoot().getY() + MARGIN;
            g2d.drawString(star.getRoot().getName(), x1 + LABEL_MARGIN, y1 - LABEL_MARGIN);
            for(Road road : star.getRoads()) {
                draw(road, star.getRoot(), g2d);
            }
        }
    }

    public void draw(Road road, Node source, Graphics2D g2d) {
        int x1 = source.getX() + MARGIN;
        int y1 = source.getY() + MARGIN;
        int x2 = road.getDestination().getX() + MARGIN;
        int y2 = road.getDestination().getY() + MARGIN;
        g2d.drawLine(x1, y1, x2, y2);
        g2d.drawString(String.valueOf(road.getCost()), (x1+x2)/2 + LABEL_MARGIN, (y1+y2)/2 - LABEL_MARGIN);
    }

    public void draw(Path path, Graphics2D g2d, Color color) {
        g2d.setColor(color);
        Node current = path.getRoot();
        for(Road road : path.getRoads()) {
            draw(road, current, g2d);
            current = road.getDestination();
        }
    }

    //Utiliser repaint quand on actualise

}
