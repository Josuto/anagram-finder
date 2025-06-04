package com.global.commtech.test.anagramfinder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class AnagramGroupFinder {
    
    public static Map<String, Set<String>> find(final List<String> words) {
        return find(words, new HashMap<>());
    }

    private static Map<String, Set<String>> find(final List<String> remainingWords, final Map<String, Set<String>> anagramGroups) {
        for (String word : remainingWords) {
            boolean doesAnagramExist = false;
            for (Entry<String, Set<String>> anagramGroup : anagramGroups.entrySet()) {
                if (AnagramChecker.areAnagrams(word, anagramGroup.getKey())) {
                    anagramGroup.getValue().add(word);
                    doesAnagramExist = true;
                    break;
                }
            }
            if (!doesAnagramExist) {
                anagramGroups.put(word, new HashSet<>(List.of(word)));
            }
            return find(remainingWords.subList(remainingWords.indexOf(word) + 1, remainingWords.size()), anagramGroups);
        }
        return anagramGroups;
    }
}
