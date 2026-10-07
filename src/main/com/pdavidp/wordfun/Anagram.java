package main.com.pdavidp.wordfun;

import java.io.IOException;

class Anagram {

    private static final int NUMBEROFROWS = 5;
    private static final int NUMBEROFCOLUMNS = 5;
    private static final String WORD_NOT_FOUND = "Your word is not in the remaining letters.";
    private static final String SHOWING_FROM_TO = "Showing words %s to %s of %s.";
    private boolean resetLastOption, complete;
    private Word remainingLetters = new Word();
    private WordPool availableOptions = new WordPool();
    private WordPool wordsSelected = new WordPool();

    Anagram(Word input) throws IOException {
        remainingLetters.setLetters(input.getLetters().replaceAll(" ", ""));
        complete = false;
        availableOptions.buildDictionary();
        availableOptions.filterList(remainingLetters);
        resetLastOption = true;
    }

    /**
     * Display a list of options available
     *
     * @param currentWord zero-based index of the last option shown
     */
    int displayOptions(int currentWord) {
        int startIndex;
        if (resetLastOption) {
            startIndex = 0;
            resetLastOption = false;
        } else {
            startIndex = currentWord + 1;
        }

        final int pageSize = NUMBEROFCOLUMNS * NUMBEROFROWS;
        final int listSize = availableOptions.getList().size();

        if (listSize == 0) {
            System.out.println("No matching words.");
            return -1;
        }

        if (startIndex >= listSize) {
            System.out.println("No more words to show.");
            return listSize - 1;
        }

        int showTo = Math.min(startIndex + pageSize, listSize) - 1;
        String morePrompt = showTo + 1 < listSize ? " Enter \".\" to show more." : "";
        System.out.printf(SHOWING_FROM_TO + "%s%n", startIndex + 1, showTo + 1, listSize, morePrompt);

        WordPool optionsToShow = availableOptions.getWordPool(startIndex, showTo);
        optionsToShow.showList(NUMBEROFCOLUMNS);

        return showTo;
    }


    void selectWord(String userEntry) {
        Word selection = new Word(userEntry);
        if (selection.isASubsetOf(remainingLetters)) {
            remainingLetters.removeLetters(selection.getLetters());
            availableOptions.filterList(remainingLetters);
            wordsSelected.add(selection);
        } else {
            System.out.println(WORD_NOT_FOUND);
        }
        resetLastOption = true;

        if (remainingLetters.length()==0) {
            complete = true;
        }
    }

    void showSelected() {
        StringBuilder allWords = new StringBuilder();
        for (Word word : wordsSelected.getList()) {
            allWords.append(word.getLetters()).append("  ");
        }
        System.out.println("Words selected: " + allWords);
    }

    boolean isComplete() {
        return complete;
    }

    void showRemainingLetters() {
        if (remainingLetters.length() > 0 ) {
            System.out.println("Letters remaining: " + remainingLetters.getLetters());
        }
    }
}
