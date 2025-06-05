package com.global.commtech.test.anagramfinder;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class AnagramCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Assert.isTrue(args.length == 1, "Please ensure that the input file is provided");

        final File file = new File(args[0]);
        Assert.isTrue(file.exists(), args[0] + " Does not exist");

        Map<Integer, List<String>> wordGroups = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                int length = line.length();
                wordGroups.computeIfAbsent(length, k -> new ArrayList<>()).add(line);
            }
        }

        // Process one group at a time (sequentially)
        for (Map.Entry<Integer, List<String>> entry : wordGroups.entrySet()) {
            Map<String, Set<String>> anagramGroups = AnagramGroupFinder.find(entry.getValue());
            for (Set<String> anagramGroup : anagramGroups.values()) {
                System.out.println(anagramGroup);
            }
        }
    }
}
