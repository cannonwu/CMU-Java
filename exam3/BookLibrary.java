//Cannon Wu
//AndrewID: clwu

package exam3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class BookLibrary {

	List<Book> booksList;  //stores all books
	Map<String, List<Book>> authorsMap;  //stores all authors' names in their original 'case' as keys and their list of books as value
	String[] bookRecords; //carries data read from the file

	//do not change this method
	public static void main(String[] args) {
		
		BookLibrary bookLibrary = new BookLibrary();
		bookLibrary.readFile("BookAuthors.txt");
		bookLibrary.loadBooksList();
		bookLibrary.loadAuthorsMap();
		
		Scanner input = new Scanner(System.in);
		System.out.println("*** Welcome to Book Library ***");
		System.out.println("1. Print Books List");
		System.out.println("2. Print Authors Map");
		switch (input.nextInt()) {
		case 1: bookLibrary.printBooksList(); break;
		case 2: bookLibrary.printAuthorsMap(); break;
		default: break;
		}
		System.out.println("** Bye Bye! **"); 
		input.close();
	}

	
	//do not change this method
	void readFile(String filename) {
		StringBuilder fileData = new StringBuilder();
		try {
			Scanner input = new Scanner(new File(filename));
			while (input.hasNext()) {
				fileData.append(input.nextLine() + "\n");
			}
			bookRecords = fileData.toString().split("\n");
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	/**loadBooksList() loads data from bookRecords into
	 * booksList
	 */
	void loadBooksList() {
		//write your code here
		booksList = new ArrayList<>();
		for (int i = 0; i < bookRecords.length; i++) {
			String[] row = bookRecords[i].split("\t");
			Book b = new Book(row[0]);		//create a book object using first column String
			booksList.add(b);
			
			for (int j = 1; j < row.length; j++) {
				b.authors.add(row[j]);		//populate authors list for each book (column 1 & beyond)
			}
		}
	}

	/**loadAuthorsMap() loads data from booksList into
	 * authorsMap
	 */
	void loadAuthorsMap() {
		//write your code here
		authorsMap = new TreeMap<String, List<Book>>();
		for (Book b : booksList) {
			for (String author : b.authors) {			//get each author for the book
				if (authorsMap.containsKey(author)) {	//if author key exists in authorsMap
					authorsMap.get(author).add(b);		//add book to list of books for this author
				} else {
					List<Book> authorsBooks = new ArrayList<>();	//initialize author's list of books
					authorsBooks.add(b);							//add first book to author's list of books
					authorsMap.put(author, authorsBooks);			//put to map <author String, author's list of books>
				}
			}
		}
		
	}

	/**printBooksList() prints the output
	 * as shown in the handout
	 */
	void printBooksList() {
		//write your code here
		Collections.sort(booksList);
		int i = 0;
		for (Book b : booksList) {
			System.out.print(++i + ". " + b.title);
			for (String author : b.authors) {
				System.out.print(author + "; ");
			}
			System.out.println();
		}
	}
	
	/**printAuthorsMap prints the output
	 * as shown in the handout. 
	 */
	void printAuthorsMap() {
		//write your code here
		int i = 0;
		for (Map.Entry<String, List<Book>> e : authorsMap.entrySet()) {		//whole entry set
			System.out.println(++i + ". Books by " + e.getKey());			//print key (author)
			Collections.sort(e.getValue());									//sort list of books before reading (alphabetically)
			for (Book b : e.getValue()) {									//get List<Book> for each author
				System.out.println("  -" + b.title);
			}
		}
	}
	
}
