package com.ccl.tools;

import com.ccl.bean.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ccl on 16/10/11.
 */
public class DataFactory {
    public static List<Person> javaProgrammers = new ArrayList<Person>() {
        {
            add(new Person(1, "Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
            add(new Person(2, "Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
            add(new Person(3, "Floyd", "Donny", "Java programmer", "male", 33, 1800));
            add(new Person(4, "Sindy", "Jonie", "Java programmer", "female", 32, 1600));
            add(new Person(5, "Vere", "Hervey", "Java programmer", "male", 22, 1200));
            add(new Person(6, "Maude", "Jaimie", "Java programmer", "female", 27, 1900));
            add(new Person(7, "Shawn", "Randall", "Java programmer", "male", 30, 2300));
            add(new Person(8, "Jayden", "Corrina", "Java programmer", "female", 35, 1700));
            add(new Person(9, "Palmer", "Dene", "Java programmer", "male", 33, 2000));
            add(new Person(10, "Addison", "Pam", "Java programmer", "female", 34, 1300));
        }
    };

    public static List<Person> phpProgrammers = new ArrayList<Person>() {
        {
            add(new Person(1, "Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
            add(new Person(2, "Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
            add(new Person(3, "Victor", "Channing", "PHP programmer", "male", 32, 1600));
            add(new Person(4, "Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
            add(new Person(5, "Osborne", "Shad", "PHP programmer", "male", 32, 1100));
            add(new Person(6, "Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
            add(new Person(7, "Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
            add(new Person(8, "Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
            add(new Person(9, "Alvin", "Lance", "PHP programmer", "male", 38, 1600));
            add(new Person(10, "Evonne", "Shari", "PHP programmer", "female", 40, 1800));
        }
    };

    public static List<String> wordList = Arrays.asList("zhang san", "li si", "wang wu", "zhao liu", "xiao qi", "lu ba", "qi jiu", "zhang san", "li si");

}
