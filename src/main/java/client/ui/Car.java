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
import javax.swing.*;
import java.awt.*;
import java.util.List;

// Classe Car pour animer une voiture sur un chemin composé de segments
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Car extends JPanel {
    private double carX = 0;
    private double carY = 0;
    private final int CAR_WIDTH = 100;
    private final int CAR_HEIGHT = 50;
    private Timer timer;
    private Path path;
    private double dx, dy;
    private Image image;

    public Car(Path path) {
        this.path = path;
        image = new ImageIcon("src/main/resources/car.png").getImage();
    }


    public Car() {
        image = new ImageIcon("src/main/resources/car.png").getImage();
    }



    public void startAnimation(Path path) {
        Node source = path.getRoot();
        carX = source.getX();
        carY = source.getY();
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
            this.dx = (x2 - x1) / distance;
            this.dy = (y2 - y1) / distance;

            timer = new Timer(30, e -> {
                // Mettre à jour les coordonnées de la voiture
                carX += dx * 2; // Ajuster la vitesse si nécessaire
                carY += dy * 2;

                // Vérification de la fin du mouvement
                if (Math.abs(carX - x2) < Math.abs(dx) && Math.abs(carY - y2) < Math.abs(dy)) {
                    timer.stop();
                    carX = x2;
                    carY = y2;
                    startAnimation(road.getDestination(), roadNumber + 1, path);
                }

                repaint();
            });
            timer.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Effacer l'ancien dessin
        drawCar(g, (int) carX, (int) carY);
    }

    public void move(Node source, Road road) {
        // Méthode inutilisée
    }

    private void drawCar(Graphics g, int x, int y) {
        g.drawImage(image, x, y, this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation de voiture");

        Network network = new Network();
        Dijkstra d = new Dijkstra(network);
        d.computeShortestPaths();
        Path path = d.getShortestPaths(0, 12);


        Car carPanel = new Car(path);


        frame.add(carPanel);


        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        carPanel.startAnimation(path);
    }

    public void setPath(Path path) {
        this.path = path;
    }


}
