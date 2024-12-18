package client;

import client.panels.*;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class will connect to a server
 * and make queries on its network.
 * This class is the context of the state pattern
 * for screen states.
 * @see ClientPanel
 * @author Matthias Gaillard
 * @since 06.12.2024
 */
public class Client extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;

    private String serverAddress;
    private int serverPort;

    private ClientState currentState;

    private ClientState registrationScreen;
    private ClientState connectionScreen;
    private ClientState appScreen;
    private ClientState adminAppScreen;


    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;

        connectionScreen = new ConnectionClientPanel(this);
        registrationScreen = new RegistrationClientPanel(this);
        appScreen = new AppClientPanel(this);
        adminAppScreen = new AdminAppClientPanel(this);
        currentState = adminAppScreen;

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add((Component) registrationScreen, "Registration");
        mainPanel.add((Component) connectionScreen, "Connection");
        mainPanel.add((Component) appScreen, "App");
        mainPanel.add((Component) adminAppScreen, "AdminApp");

        add(mainPanel);

        updateScreen();

    }

    public ClientState getCurrentState() {
        return currentState;
    }
    public void setCurrentState(ClientState currentState) {
        this.currentState = currentState;
        updateScreen();
    }
    public ClientState getConnectionScreen() {
        return connectionScreen;
    }
    public ClientState getRegistrationScreen() {
        return registrationScreen;
    }
    public ClientState getAdminAppScreen() {
        return adminAppScreen;
    }
    public ClientState getAppScreen() {
        return appScreen;
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Client client = new Client("localhost", 45000);
            client.start();
        });
    }

    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setBounds(0, 0, 800, 450);

        try {
            socket = new Socket(serverAddress, serverPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Connected to server: " + serverAddress + ":" + serverPort);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void updateScreen() {
        if (currentState == registrationScreen) {
            cardLayout.show(mainPanel, "Registration");
        } else if (currentState == connectionScreen) {
            cardLayout.show(mainPanel, "Connection");
        } else if (currentState == appScreen) {
            cardLayout.show(mainPanel, "App");
        } else if (currentState == adminAppScreen) {
            cardLayout.show(mainPanel, "AdminApp");
        }

        revalidate();
        repaint();
    }

}
