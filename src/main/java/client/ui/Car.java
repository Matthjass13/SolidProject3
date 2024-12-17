package client.ui;

import server.algorithms.Dijkstra;
import server.graphStructure.Network;
import server.graphStructure.Node;
import server.graphStructure.Path;
import server.graphStructure.Road;

import javax.swing.*;
import java.awt.*;

/**
 * This class will animate a car on the graph.
 * @author Matthias Gaillard
 * @since 13.12.2024
 */
public class Car extends JPanel {
    private double currentX;
    private double currentY;
    private Timer timer;
    private Image image;

    private Image downImage;
    private Image upImage;
    private Image leftImage;
    private Image rightImage;

    private Image upLeftImage;
    private Image upRightImage;
    private Image downLeftImage;
    private Image downRightImage;


    public Car() {
        downImage = new ImageIcon("src/main/resources/downCar.png").getImage();
        upImage = new ImageIcon("src/main/resources/upCar.png").getImage();
        leftImage = new ImageIcon("src/main/resources/leftCar.png").getImage();
        rightImage = new ImageIcon("src/main/resources/rightCar.png").getImage();

        upLeftImage = new ImageIcon("src/main/resources/upLeftImage.png").getImage();
        upRightImage = new ImageIcon("src/main/resources/upRightImage.png").getImage();
        downLeftImage = new ImageIcon("src/main/resources/downLeftImage.png").getImage();
        downRightImage = new ImageIcon("src/main/resources/downRightImage.png").getImage();


        image = new ImageIcon("src/main/resources/rightCar.png").getImage();
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

    public void startAnimation(Path path) {
        Node source = path.getRoot();
        currentX = source.getX();
        currentY = source.getY();
        startAnimation(source, 0, path);
    }

    public void startAnimation(Node source, int roadNumber, Path path) {
        if (roadNumber < path.getSize()) {

            int x1 = source.getX();
            int y1 = source.getY();
            Road road = path.getRoads().get(roadNumber);
            int x2 = road.getDestination().getX();
            int y2 = road.getDestination().getY();

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



            /*
            String[] directions = {"right", "downRight", "down", "downLeft",
                    "left", "upLeft", "up", "upRight"};
            double angle = Math.atan2(dy, dx);
            if (angle < 0)
                angle += 2 * Math.PI;
            int index = (int) Math.round(8 * angle / (2 * Math.PI)) % 8;
            setImage(directions[index]);*/





            timer = new Timer(road.getCost()*2, e -> {
                currentX += dx * 2;
                currentY += dy * 2;

                System.out.println(Math.abs(currentX - x2) + " * " + Math.abs(currentY - y2));
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
        //g.drawImage(image, (int) currentX, (int) currentY, this);
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
            case "upRight":
                image = upRightImage;
                break;
            case "upLeft":
                image = upLeftImage;
                break;
            case "downLeft":
                image = downLeftImage;
                break;
            case "downRight":
                image = downRightImage;
                break;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation de voiture");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        /*
        Network network = new Network();
        Dijkstra d = new Dijkstra(network);
        d.computeShortestPaths();
        Path path = d.getShortestPaths(0, 12);*/


        Node node0 = new Node("", 0, 0, 0);
        Node node2 = new Node("", 100, 0, 1);
        Node node1 = new Node("", 100, 100, 2);
        Node node3 = new Node("", 0, 100, 3);

        Road r0 = new Road(node1, 0);
        Road r1 = new Road(node2, 0);
        Road r2 = new Road(node3, 0);
        Road r3 = new Road(node0, 0);

        Path path = new Path(node0);
        path.add(r0);
        path.add(r1);
        path.add(r2);
        path.add(r3);


        Car carPanel = new Car();

        frame.add(carPanel);

        carPanel.startAnimation(path);

        frame.validate();
        frame.repaint();

    }

}
