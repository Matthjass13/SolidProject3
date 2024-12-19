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
 * This class will connect to a server and make queries on its network.
 * This class is the context of the state pattern for screen states.
 * @see ClientPanel
 * @author Matthias Gaillard
 * @since 06.12.2024
 */
public class Client extends JFrame {

    private final JPanel mainPanel;
    private final CardLayout cardLayout;

    private final String serverAddress;
    private final int serverPort;

    private ClientState currentState;
    private final ClientState registrationScreen;
    private final ClientState connectionScreen;
    private final ClientState appScreen;
    private final ClientState adminAppScreen;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;

        connectionScreen = new ConnectionPanel(this);
        registrationScreen = new RegistrationPanel(this);
        appScreen = new AppPanel(this);
        adminAppScreen = new AdminAppPanel(this);
        currentState = connectionScreen;
        //currentState = adminAppScreen;

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add((Component) registrationScreen, "Registration");
        mainPanel.add((Component) connectionScreen, "Connection");
        mainPanel.add((Component) appScreen, "App");
        mainPanel.add((Component) adminAppScreen, "AdminApp");

        add(mainPanel);

        updateScreen();
    }

    public ClientState getRegistrationScreen() {
        return registrationScreen;
    }
    public ClientState getConnectionScreen() {
        return connectionScreen;
    }
    public ClientState getAppScreen() {
        return appScreen;
    }
    public ClientState getAdminAppScreen() {
        return adminAppScreen;
    }
    public void setCurrentState(ClientState currentState) {
        this.currentState = currentState;
        updateScreen();
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

    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(0, 0, 820, 450);
        try {
            Socket socket = new Socket(serverAddress, serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to server: " + serverAddress + ":" + serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Client client = new Client("localhost", 45000);
            client.start();
        });
    }

}
