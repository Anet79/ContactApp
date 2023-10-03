package com.anet.contactapp.entities;



import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

import java.util.UUID;

@Entity(tableName = "contact_table",foreignKeys = {@ForeignKey(entity = User.class,parentColumns = "id",childColumns = "userId",onDelete = CASCADE)})
public class Contact {
    @PrimaryKey()
    @NonNull
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String email;
    private String gender;

    @ColumnInfo(name = "userId")
    private String userId;

    public Contact(String firstName, String phoneNumber) {
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        id = UUID.randomUUID().toString();
    }


//    public Contact(String firstName, String lastName, String phoneNumber, String email, String gender) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.gender = gender;
//    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }
}
