//Cannon Wu
//Andrew ID: clwu

package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Anagrammar {
	String[] words;		//stores all words read from words.txt
	boolean isInDictionary; //true if the clue word exists in words.txt
	boolean hasAnagrams;	//true if the clue word has anagrams
	String[] anagramArray;	//stores all anagrams of clue-word, if found
	
	/**loadWords method reads the file and loads all words 
	 * into the words[] array */
	void loadWords(String filename) {
		//write your code here
		StringBuilder filedata = new StringBuilder();
		try {
			Scanner input = new Scanner(new File(filename));
			while (input.hasNextLine()) {
				filedata.append(input.nextLine() + ",");
			}
			words = filedata.toString().split(",");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** findAnagrams method traverses the words array and looks 
	 * for anagrams of the clue. While doing so, if the clue-word itself is found in the 
	 * words array, it sets the isInDictionary to true.
	 * 
	 * If it finds any anagram of the clue, it sets the hasAnagram to true. 
	 * It loads the anagram into the anagramArray. 
	 * If no anagrams found, then anagramArray remains an array with size 0. 
	 * */
	void findAnagrams(String clue) {
		//write your code here
		//reset
		isInDictionary = false;
		hasAnagrams = false;
		
		//check if clue word is in Dictionary
		for (int i = 0; i < words.length; i++) {
			if (words[i].equalsIgnoreCase(clue)) {
				isInDictionary = true;
			}
		}
		
		StringBuilder anagrams = new StringBuilder(); 	//load anagrams into SB and later split into anagramArray
		
		for (int i = 0; i < words.length; i++) {
			if ( (clue.length() == words[i].length()) && !(clue.equalsIgnoreCase(words[i])) ) {	//if words are same length but not same word
				char[] clueChars = clue.toLowerCase().toCharArray();
				char[] wordChars = words[i].toLowerCase().toCharArray();
				Arrays.sort(clueChars);
				Arrays.sort(wordChars);
				if (Arrays.equals(clueChars, wordChars)) {
					hasAnagrams = true;
					anagrams.append(words[i] + ",");
				}
			}
		}
		
		if (hasAnagrams) {
			anagramArray = anagrams.toString().split(",");	
		} else {
			anagramArray = new String[0];
		}
		
	}

}
