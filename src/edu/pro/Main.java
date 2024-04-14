package edu.pro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static String cleanText(String url) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(url)));
        content = content.replaceAll("[^A-Za-z ]"," ").toLowerCase(Locale.ROOT);
        return content;
    }

    public static void main(String[] args) throws IOException {

        LocalDateTime start = LocalDateTime.now();
       // Path path = Paths.get()
        String content = new String(Files.readAllBytes(Paths.get("src/edu/pro/txt/harry.txt")));

        List<String> words = Arrays.stream(content.split("[^A-Za-z]+"))
                .map(String::toLowerCase)
                .toList();

        words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(30)
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        LocalDateTime finish = LocalDateTime.now();

        System.out.println("------");
        System.out.println("Time of running: " + ChronoUnit.MILLIS.between(start, finish));
    }
}
