package com.inventory.queries;

public class User {
    private int userID;
    private String username;
    private String password;
    private String role;
    private byte[] profilePicture;

    public User(int userID, String username, String password, String role, byte[] profilePicture) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.profilePicture = profilePicture;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }
}