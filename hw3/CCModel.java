//Cannon Wu
//AndrewID: clwu

package hw3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class CCModel {
	ObservableList<Case> caseList;
	ObservableMap<String, Case> caseMap;
	ObservableMap<String, List<Case>> yearMap;
	ObservableList<String> yearList;

	/** readCases() performs the following functions:
	 * It creates an instance of CaseReaderFactory, 
	 * invokes its createReader() method by passing the filename to it, 
	 * and invokes the caseReader's readCases() method. 
	 * The caseList returned by readCases() is sorted 
	 * in the order of caseDate for initial display in caseTableView. 
	 * Finally, it loads caseMap with cases in caseList. 
	 * This caseMap will be used to make sure that no duplicate cases are added to data
	 * @param filename
	 */
	void readCases(String filename) {
		//write your code here
		
		//create caseReader
		CaseReaderFactory crf = new CaseReaderFactory();
		CaseReader caseReader = crf.createReader(filename);
		
		//read cases into caseArray
		List<Case> caseArray = caseReader.readCases();			
		Collections.sort(caseArray);	//sort case array
		
		//initialize caseList
		caseList = FXCollections.observableArrayList(caseArray);
		
		//initialize caseMap
		caseMap = FXCollections.observableHashMap();		//map with caseNumber as key and Case as value
		for (Case c : caseList) {
			caseMap.put(c.getCaseNumber(), c);
		}
		
	}

	/** buildYearMapAndList() performs the following functions:
	 * 1. It builds yearMap that will be used for analysis purposes in Cyber Cop 3.0
	 * 2. It creates yearList which will be used to populate yearComboBox in ccView
	 * Note that yearList can be created simply by using the keySet of yearMap.
	 */
	void buildYearMapAndList() {
		//write your code here
		
		//extract YYYY part of caseDate and store in array, no duplicates
		StringBuilder sbYears = new StringBuilder();
		for (Case c : caseList) {
			StringBuilder tempYear = new StringBuilder();
			char[] charDate = c.getCaseDate().toCharArray();
			for (int i = 0; i < 4; i++) {
				tempYear.append(charDate[i]);		//tempYear = YYYY part of caseDate
			}
			if (!sbYears.toString().contains(tempYear)) {
				sbYears.append(tempYear + ",");
			}
		}
		String[] years = sbYears.toString().split(",");
		
		//build yearMap
		yearMap = FXCollections.observableHashMap();	//map with each year as a key and a list of all cases dated in that year as value. 
		for (int i = 0; i < years.length; i++) {
			ArrayList<Case> singleYearCaseList = new ArrayList<Case>();
			for (Case c : caseList) {
				if (c.getCaseDate().contains(years[i])) {
					singleYearCaseList.add(c);
				}
			}
			yearMap.put(years[i], singleYearCaseList);
		}
		
		//build yearList
		yearList = FXCollections.observableArrayList();			//list of years to populate the yearComboBox in ccView
		yearList.addAll(yearMap.keySet());
		Collections.sort(yearList);
	}

	/**searchCases() takes search criteria and 
	 * iterates through the caseList to find the matching cases. 
	 * It returns a list of matching cases.
	 */
	List<Case> searchCases(String title, String caseType, String year, String caseNumber) {
		//write your code here
		List<Case> caseResults = new ArrayList<>();
		caseResults.addAll(caseList);
		Iterator<Case> iter;	//need to use iterator since removing items
		
		if (title != null) {
			iter = caseResults.iterator();		//initialize iterator
			while (iter.hasNext()) {
				Case c = iter.next();
				if (!c.getCaseTitle().toLowerCase().contains(title.toLowerCase())) {
					iter.remove();	//remove cases that do not match input title
				}
			}
		}
		
		if (caseType != null) {
			iter = caseResults.iterator();		//clear iterator
			while (iter.hasNext()) {
				Case c = iter.next();
				if (!c.getCaseType().equalsIgnoreCase(caseType)) {
					iter.remove();	//remove cases that do not match input type
				}
			}
		}
		
		if (year != null) {
			iter = caseResults.iterator();		//clear iterator
			List<Case> caseFoundByYear = new ArrayList<>();
			caseFoundByYear = yearMap.get(year);	//create list of cases where case year matches input year
			while (iter.hasNext()) {
				Case c = iter.next();
				if (!caseFoundByYear.contains(c)) {
					iter.remove();	//remove cases that do not match input year
				}
			}
		}
		
		if (caseNumber != null) {
			iter = caseResults.iterator();		//clear iterator
			while (iter.hasNext()) {
				Case c = iter.next();
				if (!c.getCaseNumber().contains(caseNumber)) {
					iter.remove();	//remove cases that do not match input number
				}
			}
		}
		
		return caseResults;
	}
	
	boolean writeCases(String filename) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			//write all cases to the save file
			for (Case c : caseList) {
				bw.write(c.getCaseDate() + "\t" + c.getCaseTitle() + "\t" + c.getCaseType() + "\t" + c.getCaseNumber() + "\t" + c.getCaseLink() + " \t" + c.getCaseCategory() + " \t" + c.getCaseNotes() + " ");
				bw.write("\n");
			}
			bw.close();
			return true;
		} catch (IOException e){
			return false;
		}
	
	
	
	}
}
