package com.hneu.labs.solopov.sockets.server;

import com.hneu.labs.solopov.sockets.server.service.ServerSocketService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket project. Server side
 *
 * @author Vladyslav Solopov
 * <p>
 * Base part of server-application.
 */

class MyServerSocket {
    private ServerSocketService service = new ServerSocketService();
    private static final int port = 4909;
    /** Here is the creation of a server socket, client socket, processing and sending data back
     */
    void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                System.out.println("Waiting for a client...");
                Socket clientSocket = serverSocket.accept();

                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();

                DataInputStream dataInputStream = new DataInputStream(inputStream);
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                //get data from the client
                String line = dataInputStream.readUTF().trim();
                System.out.println("Loaded data from client: " + line);

                //generating and writing id to file
                String generatedID = service.createID(line);
                service.recordIDToFile(generatedID);
                System.out.println("ID for current client was been generated. Current id: generatedID");
                //sending full data in format: surname name middleName generatedID: portOfClient
                int clientPort=clientSocket.getPort();
                System.out.println("Current port of client is a: "+clientPort );
                String fullClientData = line + " " + generatedID + ": " + clientPort;
                System.out.println("send generated data: " + fullClientData + "\n");
                dataOutputStream.writeUTF(fullClientData);
                dataOutputStream.flush();

            }//end while
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
