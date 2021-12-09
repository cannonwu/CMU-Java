//Cannon Wu
//AndrewID: clwu

package lab6;

import java.util.ArrayList;
//import java.util.Objects;
import java.util.Objects;

public class Movie implements Comparable<Movie>{
	String movieName;
	String movieYear;
	ArrayList<String> movieGenres;
	
	Movie (String movieName, String movieYear) {
		this.movieName = movieName;
		this.movieYear = movieYear;
	}
	
	public int hashCode() {
		return Objects.hash(movieName, movieYear);
	}
	
	public boolean equals (Object o) {
		if (o == null) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (this.getClass() != o.getClass()) {
			return false;
		}
		Movie m = (Movie) o;
		return this.movieName.equalsIgnoreCase(m.movieName) && this.movieYear.equalsIgnoreCase(m.movieYear);
	}

	@Override
	public int compareTo(Movie o) {
		return movieName.compareTo(o.movieName);
	}
	
}
