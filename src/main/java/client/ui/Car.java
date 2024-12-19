package client.ui;

import server.graphStructure.Node;
import server.graphStructure.Path;
import server.graphStructure.Road;

import javax.swing.*;
import java.awt.*;

/**
 * Represents an animated car on the graph.
 * @author Matthias Gaillard
 * @since 13.12.2024
 */
public class Car extends JPanel {

    private double currentX = -30;
    private double currentY = -30;

    private Image image;
    private final Image downImage;
    private final Image upImage;
    private final Image leftImage;
    private final Image rightImage;

    private Timer timer;

    public Car() {
        String folderPath = "src/main/resources/car/";

        downImage = new ImageIcon(folderPath + "down2.png").getImage();
        upImage = new ImageIcon(folderPath + "up2.png").getImage();
        leftImage = new ImageIcon(folderPath + "left2.png").getImage();
        rightImage = new ImageIcon(folderPath + "right2.png").getImage();

        image = rightImage;
    }

    public double getCurrentX() {
        return currentX;
    }
    public double getCurrentY() {
        return currentY;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(String direction) {
        switch(direction) {
            case "right" :
                image = rightImage;
                break;
            case "left":
                image = leftImage;
                break;
            case "up" :
                image = upImage;
                break;
            case "down":
                image = downImage;
                break;
        }
    }

    /**
     * Starts the animation of the car on a path.
     * @param path path
     */
    public void startAnimation(Path path) {
        Node source = path.getRoot();
        currentX = source.x();
        currentY = source.y();
        startAnimation(source, 0, path);
    }

    /**
     * Animates the car on a road.
     * @param source source node of the road
     * @param roadNumber number of the road in the path.
     * @param path global path
     */
    public void startAnimation(Node source, int roadNumber, Path path) {
        if (roadNumber < path.getSize()) {

            int x1 = source.x();
            int y1 = source.y();
            Road road = path.getRoads().get(roadNumber);
            int x2 = road.getDestination().x();
            int y2 = road.getDestination().y();

            double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            double dx = (x2 - x1) / distance;
            double dy = (y2 - y1) / distance;

            if(Math.abs(dx)>Math.abs(dy)) {
                if(dx>=0)
                    setImage("right");
                else
                    setImage("left");
            } else {
                if(dy>=0)
                    setImage("down");
                else
                    setImage("up");
            }

            timer = new Timer(road.getCost(), e -> {
                currentX += dx * 2;
                currentY += dy * 2;
                if (Math.abs(currentX - x2) <= Math.abs(dx) && Math.abs(currentY - y2) <= Math.abs(dy)) {
                    timer.stop();
                    currentX = x2;
                    currentY = y2;
                    startAnimation(road.getDestination(), roadNumber + 1, path);
                }
                repaint();
                getParent().repaint();
            });
            timer.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}