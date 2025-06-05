package com.global.commtech.test.anagramfinder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnagramCommandLineRunnerTest {

    @InjectMocks
    AnagramCommandLineRunner anagramCommandLineRunner;

    @Test
    void shouldPrintTheAnagramGroupsOfTheSampleFile() {
        try {
            anagramCommandLineRunner.run("src/test/resources/example1.txt");
        } catch (Exception exception) {
            fail("The happy path test case must pass");
        }
    }

    @Test
    void shouldThrowExceptionWhenNoArgumentsPresent() {
        final var exception = assertThrows(Exception.class, () -> anagramCommandLineRunner.run());
        assertThat(exception.getMessage()).isEqualTo("Please ensure that the input file is provided");
    }

    @Test
    void shouldThrowExceptionWhenMoreThanOneArgumentIsPassed() {
        final var exception = assertThrows(Exception.class, () -> anagramCommandLineRunner.run("one", "two"));
        assertThat(exception.getMessage()).isEqualTo("Please ensure that the input file is provided");
    }

    @Test
    void shouldThrowExceptionWhenInputFileDoesNotExist() {
        final var exception = assertThrows(Exception.class, () -> anagramCommandLineRunner.run("notExists"));
        assertThat(exception.getMessage()).isEqualTo("notExists Does not exist");
    }

}