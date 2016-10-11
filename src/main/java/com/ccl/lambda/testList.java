package com.ccl.lambda;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by ccl on 16/9/5.
 */
public class testList {
    @Test
    public void testlist1() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);

        System.out.println("--------------------------------");

        list.forEach(o -> {
            System.out.println(o);
        });

        System.out.println("--------------------------------");

        list.forEach(o -> {
            if (o > 5) {
                System.out.println(o);
            }
        });

        System.out.println("--------------------------------");

        List<Integer> rList = list.stream().filter(o -> o < 6).collect(Collectors.toList());
        System.out.println(rList);
    }

    @Test
    public void testList2() {
        List<String> list = new ArrayList<>();
        for (int i = 100; i <= 200; i = i + 20) {
            list.add(String.valueOf(i));
        }
        System.out.println(list);

        System.out.println("---------------------------------------");

        List<Integer> r = list.stream().map(e -> new Integer(e)).filter(e -> e > 160).distinct().collect(Collectors.toList());
        System.out.println(r);

        System.out.println("---------------------------------------");

        Map<Integer, Integer> m = list.stream().map(e -> new Integer(e)).filter(e -> e < 160).collect(Collectors.groupingBy(p -> p, Collectors.summingInt(p -> 1)));
        System.out.println(m);


    }

    @Test
    public void testString() {
        List<String> a = Arrays.asList("we i di ao is great".split(" "));
        a.forEach((s) -> System.out.println(s));// 表达式
        a.forEach((String s) -> {
            System.out.println(s);
        });// 语句块
        a.forEach(System.out::println);// 函数

    }

    @Test
    public void testString2() {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        //查找以a开头的
        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

        //排序
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
        //map映射成大写
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);
        //计数
        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();
        System.out.println(startsWithB);
        //reduce 规约
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);

    }

    //串行排序
    @Test
    public void testStreamTime(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
    }
    //并行排序
    @Test
    public void testStreamTime2(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }
}
