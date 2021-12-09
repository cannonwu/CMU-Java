//Cannon Wu
//AndrewID: clwu

package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Thesaurus extends WordReference{
	
	public Thesaurus(String filename) {
		Scanner tokens;
		try {
			StringBuilder filecontent = new StringBuilder();
			tokens = new Scanner(new File(filename));
			while (tokens.hasNextLine()) {
				filecontent.append(tokens.nextLine() + "\n");
			}
			String[] fileRows = filecontent.toString().split("\n");
			wordData = new String[fileRows.length][];
			for (int i = 0; i < fileRows.length; i++) {
				wordData[i] = fileRows[i].split(", ");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	String[] lookup(String word) {
		// TODO Auto-generated method stub
		StringBuilder returnString = new StringBuilder();
		for (int i = 0; i < wordData.length; i++) {
			if (wordData[i][0].equalsIgnoreCase(word)) {
				for (int j = 1; j < wordData[i].length; j++) {
					returnString.append(wordData[i][j] + ",");
				}
			}
		}
		
		if (returnString.length() > 0) {
			String[] returnArray = returnString.toString().split(",");
			return returnArray;
		} else {
			String[] returnArray = null;
			return returnArray;
		}
	}
	
}
