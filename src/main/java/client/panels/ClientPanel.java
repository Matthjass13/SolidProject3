package client.panels;

import javax.swing.*;

import client.Client;
import client.ui.Title;

import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * This class will display a generic JPanel to the client.
 * Subclasses will define the specifics of each screen.
 * @author Sara Pereira
 * @since 09.12.2024
 */
public abstract class ClientPanel extends JPanel implements ClientState {


    protected Client client;
    protected Title titleLabel;
    private static final Color BACKGROUND_COLOR = Color.decode("#4682B4");

    public ClientPanel(Client client) {
        this.client = client;
        setBounds(0, 0, 800, 500);
        setBackground(BACKGROUND_COLOR);
        setLayout(null);
    }

    public void drawTitle(String title) {
        titleLabel = new Title(title, this);
    }

    @Override
    public void logOut() {
        client.setCurrentState(client.getConnectionScreen());
    }
    @Override
    public void logIn(boolean admin) {
        if(admin)
            client.setCurrentState(client.getAdminAppScreen());
        else
            client.setCurrentState(client.getAppScreen());
    }
    @Override
    public void goToConnection() {
        client.setCurrentState(client.getConnectionScreen());
    }
    @Override
    public void goToRegistration() {
        client.setCurrentState(client.getRegistrationScreen());
    }


    /**
     * Sends a request as a String to the server.
     * @param request request to send
     */
    public void sendRequest(String request) {

        try (Socket socket = new Socket("localhost", 45000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(request);
            System.out.println("Request sent: " + request);

            String response = in.readLine();
            System.out.println("server.Server response: " + response);

            handleRequestBack(response);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Handle the output of the server for our request.
     * Concrete implementation is done in each subclass.
     * @param response output of the server
     */
    public abstract void handleRequestBack(String response);

}
