//Cannon Wu
//AndrewID: clwu

package lab6;

import java.util.ArrayList;

public class Genre implements Comparable<Genre>{
	String genreName;
	ArrayList<Movie> genreMovies;
	
	Genre (String genreName) {
		this.genreName = genreName;
	}
	
	public int hashCode() {
		
		return 0;
	}
	
	public boolean equals (Object o) {
		
		return false;	
	}
	
	@Override
	public int compareTo(Genre genre) {
		
		return 0;
	}
	
}
