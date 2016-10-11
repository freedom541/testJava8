package com.ccl.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by ccl on 16/9/5.
 */
public class testLambda2 {

    @Test
    public void testThread() {
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.stream().filter(f->f.equals("Lambdas")).forEach(f-> System.out.println(f));

    }

    @Test
    public void testString(){
        List<String> list = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        list.stream().map(l->changeString(l)).forEach(l-> System.out.println(l));
        System.out.println("----------------------");
        list.stream().map(l->l+"-"+2).forEach(l-> System.out.println(l));
    }

    public String changeString(String str){
        return str + "-" + 1;
    }

    @Test
    public void testMap(){
        List<Person_1> list = new ArrayList<>();
        for (int i=0; i<5; i++){
            Person_1 p = new Person_1();
            p.setName("java" + i);
            p.setAge(i+5);
            list.add(p);
        }

        List<String> list1 = list.stream().map(l->l.getName()).collect(Collectors.toList());
        list1.forEach(l-> System.out.println(l));
    }

    @Test
    public void testOptional(){
        Person_1 p = new Person_1();
        p.setAge(111);
        p.setName("woshishui");
        Optional<Person_1> o = Optional.of(p);
        System.out.println(o.orElse(new Person_1("zhangsan",121)).getName());
        o = Optional.ofNullable(null);
        System.out.println(o.orElse(new Person_1("zhangsan",121)).getName());
        o = Optional.ofNullable(null);
        System.out.println(o.map(r->r.getName()).orElse("shiwo"));
    }
}
