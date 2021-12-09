//Cannon
//AndrewID: clwu

package lab7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/** GrammyAnalyst takes Grammys.txt to provide two reports and one search functionality
 */
public class GrammyAnalyst {
	/**initialize these member variables with appropriate data structures **/
	List<Nomination> nominations;  
	Map<String, List<Nomination>> grammyMap;  
	List<Artist> artists;
	
	public static void main(String[] args) {
		GrammyAnalyst ga = new GrammyAnalyst();
		ga.loadNominations();
		ga.loadGrammyMap();
		System.out.println("*********** Grammy Report ****************");
		ga.printGrammyReport();
		System.out.println("*********** Search Artist ****************");
		System.out.println("Enter artist name");
		Scanner input = new Scanner(System.in);
		String artist = input.nextLine();
		ga.searchGrammys(artist);
		ga.loadArtists();
		System.out.println("*********** Artists Report ****************");
		ga.printArtistsReport();
		input.close();
	}
	
	/**loadNominations() reads data from Grammys.txt and 
	 * populates the nominations list, where each element is a Nomination object
	 */
	void loadNominations() {
		//write your code here
		
		try {
			nominations = new ArrayList<Nomination>();
			Scanner input = new Scanner(new File("Grammys.txt"));
			String[] filerow;
			while (input.hasNextLine()) {
				filerow = input.nextLine().split(";");
				nominations.add(new Nomination(filerow[0].trim(), filerow[1].trim(), filerow[2].trim() ) );
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**loadGrammyMap uses artist name in lower case as the key, and a list of 
	 * all nominations for that artist as its value. Hint: use 'nominations' list 
	 * created in previous method to populate this map.
	 */
	void loadGrammyMap() {
		//write your code here
		
		//store everything in hashmap
		//if hashmap contains key, add existing value to current value
		
		grammyMap = new TreeMap<String, List<Nomination>>();
		for (Nomination n : nominations) {
			if (grammyMap.containsKey(n.artist.toLowerCase())) {
				grammyMap.get(n.artist.toLowerCase()).add(n);
			} else {
				ArrayList<Nomination> nomsList = new ArrayList<>();
				nomsList.add(n);
				grammyMap.put(n.artist.toLowerCase(), nomsList);
			}
		}
		
	} 
	
	/**loadArtists loads the artists array List. Each Artist object in it should have
	 * artist's name in proper case, i.e., as read from data file, and 
	 * a list of nominations for that artist. Hint: use 'grammyMap' created in 
	 * previous method to populate this list
	 */
	void loadArtists() {
		//write your code here
		artists = new ArrayList<>();
		for (Map.Entry<String, List<Nomination>> e : grammyMap.entrySet()) {
			artists.add(new Artist(e.getValue().get(0).artist, grammyMap.get(e.getValue().get(0).artist.toLowerCase())));
		}
		
	}
	
	/**printGrammyReport prints report as shown in the handout */
	void printGrammyReport() {
		//write your code here
		
		//can use nominations or grammyMap
		int count = 0;
		Collections.sort(nominations);
		for (Nomination n : nominations) {
			System.out.printf("%5s%s", (++count) + ". ", n.toString());
			System.out.println();
			//System.out.println(++count + ". " + n.toString());
		}
		
	}
	
	/**printArtistReport prints report as shown in the handout */
	void printArtistsReport() {
		//write your code here
		
		//can use nominations, grammyMap, artists
		int count = 0;
		Collections.sort(artists); //sort artist collection (by number of noms
		for (Artist a : artists) {
			System.out.printf("%5s%s", (++count) + ". ", a.toString());
			System.out.println();
			//System.out.println((++count) + ". " + a.toString());
		}
		
	}
	
	/**searchGrammys takes a string as input and makes a case-insensitive
	 * search on grammyMap. If found, it prints data about all nominations
	 * as shown in the handout.
	 */
	void searchGrammys(String artist) {
		//write your code here
		
		//can use nominations, grammyMap
		Collections.sort(nominations);
		boolean found = false;
		for (Nomination n : nominations) {
			if (n.artist.toLowerCase().contains(artist.toLowerCase())) {
				found = true;
				System.out.println(n.toString());
			}
		}
		
		if (found == false) {
			System.out.println("Sorry! " + artist + " not found!");
		}
		
	}
}
