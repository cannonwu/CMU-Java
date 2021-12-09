//Cannon Wu
//AndrewID: clwu

package exam2;

public abstract class Media {
	String title;
	String year;
	
	Media(String title, String year) {
		this.title = title;
		this.year = year;
	}
	
	abstract int enjoy();
	
}
