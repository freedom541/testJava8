package com.ccl.lambda;

/**
 * Created by ccl on 16/10/8.
 */
public class Person_1 {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public Person_1() {
    }

    public Person_1(String name, int age) {
        this.name = name;
        this.age = age;
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
}
