package com.global.commtech.test.anagramfinder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class AnagramGroupFinderTest {
    
    @Test
    void shouldCreateOneGroupForTwoAnagramWords() {
        Map<String, Set<String>> actualAnagramGroups = AnagramGroupFinder.find(List.of("ABC", "CBA"));
        Map<String, Set<String>> expectedAnagramGroups = new HashMap<>();
        expectedAnagramGroups.put("ABC", new HashSet<>(List.of("ABC", "CBA")));

        assertEquals(expectedAnagramGroups, actualAnagramGroups);
    }

    @Test
    void shouldCreateOneGroupForAnyCombinationOfAThreeLetterWord() {
        Map<String, Set<String>> actualAnagramGroups = AnagramGroupFinder.find(List.of("ABC", "ACB", "BAC", "BCA", "CAB", "CBA"));
        Map<String, Set<String>> expectedAnagramGroups = new HashMap<>();
        expectedAnagramGroups.put("ABC", new HashSet<>(List.of("ABC", "ACB", "BAC", "BCA", "CAB", "CBA")));
        
        assertEquals(expectedAnagramGroups, actualAnagramGroups);
    }

    @Test
    void shouldCreateTwoGroupsForTwoDifferentSetsOfWords() {
        Map<String, Set<String>> actualAnagramGroups = AnagramGroupFinder.find(List.of("ABC", "CBA", "XYZ"));
        Map<String, Set<String>> expectedAnagramGroups = new HashMap<>();
        expectedAnagramGroups.put("ABC", new HashSet<>(List.of("ABC", "CBA")));
        expectedAnagramGroups.put("XYZ", new HashSet<>(List.of("XYZ")));
        
        assertEquals(expectedAnagramGroups, actualAnagramGroups);
    }

    @Test
    void shouldCreateThreeGroupsForThreeDifferentSetsOfWords() {
        Map<String, Set<String>> actualAnagramGroups = AnagramGroupFinder.find(List.of("ABC", "XYZ", "JLM"));
        Map<String, Set<String>> expectedAnagramGroups = new HashMap<>();
        expectedAnagramGroups.put("ABC", new HashSet<>(List.of("ABC")));
        expectedAnagramGroups.put("XYZ", new HashSet<>(List.of("XYZ")));
        expectedAnagramGroups.put("JLM", new HashSet<>(List.of("JLM")));
        
        assertEquals(expectedAnagramGroups, actualAnagramGroups);
    }
}
