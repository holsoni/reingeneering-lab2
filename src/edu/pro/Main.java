package edu.pro;

/*
    @author   soniakk
    @class  Main
    @version  1.0
    @project   reingeneering-lab2
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final String NON_ALPHABETIC_PATTERN = "[^A-Za-z ]";
    private static final String NON_NUMERIC_PATTERN = "[^0-9]";
    private static final String FILE_PATH = "src/edu/pro/txt/harry.txt";
    private static final int SHOW_LIMIT = 30;

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        Map<String, Integer> wordFrequency = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            Scanner scanner;
            while (br.ready()) {
                scanner = new Scanner(br.readLine().replaceAll(NON_ALPHABETIC_PATTERN, " ").toLowerCase(Locale.ROOT));
                while (scanner.hasNext()) {
                    wordFrequency.put(scanner.next(), wordFrequency.getOrDefault(scanner.next(), 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            e.printStackTrace();
        }

        wordFrequency.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .forEach(System.out::println);

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryBean.getNonHeapMemoryUsage();

        System.out.println("Heap Memory Usage:");
        System.out.println("  Init: " + heapMemoryUsage.getInit());
        System.out.println("  Used: " + heapMemoryUsage.getUsed());
        System.out.println("  Committed: " + heapMemoryUsage.getCommitted());
        System.out.println("  Max: " + heapMemoryUsage.getMax());

        System.out.println("\nNon-Heap Memory Usage:");
        System.out.println("  Init: " + nonHeapMemoryUsage.getInit());
        System.out.println("  Used: " + nonHeapMemoryUsage.getUsed());
        System.out.println("  Committed: " + nonHeapMemoryUsage.getCommitted());
        System.out.println("  Max: " + nonHeapMemoryUsage.getMax());

    }
}
