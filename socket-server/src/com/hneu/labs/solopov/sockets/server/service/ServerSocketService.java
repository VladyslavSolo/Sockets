package com.hneu.labs.solopov.sockets.server.service;

import java.io.*;
import java.util.Random;

/**
 * Socket project. Server side
 *
 * @author Vladyslav Solopov
 * <p>
 * A class whose goal is to perform service tasks for a class
 * {@link com.hneu.labs.solopov.sockets.server.MyServerSocket}
 */

public class ServerSocketService {
    /**
     * Method is looking for the client's surname from the line
     * in format: surname name middleName..."
     *
     * @param line string in specified format
     * @return surname
     */
    private String findSurname(String line) {
        return line.split("\\s")[0];
    }

    /**
     * Method to form a specific prefix from the last name
     * for further use in the ID
     *
     * @param surname
     * @return first characters of the last name
     */
    private String cutAndCreatePrefixID(String surname) {
        if (surname.length() >= 2)
            return surname.substring(0, 2);
        else if (surname.length() >= 1)
            return surname.substring(0, 1);
        else return "";
    }

    /**
     * Create id by line
     *
     * @param line comes in a space separated format.
     *             the first word must be the last name
     * @return generated id by last name and generated number in range
     */
    public String createID(String line) {
        String surname = findSurname(line);
        return generateRandomNumberInBounds(surname, 7500, 8000);
    }

    /**
     * @param surname {@link #cutAndCreatePrefixID}
     * @param min     low bound of random generating id
     * @param max     high bound of random generating id
     * @return full id
     */
    private String generateRandomNumberInBounds(String surname, int min, int max) {
        String prefixID = cutAndCreatePrefixID(surname);
        Random random = new Random();
        int diff = max - min;
        int i = random.nextInt(diff + 1);
        return prefixID + (i + min);
    }

    /**
     * writing id of current client to file
     *
     * @param id
     * @return negative or positive result of writing
     */
    public boolean recordIDToFile(String id) {
        File file = new File(ServerSocketService.class.getResource("/").getFile() + "ids.log");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(id);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("id wasn't record to file");
            return false;
        }

        return true;
    }
}