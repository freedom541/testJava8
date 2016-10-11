package com.ccl.lambda;

import com.ccl.bean.Person2;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by ccl on 16/9/5.
 */
public class testLambda {
    @Test
    public void testThread() {
        // Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();
        //Java 8方式：
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();

    }

    @Test
    public void testString() {
        // Java 8之前：
        List<String> features1 = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features1) {
            System.out.println(feature);
        }
        System.out.println("--------------Java 8之后--------------");
        // Java 8之后：
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);

    }

    @Test
    public void testMap() {
        // 不使用lambda表达式为每个订单加上12%的税
        List<Integer> costBeforeTax1 = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax1) {
            double price = cost + .12 * cost;
            System.out.println(price);
        }
        System.out.println("--------------Java 8之后--------------");
        // 使用lambda表达式
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
    }

    @Test
    public void testReduce() {
        // 为每个订单加上12%的税
        // 老方法：
        List<Integer> costBeforeTax1 = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax1) {
            double price = cost + .12 * cost;
            total = total + price;
        }
        System.out.println("Total : " + total);
        System.out.println("--------------Java 8之后--------------");
        // 新方法：
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);

        int bill2 = costBeforeTax.stream().reduce((sum, cost) -> sum + cost).get();
        System.out.println(bill2);
    }

    @Test
    public void testDateTime(){
        DateTime time = new DateTime(DateTimeZone.UTC);
        System.out.println(time);
        System.out.println(time.getYear());
        System.out.println(time.getMonthOfYear());
        System.out.println(time.getDayOfMonth());
    }

    @Test
    public void testCollection(){
        List<String> list = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        list.sort(Comparator.comparing(s->s));
        list.forEach(s-> System.out.println(s));
    }

    @Test
    public void testPerformance(){
        long t0 = System.nanoTime();

        //初始化一个范围100万整数流,求能被2整除的数字，toArray（）是终点方法

        int a[]= IntStream.range(0, 1_000_000).filter(p -> p % 2==0).toArray();

        long t1 = System.nanoTime();

        //和上面功能一样，这里是用并行流来计算

        int b[]=IntStream.range(0, 1_000_000).parallel().filter(p -> p % 2==0).toArray();

        long t2 = System.nanoTime();

        //我本机的结果是serial: 0.06s, parallel 0.02s，证明并行流确实比顺序流快

        System.out.printf("serial: %.2fs, parallel %.2fs%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9);
    }
    @Test
    public void testPeek(){
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        System.out.println("-------------------------------------------------------");
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .collect(Collectors.toList());

    }
    @Test
    public void testSorted(){
        List<Person2> persons = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Person2 person = new Person2(i, "name" + i);
            persons.add(person);
        }
        List<Person2> personList2 =
                persons.stream()
                        .limit(2)
                        .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                        .collect(Collectors.toList());
        System.out.println(personList2);
    }
    @Test
    public void testMatch(){
        List<Person2> persons = new ArrayList();
        persons.add(new Person2(1, "name" + 1, 10));
        persons.add(new Person2(2, "name" + 2, 21));
        persons.add(new Person2(3, "name" + 3, 34));
        persons.add(new Person2(4, "name" + 4, 6));
        persons.add(new Person2(5, "name" + 5, 55));

        boolean isAllAdult = persons.stream().
                allMatch(p -> p.getAge() > 18);
        System.out.println("All are adult? " + isAllAdult);
        boolean isThereAnyChild = persons.stream().
                anyMatch(p -> p.getAge() < 12);
        System.out.println("Any child? " + isThereAnyChild);

    }
    @Test
    public void testStream_generate() {
        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(10).forEach(System.out::println);
        System.out.println("-------------------------------------------");
        //Another way
        IntStream.generate(() -> (int) (System.nanoTime() % 100)).
                limit(10).forEach(System.out::println);

    }

    @Test
    public void testMapList(){
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(id + "," + val));
        System.out.println("--------------------------------");
        map.forEach((k,v) -> {
            if (k > 5){
                System.out.println(k + "," + v);
            }
        });

    }

    @Test
    public void testThread2(){

        for (int i= 0;i<5;i++){
            final int finalI = i;
            new Thread(()->{
                System.out.println("=========" + finalI);
            }).start();
        }
    }

    @Test
    public void testDatetime(){
        DateTime nowTime = new DateTime(DateTimeZone.UTC);
        System.out.println(nowTime.minusDays(60));
    }

    @Test
    public void testInteger(){
        String str = "21474836480";
        String str2= "2147483647";
        System.out.println(str.length());
        Integer vl = Integer.parseInt(str2);
        Long l = Long.parseLong(str);
        System.out.println(l);
        System.out.println(vl);
    }

}
