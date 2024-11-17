import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(45000);
            System.out.println("Server is listening");
            Socket transmittingSocket = server.accept();
            System.out.println("We got a connection");

            //Receiving info
            InputStream inStream = transmittingSocket.getInputStream();
            InputStreamReader reader = new InputStreamReader(inStream);
            BufferedReader buffin = new BufferedReader(reader);
            // Buffer allows to not lose information

            // Sending info
            OutputStream outStream = transmittingSocket.getOutputStream();
            PrintWriter pout = new PrintWriter(outStream, true);


            String line = buffin.readLine();
            System.out.println(line);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
