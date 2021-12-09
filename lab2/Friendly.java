package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Friendly {

	String[] persons;
	String[][] personFriends;

	public static void main(String[] args) {
		Friendly friendly = new Friendly();
		friendly.readFriends("friends.txt");
		friendly.getInputOutput();
	}

	//do not change this method
	void getInputOutput() {
		int choice = 0;
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("*** Welcome to Friendly! ***");
			System.out.println("1. Find the number of friends a person has");
			System.out.println("2. Find the number of common friends between two persons");
			System.out.println("3. Find the names of common friends between two persons");
			System.out.println("4. Exit");
			choice = input.nextInt();
			input.nextLine(); //clear the buffer
			switch (choice) {
			case 1: {
				System.out.println("Enter the person's name"); 
				String name = input.nextLine();
				String[] friends = findFriends(name);
				if (friends != null) {
					System.out.printf("%s has %d friends%n", name, friends.length);
					int count = 0;
					for (String s : friends ) {
						System.out.println(++count + ". " + s );
					}
				} else System.out.println("Sorry! No friends found!");
				System.out.println("-----------------------------");
				break;
			}
			case 2: {
				System.out.println("Enter first person's name");
				String name1 = input.nextLine();
				System.out.println("Enter second person's name");
				String name2 = input.nextLine();
				System.out.printf("%s and %s have %d common friends%n", name1, name2, countCommonFriends(name1, name2));
				System.out.println("-----------------------------");
				break;
			}
			case 3: {
				System.out.println("Enter first person's name");
				String name1 = input.nextLine();
				System.out.println("Enter second person's name");
				String name2 = input.nextLine();
				String[] commonFriends = findCommonFriends(name1, name2);
				if (commonFriends != null) {
					System.out.printf("%s and %s have %d common friends%n", name1, name2, commonFriends.length);
					int count = 0;
					for (String s : commonFriends) {
						System.out.println(++count + ". " + s);
					}
				} else System.out.println("Sorry! No match found!");
				System.out.println("-----------------------------");
				break;
			}
			default: System.out.println("Goodbye!");break;
			}
		} while (choice != 4);
		input.close();
	}


	/** readFriends() reads the file with filename to 
	 * populate persons and personFriends arrays
	 */
	void readFriends(String filename) {
		Scanner input;
		try {
			input = new Scanner(new File(filename));
			StringBuilder fileContent = new StringBuilder();
			while (input.hasNextLine()) {
				fileContent.append(input.nextLine() + "\n");
			}
			String[] friendRows = fileContent.toString().split("\n");
			persons = new String[friendRows.length];
			personFriends = new String[friendRows.length][];
			
			
			
			String[][] splitRows = new String[friendRows.length][];
			for (int i = 0; i < friendRows.length; i++) {
				splitRows[i] = friendRows[i].toString().split(": ");
			}
			for (int i = 0; i < splitRows.length; i++) {
				persons[i] = splitRows[i][0].toString();
			}
			for (int i = 0; i < splitRows.length; i++) {
				personFriends[i] = splitRows[i][1].split(", ");
			}
			//test
			System.out.println();
			for (int i = 0; i < personFriends.length; i++) {
				for (int j = 0; j < personFriends[i].length; j++) {
					System.out.println(personFriends[i][j]);
				}
				System.out.println();
			}
			//
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** given a name, returns an array of friends a person has
	 * If the name is not found, it returns null
	 */
	String[] findFriends(String name) {
		//write your code here
		String[] friendsArray;
		for (int i = 0; i < persons.length; i++) {
			if (name.equals(persons[i].toLowerCase())) {
				friendsArray = new String[personFriends[i].length];
				for (int j = 0; j < personFriends[i].length; j++) {
					friendsArray[j] = personFriends[i][j];
				}
				return friendsArray;
			}
		}
		return null;
	}

	/** given two names, returns how many common friends they have */
	int countCommonFriends(String name1, String name2) {
		//write your code here
		int counter = 0;
		int index1 = 0;
		int index2 = 0;
		
		for (int i = 0; i < persons.length; i++) {
			if (name1.equals(persons[i].toLowerCase())) {
				index1 = i;
			}
		}
		for (int i = 0; i < persons.length; i++) {
			if (name2.equals(persons[i].toLowerCase())) {
				index2 = i;
			}
		}
		
		for (int i = 0; i < personFriends[index1].length; i++) {
			for (int j = 0; j < personFriends[index2].length; j++) {
				if (personFriends[index1][i].equals(personFriends[index2][j])) {
					counter++;
				}
			}
		}
		
		return counter;
	}

	/**given two names, returns an array of names of common friends. 
	 * If there are no common friends, then it returns an empty array, i.e. array of size 0*/
	String[] findCommonFriends(String name1, String name2) {
		//write your code here
		int index1 = -1;
		int index2 = -1;
		StringBuilder commonFriends = new StringBuilder();
		String[] commonFriendsArray;
		
		for (int i = 0; i < persons.length; i++) {
			if (name1.equals(persons[i].toLowerCase())) {
				index1 = i;
			}
		}
		for (int i = 0; i < persons.length; i++) {
			if (name2.equals(persons[i].toLowerCase())) {
				index2 = i;
			}
		}
		
		if (index1 == -1 || index2 == -1) {
			return null;
		}
		
		for (int i = 0; i < personFriends[index1].length; i++) {
			for (int j = 0; j < personFriends[index2].length; j++) {
				if (personFriends[index1][i].equals(personFriends[index2][j])) {
					commonFriends.append(personFriends[index1][i] + ",");
				}
			}
		}
		
		if (commonFriends.length() > 0) {
			commonFriendsArray = commonFriends.toString().split(",");
			return commonFriendsArray;
		} else if (commonFriends.length() == 0) {
			commonFriendsArray = new String[0];
			return commonFriendsArray;
		}
		
		return null;
		
	}

}
