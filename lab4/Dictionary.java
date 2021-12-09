//Cannon Wu
//AndrewID: clwu

package lab4;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Dictionary extends WordReference{
	
	Dictionary(String filename) {
		
		try {
			StringBuilder filecontent = new StringBuilder();
			Scanner tokens = new Scanner(new File(filename));
			while (tokens.hasNextLine()) {
				filecontent.append(tokens.nextLine() + "\n");
			}
			String[] fileRows = filecontent.toString().split("\n"); //each row of the file 
			wordData = new String[fileRows.length][2];
			String[][] wordExtract = new String[fileRows.length][2];
			String[][] meaningExtract = new String[fileRows.length][2];
			
			for (int i = 0; i < fileRows.length; i++) {
				wordExtract[i] = fileRows[i].split(" [(]"); //each row has 2 parts, part 0 is word
			}
			
			for (int i = 0; i < fileRows.length; i++) {
				meaningExtract[i] = fileRows[i].split("[)] "); //each row has 2 parts, part 1 is meaning
			}
			
			for (int i = 0; i < wordData.length; i++) {
				wordData[i][0] = wordExtract[i][0]; //load word into array
				wordData[i][1] = meaningExtract[i][1]; //load meaning into array
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	String[] lookup(String word) {
		//returns array of meanings for the word argument
		// if word is not found return null
		StringBuilder meanings = new StringBuilder();
		for (int i = 0; i < wordData.length; i++) {
			if (word.equalsIgnoreCase(wordData[i][0])) {
				meanings.append(wordData[i][1] + "%");
			}
		}
		if (meanings.length() > 0) {
			String[] meaningsArray = meanings.toString().split("%");
			return meaningsArray;
		} else {
			return null;
		}
	}

}
