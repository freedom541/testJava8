package com.ccl.lambda;

import com.ccl.bean.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ccl on 17/1/6.
 */
public class CollectorsGroupingBy {
    public static void main(String[] args) {
        Student s1 = new Student("Ram", "A", 20);
        Student s2 = new Student("Ram", "B", 22);
        Student s3 = new Student("Mohan", "A", 22);
        Student s4 = new Student("Mohan", "A", 20);
        Student s5 = new Student("Mohan", "B", 21);
        List<Student> list = Arrays.asList(s1,s2,s3,s4,s5);
//        List<Student> list = Arrays.asList(s1);
        //Group Student on the basis of ClassName
//        System.out.println("----Group Student on the basis of ClassName----");
        Map<String, List<Student>> stdByName = list.stream()
                .collect(Collectors.groupingBy(Student::getName));
        stdByName.entrySet().stream().forEach(p->{
            Map<String, List<Student>> stdByClass = p.getValue().stream().collect(Collectors.groupingBy(Student::getClassName));
            System.out.println("key=" + p.getKey());
            System.out.println("=======================================================");
            stdByClass.entrySet().stream().forEach(l->{
                System.out.println("key="+l.getKey() + ",    " + l.getValue().stream().map(m->m.getName()).collect(Collectors.joining(",")));
            });
            System.out.println();
            System.out.println();
        });

        /*//Group Student on the basis of age
        System.out.println("----Group Student on the basis of age----");
        Map<Integer, List<Student>> stdByAge = list.stream()
                .collect(Collectors.groupingBy(Student::getAge));

        stdByAge.forEach((k,v)->System.out.println("Key:"+k+"  "+
                ((List<Student>)v).stream().map(m->m.getName()).collect(Collectors.joining(","))));*/
    }
}
