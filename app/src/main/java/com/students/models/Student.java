package com.students.models;

/**
 * Created by rjuarez on 4/28/16.
 */
public class Student {
    private Integer id;
    private String name;
    private String surname;

    public Student(String paramName) {
        this.name = paramName;
    }

    public String getName () {
        return this.name;
    }
}
