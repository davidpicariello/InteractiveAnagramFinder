package com.davidpicariello.wordfun;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Word wordToAnagram = promptUserForWordToAnagram();
        try {
            Anagram anagram = new Anagram(wordToAnagram);
            int lastOption = 0;
            while (!anagram.isComplete()) {
                lastOption = anagram.displayOptions(lastOption);
                Scanner scannerWord = new Scanner(System.in);
                System.out.println("\nEnter word: ");
                if (!scannerWord.hasNext()) {
                    System.out.println("End of input. Exiting.");
                    return;
                }
                String nextWord = scannerWord.next();
                if (!nextWord.equals(".")) {
                    anagram.selectWord(nextWord.toLowerCase());
                    anagram.showSelected();
                    anagram.showRemainingLetters();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Word promptUserForWordToAnagram() {
        // Enter string to anagram
        Word wordToAnagram = new Word();
        Scanner scanner = new Scanner(System.in);
        System.out.print("The phrase to anagram: ");
        wordToAnagram.requestLetters(scanner);
        wordToAnagram.toLowerCase();
        return wordToAnagram;
    }

}
