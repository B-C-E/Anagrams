package com.pac;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SYAnagramer
{

    private Set<String> dict;

    //constructor
    public SYAnagramer(Set<String> dictionary)
    {
    this.dict = dictionary;
    }//end of constructor

    //Returns a list of all the words that can be found in the given string
    public List<String> getWords(String word)
    {
        //to letterInventory
        LetterInventory wordInv = new LetterInventory(word);

        List<String> returnList = new ArrayList<>();

        //compare with letterInventories of all english words
        for(String engWord: dict)
        {
            //if that word can be found within the given word
            if (wordInv.contains(new LetterInventory(engWord)))
            {
                returnList.add(engWord);//add it to the list
            }
        }

        //return
        return returnList;
    }//end of getWords

    //Prints all possible anagrams of a given word
    public void printAnagrams(String word)
    {
    List<String> words = getWords(word);
    //max words of -1 means print out all possible anagrams
    printWords(new LetterInventory(word),words,new ArrayList<String>(),-1);
    }//end of printAnagrams

    private void printWords(LetterInventory remainingLetters, List<String> possibleWords, List<String> currentWords,int maxWords)
    {
        //If an anagram has been found
        if (remainingLetters.isEmpty())
        {
            //print the anagram!
            System.out.print("[" + currentWords.get(0));
            for (int i = 1; i < currentWords.size(); i++)
            {
                System.out.print(", " + currentWords.get(i));
            }
            System.out.println("]");
        }

        //otherwise...
        if(maxWords==0)//if we've reached the word's per anagram limit
        {
            return;
        }
        else
        {
            maxWords--;
        }

        //try to make an anagram using every possible remaining word
        for (String word: possibleWords)
        {
            LetterInventory testWordInv = new LetterInventory(word);

            if(remainingLetters.contains(testWordInv))//if the word can be found in our remaining letters (it is a valid anagram)
            {
                currentWords.add(word);
                printWords(remainingLetters.subtract(testWordInv),possibleWords,currentWords,maxWords);
            }
        }
    }//end of printWords

    //prints all possible anagrams with no more than n words in them
    public void printLimitedAnagrams(String word, int maxWords)
    {
        List<String> words = getWords(word);

        printWords(new LetterInventory(word),words,new ArrayList<String>(),maxWords);
    }//end of printLimitedAnagrams

}//End of SYAnagramer
