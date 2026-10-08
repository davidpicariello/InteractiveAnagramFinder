package com.davidpicariello.wordfun;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class to interact with the list of words which could be
 * a very large ArrayList
 */
public class WordPool {

    // Used to limit unintentional huge file proceesing
    private ArrayList<Word> list = new ArrayList<>();

    public WordPool() {
    }

    public WordPool(ArrayList<Word> list) {
        setList(list);
    }

    public ArrayList<Word> getList() {
        return list;
    }

    private void setList(ArrayList<Word> input) {
        this.list = input;
    }


    /** Get a new WordPool which is a subset of this
     * @param startingIndex - first Word to return
     * @param endingIndex - last Word to return
     * @return - WordPool
     */
    public WordPool getWordPool(int startingIndex, int endingIndex) {
        if (startingIndex > endingIndex) {
            throw new RuntimeException("starting index is higher than ending index");
        }

        //TODO: Is ArrayList the correct data structure? If I used List, we could use subList()
        ArrayList<Word> tempList = new ArrayList<>();
        for (int i = startingIndex; i < list.size() && i <=endingIndex; i++) {
            tempList.add(list.get(i));
        }
        return new WordPool(tempList);
    }

    public void buildDictionary() throws IOException {
        this.list = new Dictionary().getList();
    }

    public void filterList(Word remainingLetters) {
        // First, remove words that are larger than the number of remaining letters
        filterWordsByLength(remainingLetters.length());

        // Next, see if any of the letters in the word can not be found in the remaining letters
        filterWordsByRemainingLetters(remainingLetters);
    }

    private void filterWordsByLength(int sizeofRemainingLetters) {
        Iterator<Word> iterator = list.iterator();
        while (iterator.hasNext()) {
            Word nextWord = iterator.next();
            if (nextWord.getLetters().length() > sizeofRemainingLetters) {
                iterator.remove();
            }
        }
    }

    private void filterWordsByRemainingLetters(Word remainingLetters) {
        Iterator<Word> iterator = list.iterator();
        while (iterator.hasNext()) {
            Word nextWord = iterator.next();
            if (!nextWord.isASubsetOf(remainingLetters)) {
                iterator.remove();
            }
        }
    }

    public void showList(int numberOfColumns) {
        for (int i = 0, listSize = list.size(); i < listSize; i++) {
            Word word = list.get(i);
            System.out.printf("%-20s", word.getLetters());
            // Add a return character
            if (Math.floorMod(i+1,  numberOfColumns)==0) {
                System.out.println("");
            }
        }

    }

    public void add(Word selection) {
        list.add(selection);
    }
}
