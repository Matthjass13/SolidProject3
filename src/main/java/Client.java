import screens.AdminAppScreen;
import screens.ConnectionScreen;
import screens.RegistrationScreen;
import screens.Screen;

import java.io.*;
import java.net.*;

/**
 * This class will connect to a server
 * and make queries on its network.
 * @see Screen
 * @author Matthias Gaillard
 * @since 06.12.2024
 */
public class Client {

    private String serverAddress;
    private int serverPort;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public static void main(String[] args) {
        Screen screen = new AdminAppScreen();
    }

}
