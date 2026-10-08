package com.davidpicariello.wordfun;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary {
    // Used to limit unintentional huge file proceesing
    public static final int MAXNUMBEROFWORDS = 20000;
    private ArrayList<Word> list = new ArrayList<>();

    public Dictionary() throws IOException {
        // Load all words as part of the creation of the object
        BufferedReader inputStream = null;
        try {
            // Taken from https://github.com/first20hours/google-10000-english
            inputStream = new BufferedReader(new FileReader("20k.txt"));

            String currentWord;
            int i = 0;
            while ((currentWord = inputStream.readLine()) != null && i < MAXNUMBEROFWORDS) {
                list.add(new Word(currentWord));
                i++;
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    public ArrayList<Word> getList() {
        return list;
    }
}
