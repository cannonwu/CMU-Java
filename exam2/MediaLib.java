//Cannon Wu
//AndrewID: clwu

package exam2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MediaLib {

	Media[] media; //to hold all types of media

	//please DO NOT change the main method
	public static void main(String[] args) {
		MediaLib mediaLib = new MediaLib();
		mediaLib.loadMedia("Media.txt");
		int index = 0;
		System.out.println("*** Welcome to MediaLib ***");
		
		for (Media m: mediaLib.media) {
			System.out.printf("%2d. %-10s %-20s \t %10s %n", ++index, m.getClass().getSimpleName(), m.title, m.year);
		}
		
		System.out.println("------------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the media number you would like to enjoy today?");
		int choice = input.nextInt();
		input.nextLine();
		input.close();
		mediaLib.selectMedia(choice);
	}

	//loadMedia reads the data from the file named filename 
	// and loads different types of media into media[] array
	void loadMedia(String filename) {
		//write your code here
		
		StringBuilder filedata = new StringBuilder();
		try {
			Scanner input = new Scanner(new File(filename));
			while (input.hasNextLine()) {
				filedata.append(input.nextLine() + "%");
			}
			String[] filecontent = filedata.toString().split("%");	//now storing entire file as single rows
			media = new Media[filecontent.length];
			String[][] fileElements = new String[filecontent.length][];
			
			for (int i = 0; i < filecontent.length; i++) {			//split each row into 2d array
				fileElements[i] = filecontent[i].split(", ");		//capturing each element into its own box
			}
			
			for (int i = 0; i < fileElements.length; i++) {
				for (int j = 0; j < fileElements[i].length; j++) {
					if (fileElements[i][j].equalsIgnoreCase("movie")) {
						media[i] = new Movie(fileElements[i][1], fileElements[i][4], fileElements[i][2], Integer.parseInt(fileElements[i][3]));
					}
					if (fileElements[i][j].equalsIgnoreCase("book")) {
						media[i] = new Book(fileElements[i][1], fileElements[i][4], fileElements[i][2], Integer.parseInt(fileElements[i][3]));
					}
					if (fileElements[i][j].equalsIgnoreCase("ebook")) {
						media[i] = new EBook(fileElements[i][1], fileElements[i][4], fileElements[i][2], Integer.parseInt(fileElements[i][3]), fileElements[i][5], Integer.parseInt(fileElements[i][6]));
					}
				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	//selectMedia takes the user choice, uses it to index into media[]
	//and invokes the media' enjoy().
	//For EBooks, it needs to invoke download() before invoking enjoy()
	void selectMedia(int choice) {
		//write your code here
		Media mediaChoice = media[choice - 1];
		if (mediaChoice instanceof Movie) {
			media[choice - 1].enjoy();
		}
		
		if (mediaChoice instanceof Book && !(mediaChoice instanceof EBook)) {
			media[choice - 1].enjoy();
		}
		if (mediaChoice instanceof EBook) {
			((EBook) media[choice - 1]).download();
			media[choice - 1].enjoy();
		}
		
	}

}
