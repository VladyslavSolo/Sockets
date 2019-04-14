package com.hneu.labs.solopov.sockets.client;

import com.hneu.labs.solopov.sockets.client.model.Client;
import com.hneu.labs.solopov.sockets.client.service.ClientService;

/**
 * Socket project. Client side
 *
 * @author Vladyslav Solopov
 * Student of 6.04.51.15.02 group, 4 course
 * Variant 8
 */

public class Main {
    public static void main(String[] args) {
        Client client = new ClientService().createClient();
        new MyClientSocket().connectToSocketServer(client);
    }
}
