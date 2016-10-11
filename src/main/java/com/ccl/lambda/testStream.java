package com.ccl.lambda;

import com.ccl.bean.Person;
import com.ccl.tools.DataFactory;
import com.ccl.tools.Info;
import com.ccl.tools.Label;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by ccl on 16/10/10.
 */
public class testStream {
    //region 流的使用详解
    @Label("流的构造与转换")
    @Test
    public void createStream1(){
        // TODO: 构造流的几种常见方法
        // 1. Individual values
        Stream<String> stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        // TODO: 数值流的构造 
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        System.out.println("-----------------------");
        IntStream.range(1, 3).forEach(System.out::println);
        System.out.println("-----------------------");
        IntStream.rangeClosed(1, 3).forEach(System.out::println);

        // TODO: 流转换为其它数据结构
        // 1. Array
        String[] strArray1 = stream.toArray(String[]::new);
        // 2. Collection
        List<String> list1 = stream.collect(Collectors.toList());
        List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
        Set set1 = stream.collect(Collectors.toSet());
        Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
        // 3. String
        String str = stream.collect(Collectors.joining()).toString();

    }
    @Label("map/flatMap")
    @Test
    public void createStream2(){
        // TODO: 转换大写
        List<String> output = DataFactory.wordList.stream().
                map(String::toUpperCase).
                collect(Collectors.toList());
        output.forEach(p-> System.out.println(p));

        // TODO: 平方数
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().
                map(n -> n * n).
                collect(Collectors.toList());
        squareNums.forEach(s-> System.out.println(s));

        // TODO: 一对多
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        outputStream.forEach(s-> System.out.println(s));
        
    }
    @Label("filter")
    @Test
    public void createStream3(){
        // TODO: 留下偶数
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
        Stream.of(evens).forEach(s-> System.out.println(s));
        // TODO: 把单词挑出来
        List<String> output = DataFactory.wordList.stream().
                flatMap(line -> Stream.of(line.split(" "))).
                filter(word -> word.length() > 0).
                collect(Collectors.toList());
        output.forEach(s-> System.out.println(s));
    }
    @Label("forEach")
    @Test
    public void createStream4(){
        // TODO: 打印姓名
        DataFactory.javaProgrammers.stream()
                .filter(p -> p.getGender().equals("male"))
                .forEach(p -> System.out.println(p.getFirstName()+" " + p.getLastName()));
    }
    @Label("peek")
    @Test
    public void createStream5(){
        // TODO: peek 对每个元素执行操作并返回一个新的 Stream
        List list = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println("--------result--------------");
        list.forEach(l-> System.out.println(l));

    }
    @Label("findFirst/Optional")
    @Test
    public void createStream6(){
        // TODO: 查询第一个并输出如果为null输出 no data !
        Optional<String> optional = Stream.of("one", "two", "three", "four").findFirst();
        System.out.println(optional.orElse("no data !"));
    }
    @Label("reduce")
    @Test
    public void createStream7(){
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println(concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sumValue);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sumValue);
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
        System.out.println(concat);
    }
    @Info("limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素（它是由一个叫 subStream 的方法改名而来）。")
    @Label("limit/skip/sorted")
    @Test
    public void createStream8(){
        // TODO:  limit 和 skip 对运行次数的影响
        List<String> personList2 = DataFactory.javaProgrammers.stream().
                map(Person::getFirstName).limit(5).skip(3).collect(Collectors.toList());
        System.out.println(personList2);
        // TODO: limit 和 skip 对 sorted 后的运行次数无影响
        List<Person> personList3 = DataFactory.javaProgrammers.stream().sorted((p1, p2) ->
                p1.getFirstName().compareTo(p2.getFirstName())).limit(5).collect(Collectors.toList());
        personList3.forEach(p-> System.out.println(p));
    }
    @Label("min/max/distinct")
    @Test
    public void createStream9(){
        // TODO: 找出最长一行的长度
        int longest = DataFactory.wordList.stream().
                mapToInt(String::length).
                max().
                getAsInt();
        System.out.println(longest);
        // TODO: 找出全文的单词，转小写，并排序
        List<String> words = DataFactory.wordList.stream().
                flatMap(line -> Stream.of(line.split(" "))).
                filter(word -> word.length() > 0).
                map(String::toLowerCase).
                distinct().
                sorted().
                collect(Collectors.toList());
        System.out.println(words);
    }
    @Label("Match{allMatch/anyMatch/noneMatch}")
    @Test
    public void createStream10(){
        boolean isAllAdult = DataFactory.javaProgrammers.stream().
                allMatch(p -> p.getAge() > 18);
        System.out.println("All are adult? " + isAllAdult);
        boolean isThereAnyChild = DataFactory.javaProgrammers.stream().
                anyMatch(p -> p.getAge() < 12);
        System.out.println("Any child? " + isThereAnyChild);
        boolean isNoneChild = DataFactory.javaProgrammers.stream().
                noneMatch(p -> p.getAge() < 12);
        System.out.println("None child? " + isNoneChild);

    }

    //endregion

    //region 定义
    class Person_p{
        private int id;
        private String name;
        private int age;

        public Person_p(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }
    private class PersonSupplier implements Supplier<Person_p> {
        private int index = 0;
        private Random random = new Random();
        @Override
        public Person_p get() {
            return new Person_p(index++, "StormTestUser" + index, random.nextInt(100));
        }
    }
    //endregion

    //region 进阶：自己生成流
    @Label("Stream.generate")
    @Test
    public void createStream11(){
        // TODO: 生成 10 个随机整数
        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(10).forEach(System.out::println);
        //Another way
        IntStream.generate(() -> (int) (System.nanoTime() % 100)).
                limit(10).forEach(System.out::println);
        // TODO: 自实现 Supplier
        Stream.generate(new PersonSupplier()).
                limit(10).
                forEach(p -> System.out.println(p.getName() + ", " + p.getAge()));
    }

    @Label("Stream.iterate")
    @Test
    public void createStream12(){
        // TODO: 生成一个等差数列
        Stream.iterate(0, n -> n + 3).limit(10). forEach(x -> System.out.print(x + " "));
    }
    //endregion

    //region 进阶：用 Collectors 来进行 reduction 操作
    @Label("groupingBy/partitioningBy")
    @Test
    public void createStream13(){
        // TODO: 按照年龄归组
        Map<Integer, List<Person_p>> personGroups = Stream.generate(new PersonSupplier()).
                limit(100).
                collect(Collectors.groupingBy(Person_p::getAge));
        Iterator it = personGroups.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, List<Person_p>> persons = (Map.Entry) it.next();
            System.out.println("Age " + persons.getKey() + " = " + persons.getValue().size());
        }
        // TODO: 按照未成年人和成年人归组
        Map<Boolean, List<Person_p>> children = Stream.generate(new PersonSupplier()).
                limit(100).
                collect(Collectors.partitioningBy(p -> p.getAge() < 18));
        System.out.println("Children number: " + children.get(true).size());
        System.out.println("Adult number: " + children.get(false).size());

    }
    @Label("流转换为其它数据结构")
    @Test
    public void createStream14(){

    }
    @Label("流转换为其它数据结构")
    @Test
    public void createStream15(){

    }
    @Label("流转换为其它数据结构")
    @Test
    public void createStream16(){

    }
    @Label("流转换为其它数据结构")
    @Test
    public void createStream17(){

    }
    @Label("流转换为其它数据结构")
    @Test
    public void createStream18(){

    }
    @Label("流转换为其它数据结构")
    @Test
    public void createStream19(){

    }
    @Label("流转换为其它数据结构")
    @Test
    public void createStream20(){

    }
    //endregion
}
