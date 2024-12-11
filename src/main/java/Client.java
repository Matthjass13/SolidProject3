import requests.*;
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

    public void sendRequest(String request) {
        try (Socket socket = new Socket(serverAddress, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(request);
            System.out.println("Request sent: " + request);

            String response = in.readLine();
            System.out.println("Server response: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void drawNetwork(String request, String serverAddress, int serverPort) {
        try (Socket socket = new Socket(serverAddress, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(request);
            System.out.println("Request sent: " + request);

            String response = in.readLine();
            System.out.println("Server response: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public static void main(String[] args) {



        Screen screen = new ConnectionScreen();


        //drawNetwork("drawNetwork", serverAddress, serverPort);


        /*
        Client client = new Client("localhost", 45000);

        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();

        System.out.println("Please log in");
        System.out.print("Username : ");
        System.out.print("Password : ");

        client.sendRequest(request);*/
    }
}
