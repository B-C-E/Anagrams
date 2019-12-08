package com.pac;


import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//This class enables the user to find Anagrams of Words
public class Driver
{

    //Main driver
    public static void main(String args[])
    {
        //VARIABLES
        boolean b_readyToExit = false;


        //INTRO
        System.out.println("Hello and welcome to the Anagram Solver!");


        //Set Up Dictionary
        Set<String> dictionary = Collections.unmodifiableSet(new HashSet<>());

        //LOOP
        while (!b_readyToExit)
        {
            //SELECTIONS
            System.out.println();
            System.out.println("Would you like to create an Anagram?");

            //if they don't want to do anagram things
            if (GetInput.getYN() == 'n')
            {
                b_readyToExit = true;//exit!
            } else
            {
                solveAnagram(dictionary);
            }

        }
        //EXIT MESSAGE
        System.out.println("Goodbye! Have a nice one!");

    }//END OF MAIN


    public static void solveAnagram(Set<String> dictionary)
    {
        boolean readyToQuit = false;

        while (!readyToQuit)
        {
            System.out.println("Which word would you find anagrams for?");
            String input = GetInput.getString();
            System.out.println("Here is a list of all words found within " + input);
            SYAnagramer solver = new SYAnagramer(dictionary);

            List<String> words = solver.getWords(input);

            if (words.isEmpty())//if the solver could find no valid words
            {
                System.out.println("No words found...");
            }
            else // if the solver found valid words
            {
                System.out.print("[" + words.get(0));

                for (int i = 1; i < words.size(); i++)
                {
                    System.out.print(", " + words.get(i));
                }

                System.out.println();
                System.out.println("Would you like to display a list of all possible anagrams in " + input +
                        ", \n or all anagrams containing fewer than a maximum number of words?");
                System.out.println("[a] All Anagrams");
                System.out.println("[m] Only those with fewer than a max number of words");

                if(GetInput.getChar() == 'a')
                {
                    System.out.println("\n All possible Anagrams:");
                    solver.printAnagrams(input);
                }
                else
                {
                    System.out.println("What is the maximum number of words you want to have in your anagrams?");
                    int maxNumb = GetInput.getRangeInt(1,input.length());
                    System.out.println("\n All Anagrams with " + maxNumb + " or fewer words in them:");
                    solver.printLimitedAnagrams(input,maxNumb);
                }
            }//end of solving one anagram

            System.out.println("Solve another?");
            readyToQuit = GetInput.getYN() == 'n';
        }
    }//end of solveAnagram

}//END OF DRIVER