//Cannon Wu
//AndrewID: clwu

package exam2;

public class Book extends Media{
	final static int PAGE_READING_TIME = 5;
	String author;
	int pages;
	
	
	Book(String title, String year, String author, int pages) {
		super(title, year);
		this.author = author;
		this.pages = pages;
		// TODO Auto-generated constructor stub
	}

	@Override
	int enjoy() {
		System.out.println("Reading book: " + title + " by " + author + " for next " + (pages * PAGE_READING_TIME) + " minutes");
		return pages * PAGE_READING_TIME;
	}

}
