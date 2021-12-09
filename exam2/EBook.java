//Cannon Wu
//AndrewID: clwu

package exam2;

public class EBook extends Book implements Downloadable{
	String format;
	int size;
	
	EBook(String title, String year, String author, int pages, String format, int size) {
		super(title, year, author, pages);
		this.format = format;
		this.size = size;
		
	}
	
	public int download() {
		System.out.println("Downloading eBook: " + title + " by " + author + " for next " + (size / INTERNET_SPEED) + " seconds");
		return size / INTERNET_SPEED;
	}

	int enjoy() {
		System.out.println("Reading eBook: " + title + " by " + author + " for next " + (pages * PAGE_READING_TIME) + " minutes");
		return pages * PAGE_READING_TIME;
	}
}
