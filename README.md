# Anagram Finder
A simple command line utility for finding anagrams in a specified file

## Software required to run this
* Java 17

## Building and Running the tests
```
./gradlew clean build
```

## Running the program
```
./gradlew bootRun --args="example2.txt" 
```
where example2.txt is the text file that we want to search for anagrams

## Solution Complexity Analysis

The given solution iterates over different chunks of words of the same size, sorting each of them before placing it in the resulting data structure (i.e., a `Map`). Therefore, it presents a linear time complexity with sorting per word. This is expressed in O notation as follows: 

`O(N * M log M)`, where `N` refers to the number of words and `M` refers to the average word length due to sorting during comparison.

## Chosen Data Structures

Probably the most interesting data structure to discuss here is that defining the output type of `AnagramGroupFinder.find()`, the method that specifies the most important logic of the provided command line utility. The type I'm referring to is: `Map<String, Set<String>>`.

I decided to pick Java `Map` to store the canonical version of each existing anagram in the list of given words as key and a `Set` of all words that are anagrams for such canonical version. Such a map enables a cleaner and clearer solution that best fits the problem at hand. Furthermore, as mentioned in the [Assumptions](#assumptions) section, duplicated words are ignored, thus justifying the use of the aformentioned `Set` instead of another Java collection type e.g., `List`.

## Assumptions
- Case sensitivity e.g., `ABC` is not an anagram of `abc`
- Ignore word duplication: displaying duplicated words may not be adding much value, specially when processing big production files

## Future Optimisations
- Perform checks on content of specified file e.g., prevent null values and words composed of less than two characters
- Enable parallel processing of the various chunks of words included in the file given to the command line utility