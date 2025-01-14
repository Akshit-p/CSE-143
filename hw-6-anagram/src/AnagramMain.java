// AnagramMain contains a main program that prompts a user for the name of a
// dictionary file and then gives the user the opportunity to find anagrams of
// various phrases.  It constructs an Anagrams object to do the actual
// search for anagrams that match the user's phrases.

import java.io.*;
import java.util.*;

public class AnagramMain {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the CSE 143 Anagram Solver!");
        System.out.println();

        // open the dictionary file
        Scanner console = new Scanner(System.in);
        System.out.print("What is the name of the dictionary file? ");
        Scanner input = new Scanner(new File(console.nextLine()));

        // Read dictionary into a List
        List<String> dictionary = new ArrayList<String>();
        while (input.hasNextLine()) {
            dictionary.add(input.nextLine());
        }

        // Solve Anagrams
        List<String> dictionary2 = Collections.unmodifiableList(dictionary);
        
        Anagrams solver = new Anagrams(dictionary2);
        for(;;) {
            System.out.println();
            System.out.print("Phrase to Scramble (return to quit)? ");
            String phrase = console.nextLine();
            if (phrase.length() == 0)
                break;
            System.out.print("Max Words to Include (0 for no max)? ");
            int max = console.nextInt();
            console.nextLine();  // to skip newline after int
            //final double startTime = System.nanoTime();
            solver.print(phrase, max);
            //final double duration = (System.nanoTime()- startTime)/ Math.pow(10, 9);
            //System.out.println(duration);
        }
    }
}
