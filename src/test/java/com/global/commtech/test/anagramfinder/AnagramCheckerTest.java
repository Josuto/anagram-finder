package com.global.commtech.test.anagramfinder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AnagramCheckerTest {
 
    @Test
    void shouldBeAnagrams() {
        assertTrue(AnagramChecker.areAnagrams("ABC", "CBA"));
        assertTrue(AnagramChecker.areAnagrams("ABCD", "DCBA"));
    }

    @Test
    void shouldNotBeAnagrams() {
        assertFalse(AnagramChecker.areAnagrams("ABC", "CBD"));
        assertFalse(AnagramChecker.areAnagrams("ABCD", "CBA"));
        assertFalse(AnagramChecker.areAnagrams("ABC", "CBAD"));
    }
}
