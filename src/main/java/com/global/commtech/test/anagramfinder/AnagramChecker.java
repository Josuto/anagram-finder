package com.global.commtech.test.anagramfinder;

import java.util.HashMap;
import java.util.Map;

public class AnagramChecker {

    public static boolean areAnagrams(final String word1, final String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        Map<Character, Integer> freqMap1 = buildFrequencyMap(word1);
        Map<Character, Integer> freqMap2 = buildFrequencyMap(word2);
        return freqMap1.equals(freqMap2);
    }

    private static Map<Character, Integer> buildFrequencyMap(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        return freqMap;
    }
    
}
