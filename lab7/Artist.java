//Cannon Wu
//AndrewID: clwu

package lab7;

import java.util.List;

public class Artist implements Comparable<Artist>{
	String name;
	List<Nomination> nominations;
	
	Artist(String name, List<Nomination> nominations) {
		this.name = name;
		this.nominations = nominations;
	}
	
	public String toString() {
		return name + ": " + nominations.size();
	}

	@Override
	public int compareTo(Artist o) {
		return o.nominations.size() - this.nominations.size();	//sort in descending order of noms
	}
	
}
