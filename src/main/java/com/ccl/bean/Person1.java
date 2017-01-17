package com.ccl.bean;

/**
 * Created by ccl on 16/9/6.
 */
public class Person1 {
    private String name;      //姓名
    private String location;  //地址

    public Person1(String name, String location) {
        this.name = name;
        this.location = location;
    }
    public Person1() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Person_1:" + name + "," + location;
    }
}