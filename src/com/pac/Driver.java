package com.pac;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

//This class enables the user to find Anagrams of Words
public class Driver
{

    //Main driver
    public static void main(String args[])
    {
        //VARIABLES
        boolean b_readyToExit = false;


        //INTRO
        System.out.println("Hello and welcome to the Anagram Solver!\n");


        //Set Up Dictionary
        Set<String> dictionary = null;
        System.out.println("Please place a file named \"dictionary.txt\" into your downloads folder");


        System.out.println("Has the file been placed?");
        while (GetInput.getYN() != 'y')
        {
            System.out.println("Please place the file.");
            System.out.println("Has the file been placed?");
        }
        try
        {
            // open the dictionary file and read dictionary into an HashSet
            Path location = Paths.get(System.getProperty("user.home") + "/Downloads/dictionary.txt");
            Scanner fileScanner = new Scanner(location.toFile());
            Set<String> dictionarySetup = new HashSet<String>();
            while (fileScanner.hasNext())
            {
                dictionarySetup.add(fileScanner.next());
            }

            dictionary = Collections.unmodifiableSet(dictionarySetup);
        }
        catch (Exception e)
        {
            System.out.println("The file does not exist. Please try again...  :(");
            return;
        }



        //LOOP
        System.out.println();
        System.out.println("Would you like to generate anagrams?");

        if(GetInput.getYN() == 'y')//if they want to generate anagrams
        {
            solveAnagram(dictionary);//this will loop
        }//end of if they want to generate anagrams
        //EXIT MESSAGE

        System.out.println("\nGoodbye! Have a nice one!");

    }//END OF MAIN


    public static void solveAnagram(Set<String> dictionary)
    {
        boolean readyToQuit = false;

        while (!readyToQuit)
        {
            System.out.println("Which phrase would you find anagrams for?");
            String input = GetInput.getString();
            System.out.println("\nHere is a list of all words found within [" + input + "]");
            SYAnagramer solver = new SYAnagramer(dictionary);

            List<String> words = solver.getWords(input);

            if (words.isEmpty())//if the solver could find no valid words
            {
                System.out.println("No words found...");
            }
            else // if the solver found valid words
            {
                //print the words
                int charCount = 0; //every 100 ish characters goe down a line
                System.out.print("\n[ ");

                for (int i = 1; i < words.size(); i++)
                {
                    charCount+=(words.get(i) +", ").length();
                    if (charCount > 100)
                    {
                        charCount = 0;
                        System.out.println();
                    }

                    System.out.print(words.get(i) + ", ");

                }
                System.out.println(words.get(words.size()-1)+ " ]");

                //end of printing words

                System.out.println("\nWould you like to display a list of all possible anagrams in [" + input +
                        "], \nor all anagrams containing fewer than a maximum number of words?");
                System.out.println("[a] All Anagrams");
                System.out.println("[m] Only those with fewer than a max number of words");

                if(GetInput.getChar() == 'a')
                {
                    System.out.println("\n All possible Anagrams:");
                    long startTime = System.currentTimeMillis();//start time
                    solver.printAnagrams(input);
                    System.out.println(System.currentTimeMillis() - startTime + " ms elapsed.");//display how long it took
                }
                else
                {
                    System.out.println("What is the maximum number of words you want to have in your anagrams?");
                    int maxNumb = GetInput.getRangeInt(1,input.length());
                    System.out.println("\nAll Anagrams with " + maxNumb + " or fewer words in them:");

                    long startTime = System.currentTimeMillis();//start time
                    solver.printLimitedAnagrams(input,maxNumb);
                    System.out.println(System.currentTimeMillis() - startTime + " ms elapsed.");//display how long it took
                }
            }//end of solving one anagram

            System.out.println("\nSolve another?");
            readyToQuit = GetInput.getYN() == 'n';
        }
    }//end of solveAnagram

}//END OF DRIVER