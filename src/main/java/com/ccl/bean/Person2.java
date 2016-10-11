package com.ccl.bean;

/**
 * Created by ccl on 16/9/7.
 */
public class Person2 {
    public int no;
    private String name;
    private int age;
    public Person2 (int no, String name, int age) {
        this.no = no;
        this.name = name;
        this.age = age;
    }
    public Person2 (int no, String name) {
        this.no = no;
        this.name = name;
    }
    public String getName() {
        System.out.println(name);
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
