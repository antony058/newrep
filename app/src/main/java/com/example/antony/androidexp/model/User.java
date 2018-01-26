package com.example.antony.androidexp.model;

public class User {
    private String userName;
    private int userAge;
    private String userBirthDate;

    private String userLocale;

    public User() {

    }

    public User(String userName, int userAge, String userBirthDate) {
        this.userName = userName;
        this.userAge = userAge;
        this.userBirthDate = userBirthDate;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public void setUserBirthDate(String userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public void setUserLocale(String userLocale) {
        this.userLocale = userLocale;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public String getUserBirthDate() {
        return userBirthDate;
    }

    public String getUserLocale() {
        return userLocale;
    }
}
