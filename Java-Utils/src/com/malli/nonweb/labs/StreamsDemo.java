package com.malli.nonweb.labs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo {
    static int counter = 0;

    public static void main(String[] args) throws Exception {
        //test1();
        //test2();
        test3();
    }

    private static void test3() {
        List<Product> productList = Arrays.asList(new Product(23, "potatoes", 45),
                new Product(14, "orange", 45), new Product(13, "lemon", 43),
                new Product(28, "bread", 45), new Product(13, "sugar", 43));

        List<Integer> collectorCollection =
                productList.stream().map(Product::getId).filter(a -> a > 20).collect(Collectors.toList());
        print(collectorCollection.stream());
        IntSummaryStatistics stats = productList.stream().collect(Collectors.summarizingInt(Product::getId));
        System.out.println(stats);
        Map<Integer, List<Product>> collectorMapOfLists = productList.stream()
                .collect(Collectors.groupingBy(Product::getPrice));
        print(collectorMapOfLists);

    }

    static class Product {

        private int id;
        private String name;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        private int price;

        public Product(int id, String name, int price) {
            this.id = id;
            this.name = name;
            this.price = price;
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


    }

    private static void test2() {

        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        counter = 0;
        Optional<String> stream = list.stream().filter(element -> {
            wasCalled();
            return element.contains("2");
        }).map(e -> {
            System.out.println("method called");
            return e.toUpperCase();
        }).findFirst();
        print(stream.stream());
    }

    private static void wasCalled() {
        counter++;
        System.out.println(counter);
    }

    public static void test1() throws Exception {
        Stream<String> streamGenerated = Stream.generate(() -> "element").limit(5);

        print(streamGenerated);
        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(5);
        print(streamIterated);

        Path path = Paths.get("C:\\Malli\\test.txt");
        Stream<String> streamOfStrings = Files.lines(path);
        print(streamOfStrings);

        Stream<String> stream1 = Stream.of("Padma", "Malli", "Yoga", "Dhanvi", "Nithya").filter(element -> element.startsWith("M"));
        //Optional<String> anyElement = stream1.findAny();
        print(stream1);

        List<String> list = Arrays.asList("abc1", "def2", "ghi3");
        Stream<String> stream2 = list.stream().skip(1)
                .map(element -> element.substring(0, 3)).sorted();
        print(stream2);

    }

    public static <T> void print(Stream<T> stream) {
        System.out.println("Printing stream....");
        stream.forEach(item -> System.out.println(item));
    }

    public static <T> void print(T t) {
        System.out.println("Value: " + t);
    }
}
