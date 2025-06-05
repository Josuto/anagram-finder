package com.global.commtech.test.anagramfinder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class AnagramGroupFinderTest {
    
    @Test
    void shouldThrowAnExceptionIfTheGivenListIsNull() {
        final var exception = assertThrows(NullPointerException.class, () -> AnagramGroupFinder.find(null));
        assertThat(exception.getMessage()).isEqualTo("The given list cannot be null");
    }

    @Test
    void shouldCreateEmptyGroupIfGivenListIsEmpty() {
        Map<String, Set<String>> result = AnagramGroupFinder.find(Collections.emptyList());

        assertTrue(result.isEmpty());
    }

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

    @Test
    void shouldCreateTwoGroupsForSameWordsWithDifferentLetterCases() {
        Map<String, Set<String>> actualAnagramGroups = AnagramGroupFinder.find(List.of("ABC", "abc"));
        Map<String, Set<String>> expectedAnagramGroups = new HashMap<>();
        expectedAnagramGroups.put("ABC", new HashSet<>(List.of("ABC")));
        expectedAnagramGroups.put("abc", new HashSet<>(List.of("abc")));
        
        assertEquals(expectedAnagramGroups, actualAnagramGroups);
    }

    @Test
    void shouldGroupRemovingDuplicates() {
        Map<String, Set<String>> actualAnagramGroups = AnagramGroupFinder.find(List.of("ABC", "CAB", "ABC"));
        Map<String, Set<String>> expectedAnagramGroups = new HashMap<>();
        expectedAnagramGroups.put("ABC", new HashSet<>(List.of("ABC", "CAB")));
        
        assertEquals(expectedAnagramGroups, actualAnagramGroups);
    }
}
