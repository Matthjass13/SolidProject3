package client.ui;


import javax.swing.*;
import java.awt.*;


/**
 * This class will animate a car on the graph.
 * @author Matthias Gaillard
 * @since 13.12.2024
 */
class Car extends JPanel {
    private int carX = 0; // Position de la voiture (x)
    private int carY = 200; // Position verticale fixe

    private final int CAR_WIDTH = 100;
    private final int CAR_HEIGHT = 50;

    private Timer timer;

    public Car() {
        timer = new Timer(30, e -> {
            carX += 5;
            if (carX > getWidth()) {
                carX = -CAR_WIDTH;
            }
            repaint();
        });
    }

    public void startAnimation() {
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Effacer l'ancien dessin

        // Dessiner la route
        g.setColor(Color.GRAY);
        g.fillRect(0, carY + CAR_HEIGHT / 2, getWidth(), 10);

        // Dessiner la voiture
        drawCar(g, carX, carY);
    }

    private void drawCar(Graphics g, int x, int y) {
        // Dessiner le corps de la voiture
        g.setColor(Color.BLUE);
        g.fillRect(x, y, CAR_WIDTH, CAR_HEIGHT);

        // Dessiner les roues
        g.setColor(Color.BLACK);
        g.fillOval(x + 10, y + CAR_HEIGHT, 20, 20); // Roue avant
        g.fillOval(x + CAR_WIDTH - 30, y + CAR_HEIGHT, 20, 20); // Roue arrière

        // Dessiner les fenêtres
        g.setColor(Color.WHITE);
        g.fillRect(x + 10, y + 10, 30, 20); // Fenêtre avant
        g.fillRect(x + 50, y + 10, 30, 20); // Fenêtre arrière
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation de voiture");
        Car carPanel = new Car(); // Panel qui gère l'animation

        frame.add(carPanel);
        frame.setSize(800, 400); // Taille de la fenêtre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        carPanel.startAnimation(); // Démarrer l'animation
    }


}



