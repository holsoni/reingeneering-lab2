package edu.pro;

/*
    @author   soniakk
    @class  Main
    @version  1.0
    @project   reingeneering-lab2
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final String NON_ALPHABETIC_PATTERN = "[^A-Za-z ]";
    private static final String NON_NUMERIC_PATTERN = "[^0-9]";
    private static final String FILE_PATH = "src/edu/pro/txt/harry.txt";
    private static final int SHOW_LIMIT = 30;

    public static void main(String[] args) throws IOException {

        LocalDateTime start = LocalDateTime.now();

        String content = cleanText(FILE_PATH);

        String[] words = content.split(SPACE);

        Map<String, Long> wordsAmount = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        String[] distincts = wordsAmount.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(30)
                .map(entry -> entry.getKey() + EMPTY_STRING + entry.getValue())
                .toArray(String[]::new);

        //This is another possible way to do this

        /* Arrays.sort(words);
        StringBuilder distinctString = new StringBuilder(SPACE);

        for (String word : words) {
            if (!distinctString.toString().contains(word)) {
                distinctString.append(word).append(SPACE);
            }
        }

        String[] distinctWords = distinctString.toString().split(SPACE);
        int[] freq = new int[distinctWords.length];

        for (int i = 0; i < distinctWords.length ; i++) {
            int count = 0;
            for (String word : words) {
                if (distinctWords[i].equals(word)) {
                    count++;
                }
            }
            freq[i] = count;
        }

        for (int i = 0; i < distinctWords.length ; i++) { // 5 000
            distinctWords[i] += SPACE + freq[i];
        }

        Arrays.sort(distinctWords, Comparator.comparing(str
                -> Integer.valueOf(str.replaceAll(NON_NUMERIC_PATTERN, EMPTY_STRING))));*/

        for (int i = 0; i < SHOW_LIMIT; i++) {
            System.out.println(distincts[i]);
        }
        LocalDateTime finish = LocalDateTime.now();

        System.out.println("------");
        System.out.println(ChronoUnit.MILLIS.between(start, finish));
    }

    private static String cleanText(String url) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(url)));
        content = content.replaceAll(NON_ALPHABETIC_PATTERN, SPACE).toLowerCase(Locale.ROOT);
        return content;
    }
}
