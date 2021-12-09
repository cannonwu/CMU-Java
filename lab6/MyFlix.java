//Cannon Wu
//AndrewID: clwu

package lab6;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MyFlix {
	List<Movie> moviesList = new ArrayList<>();
	List<Genre> genresList = new ArrayList<>();
	String[] movieDBStrings;

	//do not change this method
	public static void main(String[] args) {
		MyFlix myFlix = new MyFlix();
		myFlix.movieDBStrings = myFlix.readMovieDB("MoviesDB.tsv");
		myFlix.loadMovies();
		myFlix.loadGenres();
		Collections.sort(myFlix.moviesList);
		Collections.sort(myFlix.genresList);
		myFlix.showMenu();
	}

	//do not change this method
	//showMenu() displays the menu for the user to choose from,
	//takes required inputs, and invokes related methods
	void showMenu() {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		System.out.println("*** Welcome to MyFlix ***"); 
		System.out.println("1. Search for a movie");
		System.out.println("2. List of genres");
		System.out.println("3. Exit");
		choice = input.nextInt();
		input.nextLine();
		switch (choice) {
		case 1: {
			System.out.println("Enter the string to search in movie names");
			String searchString = input.nextLine();
			printSearchResults(findMovies(searchString));
			break;
		}
		case 2: {
			printGenreReport();
			break;
		}
		case 3: System.out.println("Bye Bye!"); break;
		default: break;
		}
		input.close();
	}

	//do not change this method
	//readMovieDB reads all data from the MovieDB file
	//and loads each row as a string in movieDBStrings
	String[] readMovieDB(String filename) {
		StringBuilder movies = new StringBuilder();
		try {
			Scanner input = new Scanner(new File(filename));
			while (input.hasNextLine()) {
				movies.append(input.nextLine() + "\n");
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return movies.toString().split("\n");
	}

	//loadMovies use data in movieDBStrings to create Movie objects 
	//and add them to moviesList.
	void loadMovies() {
		//write your code here
		//movieDBStrings contains each line in file, index[0] is moviename, index[1] is year, index[1+] is genres
		String[][] movie2D = new String[movieDBStrings.length][];
		for (int i = 0; i < movie2D.length; i++) {
			movie2D[i] = movieDBStrings[i].split("\t");
		}
		
		for (int i = 0; i < movie2D.length; i++) {
			Movie m = new Movie(movie2D[i][0], movie2D[i][1]);
			moviesList.add(m);
			m.movieGenres = new ArrayList<>();
			if (movie2D.length > 2 ) {
				for (int j = 2; j < movie2D[i].length; j++) {
					m.movieGenres.add(movie2D[i][j].toLowerCase() + ",");
				}
			}
		}
		
		
//		for (int i = 0; i < movieDBStrings.length; i++) {
//			moviesList.add(new Movie(null, null))
//		}
	}

	//loadGenres uses data in moviesList to create Genre objects 
	//and add them to genresList
	void loadGenres() {
		//write your code here
	}

	//findMovies() returns a list of Movie objects 
	//that have the searchString in their names
	List<Movie> findMovies(String searchString) {
		//write your code here
		ArrayList<Movie> foundMovies = new ArrayList<Movie>();
		Iterator<Movie> iter = moviesList.iterator();
		while (iter.hasNext()) {
			Movie m = iter.next();
			if (m.movieName.toLowerCase().contains(searchString.toLowerCase())) {
				foundMovies.add(m);
			}
		}
		
		return foundMovies;
	}

	//print the search output. 
	//You may use this printf statement:System.out.printf("%3d. %-50s\tYear: %s\n", ++count, movie.movieName, movie.movieYear);
	void printSearchResults(List<Movie> searchResults) {
		//write your code here
		if (searchResults.size() < 1 ) {
			System.out.println("Sorry! No movie found!");
		} else {
			int count = 0;
			Collections.sort(searchResults);
			for (Movie movie : searchResults) {
				System.out.printf("%3d. %-50s\tYear: %s\n", ++count, movie.movieName, movie.movieYear);
			}
		}
	}

	//print the genre summary report. 
	//You may use this printf statement:System.out.printf("%3d. %-15s Number of movies: %,6d%n", ++count, genre.genreName, genre.genreMovies.size());
	void printGenreReport() {
		//write your code here
	}
}
