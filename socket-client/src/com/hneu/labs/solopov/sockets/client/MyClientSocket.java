package com.hneu.labs.solopov.sockets.client;

import com.hneu.labs.solopov.sockets.client.model.Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Socket project. Client side
 *
 * @author Vladyslav Solopov
 * <p>
 * Base part of client-application.
 */

class MyClientSocket {
    private static final int serverPort = 4909;

    /**
     * Here is the creation of a client socket, connecting to server socket and data exchange
     *
     * @param client stores basic customer data
     */
    void connectToSocketServer(Client client) {
        {

            try (Socket clientSocket = new Socket(InetAddress.getLocalHost().getHostName(), serverPort)) {

                while (true) {
                    InputStream inputStream = clientSocket.getInputStream();
                    DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                    DataInputStream dataInputStream = new DataInputStream(inputStream);


                    String clientDataLine = client.getSurname() + " " + client.getName() + " " + client.getMiddleName();
                    System.out.println("Generated client data to send: " + clientDataLine);
                    System.out.println("Sending client data to the server...");
                    dataOutputStream.writeUTF(clientDataLine);
                    dataOutputStream.flush();
                    clientDataLine = dataInputStream.readUTF();
                    System.out.println("Get the generated data from the server: " + clientDataLine + '\n');
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());

            }
        }
    }
}