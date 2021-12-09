//Cannon Wu
//AndrewID: clwu

package hw3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Case implements Comparable<Case>{
	private StringProperty caseDate;
	private StringProperty caseTitle;
	private StringProperty caseType;
	private StringProperty caseNumber;
	private StringProperty caseLink;
	private StringProperty caseCategory;
	private StringProperty caseNotes;
	
	//constructor
	Case(String caseDate, String caseTitle, String caseType, String caseNumber, 
				String caseLink, String caseCategory, String caseNotes) {
		 this.caseDate = new SimpleStringProperty((String) caseDate);		//initialize each Case element as StringProperty
		 this.caseTitle = new SimpleStringProperty((String) caseTitle);
		 this.caseType = new SimpleStringProperty((String) caseType);
		 this.caseNumber = new SimpleStringProperty((String) caseNumber);
		 this.caseLink = new SimpleStringProperty((String) caseLink);
		 this.caseCategory = new SimpleStringProperty((String) caseCategory);
		 this.caseNotes = new SimpleStringProperty((String) caseNotes);
	}
	
	//caseDate getters, setters
	public String getCaseDate() {
		return caseDate.get();
	}
	public void setCaseDate(String date) {
		this.caseDate.set(date);
	}
	public StringProperty caseDateProperty() {
		return caseDate;
	}
	
	//caseTitle getters, setters
	public String getCaseTitle() {
		return caseTitle.get();
	}
	public void setCaseTitle(String title) {
		this.caseTitle.set(title);
	}
	public StringProperty caseTitleProperty() {
		return caseTitle;
	}
	
	//caseType getters, setters
	public String getCaseType() {
		return caseType.get();
	}
	public void setCaseType(String type) {
		this.caseType.set(type);
	}
	public StringProperty caseTypeProperty() {
		return caseType;
	}
	
	//caseNumber getters, setters
	public String getCaseNumber() {
		return caseNumber.get();
	}
	public void setCaseNumber(String number) {
		this.caseNumber.set(number);
	}
	public StringProperty caseNumberProperty() {
		return caseNumber;
	}
	
	//caseLink getters, setters
	public String getCaseLink() {
		return caseLink.get();
	}
	public void setCaseLink(String link) {
		this.caseLink.set(link);
	}
	public StringProperty caseLinkProperty() {
		return caseLink;
	}
	
	//caseCategory getters, setters
	public String getCaseCategory() {
		return caseCategory.get();
	}
	public void setCaseCategory(String category) {
		this.caseCategory.set(category);
	}
	public StringProperty caseCategoryProperty() {
		return caseCategory;
	}
	
	//caseNotes getters, setters
	public String getCaseNotes() {
		return caseNotes.get();
	}
	public void setCaseNotes(String notes) {
		this.caseNotes.set(notes);
	}
	public StringProperty caseNotesProperty() {
		return caseNotes;
	}

	@Override
	public int compareTo(Case o) {		//compare caseDate and allow sorting by most recent
		// TODO Auto-generated method stub
		//return this.getCaseDate().compareTo(o.getCaseDate());
		return o.getCaseDate().compareTo(this.getCaseDate());
	}
	
	public String toString() {			//override toString() to return caseNumber
		return caseNumber.get();
	}
}
