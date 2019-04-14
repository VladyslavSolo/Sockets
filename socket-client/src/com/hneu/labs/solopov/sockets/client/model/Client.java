package com.hneu.labs.solopov.sockets.client.model;

/**
 * Socket project. Client side
 *
 * @author Vladyslav Solopov
 * Client entity
 */

public class Client {
    private String name, surname, middleName;
    private long id;

    @Override
    public String toString() {
        return this.getSurname() + " " + this.getName() + " " + this.getMiddleName();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setId(long id) {
        this.id = id;
    }
}
