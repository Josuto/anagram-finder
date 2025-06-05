package com.global.commtech.test.anagramfinder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class AnagramGroupFinder {
    
    public static Map<String, Set<String>> find(final List<String> words) {
        Objects.requireNonNull(words, "The given list cannot be null");
        
        Map<String, Set<String>> anagramGroups = new HashMap<>();
        for (String word : words) {
            String canonicalForm = canonicalize(word);
            anagramGroups.computeIfAbsent(canonicalForm, k -> new HashSet<>()).add(word);
        }
        return anagramGroups;
    }

    private static String canonicalize(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
