package com.example.nishanth.jsonparsingdemo;

/**
 * Created by nishanth on 2/10/2017.
 */

public class Person {
    String name,id;
    int department,age;

    public Person(String name, String id, int department, int age) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
