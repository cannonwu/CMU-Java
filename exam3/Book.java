//Cannon Wu
//AndrewID: clwu

package exam3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book implements Comparable<Book>{
	String title;
	List<String> authors;
	
	Book(String title) {
		this.title = title;
		authors = new ArrayList<>();
	}

	public int hashCode() {
		return Objects.hash(title);
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (this == o) return true;
		if (getClass() != o.getClass()) return false;
		Book b = (Book) o;
		return title.equals(b.title);
	}
	
	@Override
	public int compareTo(Book b) {
		return title.compareTo(b.title);
	}

}
