/*
 * File: SpellingBee.java
 * ----------------------
 * This program contains the starter file for the SpellingBee application.
 * BE SURE TO CHANGE THIS COMMENT WHEN YOU COMPLETE YOUR SOLUTION.
 */

import edu.willamette.cs1.spellingbee.SpellingBeeGraphics;
import java.awt.Color;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class SpellingBee {

    public void run() {
        sbg = new SpellingBeeGraphics();
        score = 0;
        wordCount = 0;
        sbg.addField("Puzzle", (s) -> puzzleAction(s));
        sbg.addButton("Solve", (s) -> solveAction());
    }

    private void puzzleAction(String s) {
        
        s = s.toUpperCase();

        /* Check to see if string length is 7 */
        if (s.length() != 7)
        {
            sbg.showMessage("Puzzle must have seven letters.", Color.RED);
            return;
        }

        HashSet<Character> charSet = new HashSet<Character>();

        for (int i = 0; i < s.length(); i++) {
            /* Check to see if input character is a letter. */
            if (!Character.isLetter(s.charAt(i))) {
                sbg.showMessage("All input characters must be letters.", Color.RED);
                return;
            }

            /* Check to see if input character is a duplicate. */
            if (charSet.contains(s.charAt(i))) {
                sbg.showMessage("All input characters must be unique.", Color.RED);
                return; 
            }
            charSet.add(s.charAt(i));
        }
        sbg.showMessage("");
        sbg.setBeehiveLetters(s);
    }

    private void solveAction() {
        ArrayList<String> dictionary = readDictionary(ENGLISH_DICTIONARY);
        dictionary.forEach((word) -> {
            int output = testWord(word);
            if (output > 0) {
                score += output;
                wordCount++;
            }});
        sbg.showMessage(wordCount + " words; " + score + " points");
    }

    private ArrayList<String> readDictionary(String name) {
        ArrayList<String> output = new ArrayList<String>();
        try {
            File dictFile = new File(name);
            Scanner reader = new Scanner(dictFile);
            while (reader.hasNextLine()) {
                String wordFromDic = reader.nextLine();
                output.add(wordFromDic);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        return output;
    }

    private int testWord(String word) {
        int scoreVal = 0;   
        String beehiveLetters = sbg.getBeehiveLetters();
        
        /* Check length requirement */
        if (word.length() < 4)
            return 0;

        /* Ensure word contains center letter */
        word = word.toUpperCase();
        if (!word.contains("" + beehiveLetters.charAt(0)))
            return 0;

        /* Ensure all characters in word are beehive letters
            and determine if bonus needed */
        HashSet<Character> bonusSet = new HashSet<Character>();
        for (int i = 0; i < word.length(); i++) {
            if (!beehiveLetters.contains("" + word.charAt(i))) {
                return 0;
            }
            bonusSet.add(word.charAt(i));
        }


        /* Adjust score and print prettily */
        if (word.length() == 4)
            scoreVal += 1; 
        else 
            scoreVal += word.length();
        if (bonusSet.size() == 7) {
            scoreVal += 7;
            sbg.addWord(word + " (" + scoreVal + ")", Color.BLUE);
        }
        else {
            sbg.addWord(word + " (" + scoreVal + ")");
        }

        return scoreVal;
    }

/* Constants */

    private static final String ENGLISH_DICTIONARY = "EnglishWords.txt";

/* Private instance variables */

    private SpellingBeeGraphics sbg;
    private int score;
    private int wordCount;

/* Startup code */

    public static void main(String[] args) {
        new SpellingBee().run();
    }

}
