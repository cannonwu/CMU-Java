//Cannon
//AndrewID: clwu

package lab7;

public class Nomination implements Comparable<Nomination>{
	String category;
	String title;
	String artist;
	
	Nomination(String category, String title, String artist) {
		this.category = category;
		this.title = title;
		this.artist = artist;
	}
	
	public String toString() {
		return artist + ": " + category + ": " + title;
	}

	@Override
	public int compareTo(Nomination o) {
		// TODO Auto-generated method stub
		return this.artist.toLowerCase().compareTo(o.artist.toLowerCase());
	}
}
