package com.davidpicariello.wordfun;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WordPoolTest {
    @Test
    public void testGetList() throws Exception {
        WordPool fullDictionary = new WordPool();
        fullDictionary.buildDictionary();
        ArrayList list = fullDictionary.getList();
        assertTrue(list.size() == Dictionary.MAXNUMBEROFWORDS);
    }

    @Test
    public void testGetWordPool() {
        ArrayList<Word> list = createCustomWordList(new String[]{"hello","the","teach","test"});
        WordPool wordPool = new WordPool(list);
        wordPool = wordPool.getWordPool(1,2);
        assertEquals("the", wordPool.getList().get(0).getLetters());
        assertEquals(2, wordPool.getList().size());
    }

    @Test
    public void testGetWordPoolNoExceptionForEnd() {
        ArrayList<Word> list = createCustomWordList(new String[]{"hello","the","teach","test"});
        WordPool wordPool = new WordPool(list);
        wordPool = wordPool.getWordPool(1,50000);
        assertEquals("the", wordPool.getList().get(0).getLetters());
        assertEquals(3, wordPool.getList().size());
    }

    @Test
    public void testGetWordPoolNoExceptionForStart() {
        ArrayList<Word> list = createCustomWordList(new String[]{"hello","the","teach","test"});
        WordPool wordPool = new WordPool(list);
        wordPool = wordPool.getWordPool(1000,1001);
        assertEquals(0, wordPool.getList().size());
    }

    @Test
    public void filterNormalList() throws Exception {
        Word entry = new Word("teacher");
        ArrayList<Word> list = createCustomWordList(new String[]{"hello","the","teach","test"});
        WordPool wordPool = new WordPool(list);
        wordPool.filterList(entry);
        assertEquals("the", wordPool.getList().get(0).getLetters());
        assertEquals("teach", wordPool.getList().get(1).getLetters());
        assertEquals(2, wordPool.getList().size());
    }

    @Test
    public void filterListNoResults() throws Exception {
        Word entry = new Word("xyz");
        ArrayList<Word> list = createCustomWordList(new String[]{"hello","the","teach","test"});
        WordPool wordPool = new WordPool(list);
        wordPool.filterList(entry);
        assertEquals(0, wordPool.getList().size());
    }


    @Test
    public void filterDuplicateLettersNoResults() throws Exception {
        Word entry = new Word("aa");
        ArrayList<Word> list = createCustomWordList(new String[]{"hello","the","teach","test","azzzz"});
        WordPool wordPool = new WordPool(list);
        wordPool.filterList(entry);
        assertEquals(0, wordPool.getList().size());
    }

    @Test
    public void filterDuplicateLettersWithResults() throws Exception {
        Word entry = new Word("aaaz");
        ArrayList<Word> list = createCustomWordList(new String[]{"hello","the","teach","test","aaa"});
        WordPool wordPool = new WordPool(list);
        wordPool.filterList(entry);
        assertEquals(1, wordPool.getList().size());
    }

    @Test
    public void testShowListTwoColumns() {
        ArrayList<Word> list = createCustomWordList(new String[]{
                "rowOneColumnOne","rowOneColumnTwo",
                "row2Column1","rowTwoColumnTwo",
                "rowThreeColumnOne","rowThreeColumnTwo"
        });
        WordPool wordPool = new WordPool(list);
        wordPool.showList(2);
    }

    @Test
    public void testShowListFourColumns() {
        ArrayList<Word> list = createCustomWordList(new String[]{
                "rowOneColumnOne","rowOneColTwo","rowOneColumnThree","rowOneColumnFour",
                "rowTwoColumnOne","rowTwoColumnTwo","rowOneColumn3","rowOneColumnFour"
        });
        WordPool wordPool = new WordPool(list);
        wordPool.showList(4);
    }

    @Test
    public void testAdd() {
        ArrayList<Word> list = createCustomWordList(new String[]{"hello","the","teach","test"});
        WordPool wordPool = new WordPool(list);
        wordPool.add(new Word("abc"));
        wordPool.add(new Word("xyz"));
        Assert.assertEquals(6, wordPool.getList().size());
        Assert.assertEquals("abc", wordPool.getList().get(4).getLetters());
        Assert.assertEquals("xyz", wordPool.getList().get(5).getLetters());
    }

    private ArrayList<Word> createCustomWordList(String[] wordsToAdd) {
        ArrayList<Word> list = new ArrayList<>();
        for (String letters : wordsToAdd) {
            Word word = new Word();
            word.setLetters(letters);
            list.add(word);
        }
        return list;
    }
}
