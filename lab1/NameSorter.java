//Name: Cannon Wu
//AndrewID: clwu
package lab1;

import java.util.Scanner;

/**NameSorter class takes n number of names in the form of string inputs 
 * from the user. It then asks user for which name to search for. 
 * It sorts the names entered by the user, and then prints the 
 * position of the search-name in the sorted list of the names, 
 * if it is found.
 */

public class NameSorter {
	Scanner input = new Scanner(System.in);
	
	/**getNameInputs takes an int parameter n and creates an array of size n. 
	 * It then asks user to Enter n names that get stored in the array. 
	 * It uses the helper method toTitleCase() to convert all names to Title case. 
	 * It returns the array filled with names entered by the user.
	 */
	String[] getNameInputs(int n) {
		input.nextLine();
		String[] nameArray = new String[n];
		for (int i = 0; i < n; i++) {
			System.out.println("Enter name:");
			nameArray[i] = input.nextLine();
			toTitleCase(nameArray[i]);
		}
		return nameArray;
	}
	
	/**toTitleCase() takes one string argument as name and returns the string in title case. 
	 * If the name is null or the string is empty, it returns null.
	 */
	String toTitleCase(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		} else {
			name = name.toLowerCase();
			char[] nameChars = name.toCharArray();
			nameChars[0] = Character.toUpperCase(nameChars[0]);
			String titleName = String.copyValueOf(nameChars);
			return titleName;
		}
	}

	/**sortAndSearch() takes two arguments. The first is an array of strings and the second
	 * is a searchString. The method first sorts the array in increasing alphabetical order, 
	 * and prints it in that order.
	 * It then searches for the searchString in a case-insensitive way. If the searchString is found,
	 * it returns the index of the searchString according to the sorted list. 
	 * If it is not found, then it returns -1.
	 * It also handles nulls as needed.
	 */
	int sortAndsearch(String[] strings, String searchString) {
		if (strings == null || searchString == null) {
			return -1;
		}
		
		for (int i = 0; i < strings.length - 1; i++) {
			for (int j = 0; j < strings[i].length() - i - 1; i++) {
				if (strings[j].charAt(j) > strings[j + 1].charAt(j)) {
					String temp = strings[j];
					strings[j] = strings[j+1];
					strings[j+1] = temp;
				}
			}
		}
		
		for (int i = 0; i < strings.length - 1; i++) {
			if (searchString.equalsIgnoreCase(strings[i])) {
				return i - 1;
			}
		}
		
		return -1;
	}

	/**DO NOT CHANGE THIS METHOD */
	public static void main(String[] args) {
		NameSorter nameSorter = new NameSorter();
		System.out.println("*** How many names to store? ***");
		int n = nameSorter.input.nextInt();
		if (n > 0) {
			String[] names = nameSorter.getNameInputs(n);
			System.out.println("*** Enter the name to search ***");
			String name = nameSorter.input.next();
			int index = nameSorter.sortAndsearch(names, name);
			if (index >=0 ) System.out.println(name + " found at position " + (index+1));
			else System.out.println("Sorry! " + name + " not found!");
		} else System.out.println("Good Bye!");
	}
}
