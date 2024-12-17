package client.panels;

import javax.swing.*;

import client.Client;
import client.ui.Title;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * This class will display a generic JPanel to the client.
 * Subclasses will define the specific of each screen.
 * @see RegistrationClientPanel
 * @see ConnectionClientPanel
 * @see AppClientPanel
 * @author Sara Pereira
 * @since 09.12.2024
 */

public abstract class ClientPanel extends JPanel implements ClientState {

    protected Client client;

    protected Title titleLabel;
    private static Color BACKGROUND_COLOR = Color.decode("#009DCF");


    public ClientPanel(Client client) {

        this.client = client;

        setBounds(0, 0, 800, 500);
        setLayout(null);
        setBackground(BACKGROUND_COLOR);

        revalidate();
        repaint();

        setVisible(true);
    }

    public ClientPanel() {

        setBounds(0, 0, 800, 400);
        setLayout(null);
        setBackground(BACKGROUND_COLOR);

        revalidate();
        repaint();

        setVisible(true);
    }


    public String getTitle() {
        return titleLabel.getText();
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


    public void drawTitle(String title) {
        titleLabel = new Title(title, this);
    }






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


    public abstract void handleRequestBack(String response);


}
