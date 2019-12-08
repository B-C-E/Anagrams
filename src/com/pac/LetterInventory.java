package com.pac;

import java.util.HashMap;


//CS 145
//This Class constructs "Letter Inventories", which take in strings and record how many of each letter (from the alphabet)
//the a given string has. (caat would have two 'a's, a 'c', and a 't').
//The strings are sorted and stored in an alphabetical manner (caat --> aact)

public class LetterInventory {

    private int size;// the combined count of all the letters
    private HashMap<Character, Integer> letMap;//This map stores the counts for each letter

    // constructor - takes in a string
    public LetterInventory(String input) {
        input = input.toLowerCase();//convert input to lower case
        letMap = new HashMap<>();// make a new HashMap to store our letters

        for (int i = 0; i < input.length(); i++)// for each character in the input string
        {
            if (isALetter(input.charAt(i))) {// if a given character is a letter

                // add the letter to the HashMap

                if (letMap.containsKey(input.charAt(i)))// if our HashMap already contains that letter
                {
                    letMap.put(input.charAt(i), letMap.get(input.charAt(i)) + 1);// increment that letter's count by 1
                    size++;// increment size
                } else {// if we are adding a letter to our HashMap for the first time
                    letMap.put(input.charAt(i), 1);// set that letter's count to one
                    size++;// increment size
                }

                // end of adding the letter to the HashMap
            } // end of if a given character is a letter
        } // end of looking at each character in the input string.
    }// end of constructor

    // tells you if a character is a letter
    private static boolean isALetter(char letToTest) {
        letToTest = Character.toLowerCase(letToTest);

        // if it's within the range of valid letters
        if (letToTest <= 122 && letToTest >= 97) {//97=a, 122=z
            return true;
        }
        //otherwise
        return false;
    }// end of isALetter

    // returns the count of char letter in the LetterInventory

    public int get(char letter) {
        if (letMap.containsKey(letter)) {//if the lettermap has a letter
            return letMap.get(letter);
        }
        //rather than store zeros, this class simply has nothing if a letter's count is zero
        return 0;
    }// end of get

    //sets the count of a given letter equal to a given value

    public void set(char letter, int value) {
        if (letMap.containsKey(letter)) {// if the map already has a value for that letter
            size -= letMap.get(letter);// subtract the old value of that letter from the size
        }//end of if the map already has a value for that letter

        size += value;// add the new value to the total size
        letMap.put(letter, value);// add the letter and value

        if (letMap.get(letter) == 0)// clean up the HashMap by removing zeros
        {
            letMap.remove(letter);
        }
    }// end of set

    //returns the total summed count of all letters

    public int size() {
        return size;
    }// end of size

    //returns if the letterMap is empty

    public boolean isEmpty() {
        if (letMap.isEmpty())
        {
            return true;
        }
        return false;
    }// end of isEmpty


    public String toString() {
        String toReturn = "";
        for (int i = 97; i <= 122; i++)// for each letter in alphabet (from a to z)
        {
            if (letMap.containsKey(toChr(i)))// if the map contains that letter
            {
                for (int j = 0; j < letMap.get(toChr(i)); j++)// for the count of that letter
                {
                    toReturn += toChr(i); // add that letter to the return string
                } // end of for the count of that letter
            } // end of if the map contains that letter
        } // end of each letter in alphabet
        return toReturn;
    }// end of toString

    // returns a duplicate letter inventory

    public LetterInventory clone() {
        return new LetterInventory(this.toString());
    }// end of clone

    // casts an int to a char (used for visual clarity)
    public char toChr(int input) {
        return (char) input;
    }// end of toChr


    // adds two LetterInventories together, and returns their combined sums
    public LetterInventory add(LetterInventory other) {
        // clone the original list
        LetterInventory toReturn = this.clone();
        // end of cloning the original list

        for (int i = 97; i <= 122; i++)// for each letter in alphabet (from a to z)
        {

            if (other.letMap.containsKey(toChr(i))) {// if the other map has a value for that letter

                if (this.letMap.containsKey(toChr(i)))// if we already have a value for that letter
                {
                    toReturn.set(toChr(i), toReturn.get(toChr(i)) + other.get(toChr(i)));// add them together
                } // end of if we already have a value for that letter
                else {
                    toReturn.set(toChr(i), other.get(toChr(i)));
                }
            } // end of if the other map has a value for that letter
        } // end of each letter in alphabet

        return toReturn;
    }// end of add


    public LetterInventory subtract(LetterInventory other) {
        // clone this LetterInventory
        LetterInventory toReturn = this.clone();

        for (int i = 97; i <= 122; i++) {
            if (other.letMap.containsKey(toChr(i)))// if the other map has a letter
            {
                if (letMap.containsKey(toChr(i)))// if our map also has that letter
                {
                    // return either the difference between the two counts, or 0 (if the difference
                    // is negative)
                    toReturn.set(toChr(i), Math.max(0, toReturn.get(toChr(i)) - other.get(toChr(i))));
                } // end of if our map also has that letter
                // whenever the other map wants to subtract a letter, but this map doesn't have
                // that letter, the answer would just be 0
                // since we don't include negatives, so we don't have to do anything here
            } // end of if the other map has a letter (to subtract)
        }
        return toReturn;
    }// end of subtract

    //does this letter inventory contain another?
    public boolean contains(LetterInventory other)
    {
        //If the other one has more letters, it can't be a subset of this inventory
        if(other.size>this.size())
        {
            return false;
        }

        //look through all of our letters
        for(char letter :other.letMap.keySet())
        {
            //if the other map has more of one letter than this map, it is not a subset
            if(this.get(letter) < other.get(letter))
            {
            return false;
            }
        }

        //if the other map is indeed contained
        return true;
    }
}//end of LetterInventory