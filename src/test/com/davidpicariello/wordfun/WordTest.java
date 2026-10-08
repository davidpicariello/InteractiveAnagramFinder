package com.davidpicariello.wordfun;

import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

public class WordTest {

    private static final String INPUT = "ThisIsAString";
    private static final String LOWERCASE_INPUT = "thisisastring";

    @Test
    public void testContainsOneOfEachLetterTrue() {
        Word remainingLetters = new Word(INPUT);
        Word selection = new Word("This");
        Assert.assertTrue(selection.isASubsetOf(remainingLetters));
        // Make sure remainingLetters has not been effected
        Assert.assertEquals(INPUT, remainingLetters.getLetters());
    }

    @Test
    public void testContainsOneOfEachLetterFalse() {
        Word remainingLetters = new Word(INPUT);
        Word selection = new Word("Thisz");
        Assert.assertFalse(remainingLetters.isASubsetOf(selection));
        // Make sure remainingLetters has not been effected
        Assert.assertEquals(INPUT, remainingLetters.getLetters());
    }

    @Test
    public void testSetAndGetLetters() {
        Word remainingLetters = new Word();
        remainingLetters.setLetters(INPUT);
        Assert.assertEquals(INPUT,remainingLetters.getLetters());
    }

    @Test
    public void testLength() {
        Word remainingLetters = new Word(INPUT);
        Assert.assertEquals(INPUT.length(),remainingLetters.length());
    }

    @Test
    public void testRequestLetters() {
        Word remainingLetters = new Word(INPUT);
        Scanner scanner = new Scanner("Testing");
        remainingLetters.requestLetters(scanner);
    }

    @Test
    public void testToLowerCase() {
        Word remainingLetters = new Word(INPUT);
        Assert.assertEquals(LOWERCASE_INPUT, remainingLetters.toLowerCase().getLetters());
    }

}
