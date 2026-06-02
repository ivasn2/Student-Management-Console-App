package model;

import java.security.PublicKey;

public class Student {
    private int id;
    private String name;
    private int age;
    private int course;


    public Student(int id, String name, int age, int course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return String.format("[ID: %d] %s | Возраст: %d | Курс: %d", id, name, age, course);
    }
}


