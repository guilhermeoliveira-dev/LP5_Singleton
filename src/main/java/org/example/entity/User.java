package org.example.entity;

public class User {

    private final String id;
    private final String email;
    private final String number;

    public User(String id, String email, String number) {

        if(id.isEmpty()){
            throw new IllegalArgumentException("Invalid user ID");
        }
        if(email.isEmpty()){
            throw new IllegalArgumentException("Invalid user email");
        }
        if(number.isEmpty()){
            throw new IllegalArgumentException("Invalid user number");
        }

        this.id = id;
        this.email = email;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }
}
