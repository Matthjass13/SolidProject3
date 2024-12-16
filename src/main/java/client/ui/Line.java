package client.ui;

import server.graphStructure.Node;
import javax.swing.*;
import java.awt.*;


/**
 * UNUSED
 * This class represents a segment in a JFrame
 * It is used to trace roads in the network.
 * @author Matthias Gaillard
 * @since 12.12.2024
 */
public class Line extends JPanel {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;

    public Line(int x1, int y1, int x2, int y2, Color color, JPanel pnl) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;

        pnl.add(this);
    }
    public Line(Node node1, Node node2, Color color, JPanel pnl) {
        this(node1.getX(), node1.getY(), node2.getX(), node2.getY(), color, pnl);
    }
    public Line(Node node1, Node node2, JPanel pnl) {
        this(node1.getX(), node1.getY(), node2.getX(), node2.getY(), Color.BLACK, pnl);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.drawLine(x1, y1, x2, y2);
    }

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

}
