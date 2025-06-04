package com.global.commtech.test.anagramfinder;

import java.util.Set;
import java.util.stream.Collectors;

public class AnagramChecker {

    public static boolean areAnagrams(final String word1, final String word2) {
        Set<Character> charSet1 = word1.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
        Set<Character> charSet2 = word2.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
        return charSet1.equals(charSet2);
    }
    
}
