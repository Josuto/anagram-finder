package com.global.commtech.test.anagramfinder;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class AnagramCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        this.checkPreconditionsOverArguments(args);
        Set<Integer> uniqueWordLengths = this.discoverUniqueWordLengths(args[0]);
        for (int currentLength : uniqueWordLengths) {
            this.processWordsOfCurrentLength(args[0], currentLength);
        }
    }

    private void checkPreconditionsOverArguments(String... args) {
        Assert.isTrue(args.length == 1, "Please ensure that the input file is provided");

        final File file = new File(args[0]);
        Assert.isTrue(file.exists(), args[0] + " Does not exist");
    }

    private Set<Integer> discoverUniqueWordLengths(final String filePath) throws Exception {
        Set<Integer> uniqueWordLengths = new TreeSet<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim();
                if (!word.isEmpty()) {
                    uniqueWordLengths.add(word.length());
                }
            }
            return uniqueWordLengths;
        }
    }

    private void processWordsOfCurrentLength(final String filePath, int length) throws Exception {
        List<String> wordsOfCurrentLength = this.loadWordsOfGivenLengthFrom(filePath, length);
        Map<String, Set<String>> anagramGroups = AnagramGroupFinder.find(wordsOfCurrentLength);
        for (Set<String> anagramGroup : anagramGroups.values()) {
            System.out.println(anagramGroup);
        }
        wordsOfCurrentLength = null; // enable GC to delete the processed words from memory
    }

    private List<String> loadWordsOfGivenLengthFrom(final String filePath, final int length) throws Exception {
        List<String> wordsOfCurrentLength = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim();
                if (!word.isEmpty() && word.length() == length) {
                    wordsOfCurrentLength.add(word);
                }
            }
            return wordsOfCurrentLength;
        }
    }
}
