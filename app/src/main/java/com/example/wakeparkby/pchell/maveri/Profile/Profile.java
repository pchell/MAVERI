package com.example.wakeparkby.pchell.maveri.Profile;

import com.example.wakeparkby.pchell.maveri.Database.DatabaseProfile;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


/**
 * this class for encapsulate info about profile
 */
public class Profile {
    /*
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String userId = mAuth.getCurrentUser().getUid();
    */


    private static String userKey;
    private static String firstName;
    private String lastName;
    private String age;
    private String sex;
    private String listInterests;


   public Profile(String id,String firstName,String lastName,String age,String sex,String listInterests) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.listInterests = listInterests;
        this.userKey = id;
    }

    public Profile(String firstName, String lastName, String age, String sex, String listInterests) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.listInterests = listInterests;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getUserId() {
        return userKey;
    }

    public String getListInterests() {
        return listInterests;
    }

    public Profile(String userKey) {
        this.userKey = userKey;
    }

    public String getUserKey() {
        return userKey;
    }


}
