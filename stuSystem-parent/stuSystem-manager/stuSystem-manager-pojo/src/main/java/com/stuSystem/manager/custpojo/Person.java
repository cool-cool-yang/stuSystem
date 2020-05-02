package com.stuSystem.manager.custpojo;

public class Person {
    private String username;
    private String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
