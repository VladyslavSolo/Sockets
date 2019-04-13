package com.hneu.labs.solopov.sockets.client.service;

import com.hneu.labs.solopov.sockets.client.model.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Socket project. Server side
 *
 * @author Vladyslav Solopov
 * <p>
 * A class whose goal is to perform service tasks for a class
 * {@link com.hneu.labs.solopov.sockets.client.MyClientSocket}
 */
public class ClientService {
    /**
     * Here a client object is created from the console
     * @return client filled object
     */
    public Client createClient() {
        String[] messages = {"Select surname:", "Select name:", "Select middle name:"};
        Client client = new Client();

        try (BufferedReader keyListener = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Select your personal data without whitespaces");

            for (int i = 0; i < messages.length; i++) {
                String currIData;
                System.out.println(messages[i]);
                if ((currIData = keyListener.readLine()).trim().isEmpty()) {
                    i--;
                    continue;
                }

                switch (i) {
                    case 0:
                        client.setSurname(currIData);
                        break;
                    case 1:
                        client.setName(currIData);
                        break;
                    case 2:
                        client.setMiddleName(currIData);
                        break;
                    default:
                        break;
                }
            }// end for
        } catch (Exception e) {
            System.err.println("Client data wasn't entered");
        }
        return client;


    }
}
