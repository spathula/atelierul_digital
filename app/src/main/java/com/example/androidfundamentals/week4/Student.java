package com.example.androidfundamentals.week4;

public class Student {
    private final String firstName, lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student() {
        this.firstName = "First";
        this.lastName = "Last";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
