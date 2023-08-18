/*
 * File: SpellingBee.java
 * ----------------------
 * This program contains the starter file for the SpellingBee application.
 * BE SURE TO CHANGE THIS COMMENT WHEN YOU COMPLETE YOUR SOLUTION.
 */

import edu.willamette.cs1.spellingbee.SpellingBeeGraphics;
import java.awt.Color;
import java.util.*;

public class SpellingBee {

    public void run() {
        sbg = new SpellingBeeGraphics();
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
        String temp = "";

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

            
            temp += s.charAt(i);
        }
        sbg.showMessage("");
        sbg.setBeehiveLetters(temp);
    }

    private void solveAction() {
        sbg.showMessage("solveAction is not yet implemented", Color.RED);
    }

/* Constants */

    private static final String ENGLISH_DICTIONARY = "EnglishWords.txt";

/* Private instance variables */

    private SpellingBeeGraphics sbg;

/* Startup code */

    public static void main(String[] args) {
        new SpellingBee().run();
    }

}
