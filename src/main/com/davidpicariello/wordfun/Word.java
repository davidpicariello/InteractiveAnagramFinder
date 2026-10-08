package com.davidpicariello.wordfun;

import java.util.Scanner;

public class Word {

    private String letters;

    public Word(String letters) {
        this.letters = letters;
    }

    /**
     * Copy constructor
     * @param original - Word object to copy
     */
    private Word(Word original) {
        this.letters = original.getLetters();
    }

    public Word() {

    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String input) {
        letters = input;
    }

    public void requestLetters(Scanner scanner) {
        setLetters(scanner.nextLine());
    }

    public int length() {
        return letters.length();
    }

    /**
     * @param remainingLetters - full list of letters remaining to use
     * @return - true if remainingLetters contains all letters in this.letters
     */
    public boolean isASubsetOf(Word remainingLetters) {
        // Copy remaining letters so we don't edit it
        Word tempRemainingLetters = new Word(remainingLetters);
        return tempRemainingLetters.removeLetters(letters);
    }

    /**
     * @param lettersToRemove - remove this string from this Word
     * @return - return true if all letters are found once
     */
    boolean removeLetters(String lettersToRemove) {
        for (int i = 0; i < lettersToRemove.length(); i++) {
            String currentLetter = lettersToRemove.substring(i, i + 1);

            int letterIndex = letters.indexOf(currentLetter);
            if (letterIndex >= 0) {
                // found current letter remove
                String letter = letters.substring(letterIndex,letterIndex+1);
                letters = letters.replaceFirst(letter,"");
            } else {
                return false;
            }
        }
        return true;
    }

    /** Lowercase the word. Returns the word object as well
     * @return the current word, but lowercased
     */
    public Word toLowerCase() {
        letters = letters.toLowerCase();
        return this;
    }
}
