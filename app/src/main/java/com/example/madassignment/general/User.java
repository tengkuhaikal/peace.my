package com.example.madassignment.general;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String address;

    @NonNull
    private String address2;

    @NonNull
    private String postcode;

    @NonNull
    private String city;

    @NonNull
    private String state;

    @NonNull
    private String dob;

    @NonNull
    private String phone;

    public User(String username, String password, String firstName, String lastName, String address, String address2, String postcode, String city, String state, String dob, String phone) {
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    @NonNull
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(@NonNull String address2) {
        this.address2 = address2;
    }

    @NonNull
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(@NonNull String postcode) {
        this.postcode = postcode;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @NonNull
    public String getState() {
        return state;
    }

    public void setState(@NonNull String state) {
        this.state = state;
    }

    @NonNull
    public String getDob() {
        return dob;
    }

    public void setDob(@NonNull String dob) {
        this.dob = dob;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }
}
