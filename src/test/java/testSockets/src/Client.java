
//package org.example;
import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        try {
            Socket transmittingSocket = new Socket("127.0.0.1", 45000);
            System.out.println("We got in");


            //Receiving info
            InputStream inStream = transmittingSocket.getInputStream();
            InputStreamReader reader = new InputStreamReader(inStream);
            BufferedReader buffin = new BufferedReader(reader);
            // Buffer allows to not lose information

            // Sending info
            OutputStream outStream = transmittingSocket.getOutputStream();
            PrintWriter pout = new PrintWriter(outStream, true);

            pout.println("Hello");




        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
