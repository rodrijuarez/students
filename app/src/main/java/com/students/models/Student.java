package com.students.models;

/**
 * Created by rjuarez on 4/28/16.
 */
public class Student {
    private Integer id;
    private String name;
    private String surname;
    private byte[] photo;

    public Student(String paramName) {
        this.name = paramName;
    }

    public Student(String paramName, String paramSurname) {
        this.name = paramName;
        this.surname = paramSurname;
    }

    public Student(String paramName, String paramSurname, byte[] paramPhoto) {
        this.name = paramName;
        this.surname = paramSurname;
        this.photo = paramPhoto;
    }

    public String getName () {
        return this.name;
    }

    public String getSurname () {
        return this.surname;
    }

    public byte[] getPhoto () {
        return this.photo;
    }
}
